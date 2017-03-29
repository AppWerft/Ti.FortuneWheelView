package com.myriadmobile.fortune;

import android.content.Context;

import org.appcelerator.titanium.util.TiConvert;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;

import com.myriadmobile.fortune.paths.CircleWheelPath;
import com.myriadmobile.fortune.paths.CustomWheelPath;

import java.util.ArrayList;
import java.util.List;

import org.appcelerator.kroll.KrollDict;

/**
 * Created by cclose on 9/3/14.
 */
public class FortuneView extends View implements RedrawListener,
		WheelClickListener {

	private ArrayList<FortuneItem> fortuneItems = new ArrayList<FortuneItem>();
	public static final String LCAT = "FortuneView";
	double radius;
	GrooveListener grooveListener;
	WheelClickListener wheelClickListener;
	int lastGrooveIndex = 0;
	Matrix matrix = new Matrix();
	SwipeController swipeController;
	Canvas mCanvas;
	CustomWheelPath path = new CircleWheelPath();

	// Default settings
	private float spinSensitivity; // Multipler for spin speed. ie .5,
									// half
									// the speed of finger
	private int frameRate; // Frames per second
	private double friction; // Slows down friction radians per second
	private double velocityClamp; // clamps max fling to radians per second
	private boolean flingable; // Decides if the user can fling
	private boolean grooves; // Locks at correct angles
	private int notch; // Where the notch is located in degrees
	private float unselectScaleOffset = 1f; // Scale offset of unselected icons
	private float selectScaleOffset = 1f; // Scale offset of the selected icons
	private float distanceScale = 1; // Float from 0 - 1 (should be) to decide
										// how close to the edge the icons show
	private float centripetalPercent = .25f; // Float from -.5 - distancePercent
												// amount of Centripetal force
												// affects you
	private int backgroundResourceId = -1; // Resource id of the background
	private float backgroundScale = 1; // Scale of the background image
	private boolean backgroundCentripetalForce = false; // Does centripetal
														// force act on the
														// background
	public FortuneItem.HingeType backgroundHinge = FortuneItem.HingeType.Fixed; // Background
	private float minimumSize = .5f; // Minimun size of a view

	/* constructor */
	public FortuneView(Context context) {
		super(context);
		/* now we need attributes from proxy ??? */
		// Log.d(LCAT,WheelViewProxy.attributes.toString());

	}

	public void setOptions(KrollDict attributes) {
		centripetalPercent = TiConvert
				.toFloat(attributes, "centripetalPercent");
		distanceScale = TiConvert.toFloat(attributes, "distanceScale");
		flingable = TiConvert.toBoolean(attributes, "flingable");
		frameRate = TiConvert.toInt(attributes, "frameRate");
		friction = TiConvert.toDouble(attributes, "friction");
		grooves = TiConvert.toBoolean(attributes, "grooves");
		minimumSize = TiConvert.toFloat(attributes, "minimumSize");
		notch = TiConvert.toInt(attributes, "notch");
		spinSensitivity = TiConvert.toFloat(attributes, "spinSensitivity");
		selectScaleOffset = TiConvert.toFloat(attributes, "selectScaleOffset");
		unselectScaleOffset = TiConvert.toFloat(attributes,
				"unselectScaleOffset");
		velocityClamp = TiConvert.toDouble(attributes, "velocityClamp");
	}

	public void initSwipeControler() {
		swipeController = new SwipeController(this, this, velocityClamp,
				friction, frameRate, grooves, flingable, spinSensitivity,
				grooveListener);
	};

	public void setGrooveListener(GrooveListener grooveListener) {
		this.grooveListener = grooveListener;
	}

	public void addFortuneItems(List<FortuneItem> items) {
		fortuneItems.addAll(items);
		swipeController.setTotalItems(fortuneItems.size());
		reconfigure(true);
	}

	public void addFortuneItem(FortuneItem item) {
		fortuneItems.add(item);
		swipeController.setTotalItems(fortuneItems.size());
		reconfigure(true);
	}

	private void reconfigure(boolean invalidate) {
		if (mCanvas != null) {
			// Calculate size of bitmaps
			int width = mCanvas.getWidth();
			int height = mCanvas.getHeight();
			radius = width > height ? height / 2 : width / 2;
			if (invalidate)
				invalidate();
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (mCanvas == null) {
			mCanvas = canvas;
			reconfigure(false);
		} else {
			mCanvas = canvas;
		}
		super.onDraw(canvas);

		// Offset radians
		double radians = swipeController.getRadianOffset();
		double centripitalForceAmount = (centripetalPercent * swipeController
				.getCentripetalPercent());

		// Add groove notch
		radians -= notch * Math.PI / 180;

		// Add Background to the canvas.
		if (backgroundResourceId != -1) {
			Bitmap backgroundImage = BitmapFactory.decodeResource(
					getResources(), backgroundResourceId);
			int smallSide = (int) ((canvas.getWidth() > canvas.getHeight() ? canvas
					.getHeight() : canvas.getWidth()) * (backgroundScale - (centripitalForceAmount * (backgroundCentripetalForce ? 1
					: 0))));

			// Add background image
			int backgroundX = smallSide / 2;
			int backgroundY = smallSide
					* (backgroundImage.getHeight() / backgroundImage.getWidth())
					/ 2;
			matrix.reset();
			if (backgroundHinge == FortuneItem.HingeType.Fixed) {
				matrix.postRotate((float) (radians / Math.PI * 180),
						backgroundImage.getWidth() / 2,
						backgroundImage.getHeight() / 2);
			}
			matrix.postScale(smallSide / (float) backgroundImage.getWidth(),
					smallSide / (float) backgroundImage.getWidth());
			matrix.postTranslate(canvas.getWidth() / 2 - backgroundX,
					canvas.getHeight() / 2 - backgroundY);

			canvas.drawBitmap(backgroundImage, matrix, null);
		}

		// Apply centripital force
		for (int i = 0; i < fortuneItems.size(); i++) {
			double rad = (path.getRadiusAtRadians(radians) * (distanceScale - centripitalForceAmount))
					* radius;
			// Draw dialItem
			radians = fortuneItems.get(i).drawItem(
					canvas,
					rad,
					radians,
					getTotalValue(),
					(i == getSelectedIndex() ? selectScaleOffset
							: unselectScaleOffset), minimumSize,
					path.sizeBasedOnRadius());
		}

		// Notify Listener
		if (getSelectedIndex() != lastGrooveIndex) {
			lastGrooveIndex = getSelectedIndex();
			if (grooveListener != null)
				grooveListener.onGrooveChange(lastGrooveIndex);

		}
	}

	@Override
	public void redraw() {
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mCanvas != null) {
			if (swipeController.handleUserEvent(event, mCanvas.getWidth(),
					mCanvas.getHeight()))
				return true;
		}
		return super.onTouchEvent(event);
	}

	/**
	 * @return the currently selected index on the wheel
	 */
	public int getSelectedIndex() {
		if (fortuneItems.size() == 0)
			return 0;

		double offset = swipeController.getRadianOffset();
		if (offset < 0) {
			offset -= Math.PI / fortuneItems.size();
		} else {
			offset += Math.PI / fortuneItems.size();
		}
		int index = ((int) (-offset / (Math.PI * 2 / fortuneItems.size())))
				% fortuneItems.size();
		if (index < 0)
			index += fortuneItems.size();
		return index;
	}

	/**
	 * Moves the wheel to the index IF user is not interacting with wheel
	 * 
	 * @param index
	 *            index of the fortune item
	 */
	public void setSelectedItem(int index) {
		if (index < 0 || index >= fortuneItems.size()
				|| index == getSelectedIndex())
			return;

		// Fling to the needed offset for the icon
		swipeController.flingToRadians(positionOnWheel(index));

	}

	/**
	 * @return total items in the fortune items list
	 */
	public int getTotalItems() {
		return fortuneItems.size();
	}

	public void setCustomPath(CustomWheelPath path) {
		this.path = path;
	}

	/**
	 * @return the total amount of value in the wheel
	 */
	private float getTotalValue() {
		float total = 0;
		for (FortuneItem di : fortuneItems)
			total += di.value;
		return total;
	}

	/**
	 * @param index
	 *            of wheel
	 * @return the poisition in radians of that notch(not including notch start)
	 */
	private double positionOnWheel(int index) {
		double total = 0;
		for (int i = 0; i < index; i++) {
			total -= fortuneItems.get(i).value / getTotalValue() * Math.PI * 2;
		}
		return total;
	}

	@Override
	public void onLongpress() {
		if (grooveListener != null)
			grooveListener.onGrooveClicked(lastGrooveIndex);
	}
}

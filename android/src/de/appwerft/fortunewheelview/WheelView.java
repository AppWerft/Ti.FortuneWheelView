package de.appwerft.fortunewheelview;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiCompositeLayout;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.view.TiCompositeLayout.LayoutArrangement;
import java.util.HashMap;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import com.myriadmobile.fortune.FortuneView;
import android.content.res.TypedArray;

public class WheelView extends TiUIView {
	FortuneView wheelView;

	WheelView(TiViewProxy proxy) {
		super(proxy);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		LinearLayout container = new LinearLayout(proxy.getActivity());
		container.setLayoutParams(lp);
		wheelView = new FortuneView(proxy.getActivity());
		container.addView(wheelView);
		setNativeView(container);
	}

	void addWheel(String[] icons, HashMap<String, Object> options) {

	};

	@Override
	public void processProperties(KrollDict d) {
		super.processProperties(d);
	}
}
/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package de.appwerft.fortunewheelview;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;

import com.myriadmobile.fortune.FortuneView;

import android.app.Activity;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import java.util.HashMap;

// This proxy can be created by calling Wheel.createExample({message: "hello world"})
@Kroll.proxy(creatableInModule = WheelViewModule.class)
public class WheelViewProxy extends TiViewProxy {
	// Standard Debugging variables
	public static final String LCAT = "WheelView";
	public WheelView mView;
	public String[] icons;

	public HashMap<String, Object> attrs = new HashMap<String, Object>();

	// Constructor
	public WheelViewProxy() {
		super();
	}

	@Override
	public TiUIView createView(Activity activity) {
		mView = new WheelView(this);
		mView.getLayoutParams().autoFillsHeight = true;
		mView.getLayoutParams().autoFillsWidth = true;
		mView.addWheel(icons,attrs);
		return mView;
	}

	// Handle creation options
	@Override
	public void handleCreationDict(KrollDict args) {
		super.handleCreationDict(args);
		if (args.containsKey("icons")) {
			icons = args.getStringArray("icons");
		}
		if (args.containsKey("options")) {
			final HashMap<String, Object> options;
			options = args.getKrollDict("options");
			attrs.put("spinSensitivity",
					options.getOrDefault("spinSensitivity", 1f));
			attrs.put("frameRate", options.getOrDefault("frameRate", 40));
			attrs.put("friction", options.getOrDefault("friction", 5));
			attrs.put("velocityClamp", options.getOrDefault("velocityClamp", 15));
			attrs.put("flingable", options.getOrDefault("flingable", true));
			attrs.put("grooves", options.getOrDefault("grooves", true));
			attrs.put("notch", options.getOrDefault("notch", 90));
			attrs.put("unselectScaleOffset",
					options.getOrDefault("unselectScaleOffset", .8f));
			attrs.put("selectScaleOffset",
					options.getOrDefault("selectScaleOffset", 1));
			attrs.put("distanceScale", options.getOrDefault("distanceScale", 1));
			attrs.put("centripetalPercent",
					options.getOrDefault("centripetalPercent", .25f));
			attrs.put(" minimumSize", options.getOrDefault(" minimumSize", .1f));
		}
	}
	
	
	private class WheelView extends TiUIView {
		FortuneView wheelView;

		WheelView(TiViewProxy proxy) {
			super(proxy);
			Log.d("WheelView", attrs.toString());
			LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			LinearLayout container = new LinearLayout(proxy.getActivity());
			container.setLayoutParams(lp);
			AttributeSet attrs = new AttributeSet() {
				@Override
				public int getAttributeCount() {
					return 0;
				}

				@Override
				public String getAttributeName(int index) {
					return null;
				}

				@Override
				public String getAttributeValue(int index) {
					return null;
				}

				@Override
				public String getAttributeValue(String namespace, String name) {
					return null;
				}

				@Override
				public String getPositionDescription() {
					return null;
				}

				@Override
				public int getAttributeNameResource(int index) {
					return 0;
				}

				@Override
				public int getAttributeListValue(String namespace,
						String attribute, String[] options, int defaultValue) {
					return 0;
				}

				@Override
				public boolean getAttributeBooleanValue(String namespace,
						String attribute, boolean defaultValue) {
					return false;
				}

				@Override
				public int getAttributeResourceValue(String namespace,
						String attribute, int defaultValue) {
					return 0;
				}

				@Override
				public int getAttributeIntValue(String namespace, String attribute,
						int defaultValue) {
					return 0;
				}

				@Override
				public int getAttributeUnsignedIntValue(String namespace,
						String attribute, int defaultValue) {
					return 0;
				}

				@Override
				public float getAttributeFloatValue(String namespace,
						String attribute, float defaultValue) {
					return 0;
				}

				@Override
				public int getAttributeListValue(int index, String[] options,
						int defaultValue) {
					return 0;
				}

				@Override
				public boolean getAttributeBooleanValue(int index,
						boolean defaultValue) {
					return false;
				}

				@Override
				public int getAttributeResourceValue(int index, int defaultValue) {
					return 0;
				}

				@Override
				public int getAttributeIntValue(int index, int defaultValue) {
					return 0;
				}

				@Override
				public int getAttributeUnsignedIntValue(int index, int defaultValue) {
					return 0;
				}

				@Override
				public float getAttributeFloatValue(int index, float defaultValue) {
					return 0;
				}

				@Override
				public String getIdAttribute() {
					return null;
				}

				@Override
				public String getClassAttribute() {
					return null;
				}

				@Override
				public int getIdAttributeResourceValue(int defaultValue) {
					return 0;
				}

				@Override
				public int getStyleAttribute() {
					return 0;
				}
			};
			wheelView = new FortuneView(proxy.getActivity(), attrs);
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
	
	
}
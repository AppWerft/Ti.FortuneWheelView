package de.appwerft.fortunewheelview;


import org.appcelerator.kroll.KrollDict;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiCompositeLayout;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.view.TiCompositeLayout.LayoutArrangement;

public class WheelView extends TiUIView {
		public WheelView(TiViewProxy proxy) {
			super(proxy);
			LayoutArrangement arrangement = LayoutArrangement.DEFAULT;
			setNativeView(new TiCompositeLayout(proxy.getActivity(),
					arrangement));
		}

		@Override
		public void processProperties(KrollDict d) {
			super.processProperties(d);
		}
	}
package de.appwerft.helpers;

import android.graphics.Bitmap;
import java.io.IOException;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.io.TiFileFactory;
import org.appcelerator.titanium.io.TiBaseFile;
import org.appcelerator.titanium.TiBlob;
//import org.appcelerator.titanium.util.TiUIHelper;
import org.appcelerator.titanium.util.TiUIHelper;

public class AHelper {
	public static TiBlob loadImageFromNativePath(String nativePath) {
		TiBlob result = null;
		try {
			// Load the image from the application assets
			TiBaseFile file = TiFileFactory.createTitaniumFile(
					new String[] { nativePath }, false);
			Bitmap bitmap = TiUIHelper.createBitmap(file.getInputStream());

			// The bitmap must be converted to a TiBlob before returning
			result = TiBlob.blobFromImage(bitmap);
		} catch (IOException e) {
			Log.e("AssetHelper", " EXCEPTION - IO");
		}
		return result;
	}
	public static Bitmap loadBitmapFromNativePath(String nativePath) {
		Bitmap  bitmap = null;
		
		try {
			// Load the image from the application assets
			TiBaseFile file = TiFileFactory.createTitaniumFile(
					new String[] { nativePath }, false);
			bitmap = TiUIHelper.createBitmap(file.getInputStream());

			// The bitmap must be converted to a TiBlob before returning
			
		} catch (IOException e) {
			Log.e("AssetHelper", " EXCEPTION - IO");
		}
		return bitmap;
	}
}

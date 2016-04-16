package lib.dalcoms.andengineheesanglib.utils;

import android.app.Activity;
import android.widget.Toast;

public final class HsUtils {

	private HsUtils( ) {

	}

	public static void safeToastMessageShow( Activity pActivity, String pToastMsg, int pToastLength ) {
		final String toastMsg = pToastMsg;
		final int toastLength = pToastLength;
		final Activity activity = pActivity;
		
		activity.runOnUiThread( new Runnable() {
			@Override
			public void run( ) {
				Toast.makeText( activity.getApplicationContext(),
						toastMsg,
						toastLength ).show();
			}
		} );
	}

}

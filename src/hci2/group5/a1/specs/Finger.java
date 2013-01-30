package hci2.group5.a1.specs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

public enum Finger {

	THUMB(100), INDEX(200);
	
	public final int id;

	Finger(int id) {
		this.id = id;
	}
	
	public Dialog getAlertDialog(Activity activity) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity)
	    	.setMessage(String.format("Please use your %s finger.", name().toLowerCase()))
	    	.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int which) { 
	    			dialog.dismiss();
	    		}
	    	})
	    	.setCancelable(false);
		return builder.create();
	}
	
	public char toAbbr() {
		return name().charAt(0);
	}
}

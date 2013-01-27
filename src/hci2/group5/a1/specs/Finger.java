package hci2.group5.a1.specs;

import hci2.group5.a1.main.DataRecorder;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

public enum Finger {

	Thumb(1), Index(2);
	
	public final int id;

	Finger(int id) {
		this.id = id;
	}
	
	public Dialog getAlertDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context)
	    	.setMessage(String.format("Please use your %s finger.", name().toLowerCase()))
	    	.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int which) { 
	    			dialog.dismiss();
	    			DataRecorder.getInstance().record("Finger: " + name().toLowerCase());
	    		}
	    	});
		return builder.create();
	}
}

package hci2.group5.a1.main;

import hci2.group5.a1.specs.Finger;
import hci2.group5.a1.specs.User;
import hci2.group5.a1.util.DataRecorder;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.EditText;

public class DialogHandler {

	// make sure not conflict with Finger id in the Finger class
    public static final int ASK_USER_NAME_ID = 0;
    public static final int STATATISTICS_ID = 1;
    public static final int DONE_DIALOG_ID = 2;

	private final MainActivity mainActivity;
    
	public DialogHandler(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	public Dialog onCreateDialog(int id) {

		if (id == Finger.THUMB.id) {
			return Finger.THUMB.getAlertDialog(mainActivity);
		}
		else if (id == Finger.INDEX.id) {
			return Finger.INDEX.getAlertDialog(mainActivity);
		}
		else if (id == STATATISTICS_ID) {
			return getStatDialog();
		}
		else if (id == DONE_DIALOG_ID) {
			return getDoneDialog();
		}
		else if (id == ASK_USER_NAME_ID) {
			return getAskUsernameDialog();
		}
		else {
			return null;
		}
	}
	
	public Dialog getStatDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity)
			.setTitle(DataRecorder.getInstance().niceTitleOutput())
	    	.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int which) { 
	    			dialog.dismiss();
	    			mainActivity.removeDialog(DialogHandler.STATATISTICS_ID);
	    		}
	    	})
	    	.setNegativeButton("Email Log", new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int which) {
	    			DataRecorder.getInstance().email(mainActivity);
	    		}
	    	})
	    	.setMessage(DataRecorder.getInstance().niceContentOutput());
		
		return builder.create();
	}

	public Dialog getDoneDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity)
		.setTitle("Thank you")
		.setPositiveButton("View Log", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				mainActivity.showDialog(STATATISTICS_ID);
			}
		})
		.setMessage("The experiment is done.");

		return builder.create();
	}

	public Dialog getAskUsernameDialog() {
		
		final EditText inputEditText = new EditText(mainActivity);
		inputEditText.setSingleLine(true);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity)
		.setTitle("Please enter your name")
		.setView(inputEditText)
		.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String inputString = inputEditText.getText().toString().trim();
				dialog.dismiss();
				User.name = inputString;

				mainActivity.showDialog(User.currFinger.id);
				
				inputEditText.setText("");
			}
		})
		.setCancelable(false);

		return builder.create();
	}
}
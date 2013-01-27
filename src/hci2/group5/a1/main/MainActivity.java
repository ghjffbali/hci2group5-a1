package hci2.group5.a1.main;

import hci2.group5.a1.R;
import hci2.group5.a1.specs.Finger;
import hci2.group5.a1.square.TwinSquareView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends Activity {

    public static final int INPUT_USER_NAME_ID = 99;

    public static final int STATATISTICS_ID = 5;

    public static final int DONE_DIALOG_ID = 10;
    
	private TwinSquareView twinSquareView;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        start();
    }

	private void start() {
		twinSquareView = new TwinSquareView(this);
        setContentView(twinSquareView);
        
        showDialog(INPUT_USER_NAME_ID);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {

		if (id == Finger.Thumb.id) {
			return Finger.Thumb.getAlertDialog(this);
		}
		else if (id == Finger.Index.id) {
			return Finger.Index.getAlertDialog(this);
		}
		else if (id == STATATISTICS_ID) {
			return getStatDialog();
		}
		else if (id == DONE_DIALOG_ID) {
			return getDoneDialog();
		}
		else if (id == INPUT_USER_NAME_ID) {
			return getUsernameInputDialog();
		}
		else {
			return super.onCreateDialog(id);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Statistics");
		menu.add("Restart");
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getTitle().equals("Statistics")) {
			showDialog(STATATISTICS_ID);
		}
		else if (item.getTitle().equals("Restart")) {
			reset();
		}
		return true;
	}
	
	public Dialog getStatDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
			.setTitle(DataRecorder.getInstance().niceTitleOutput())
	    	.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int which) { 
	    			dialog.dismiss();
	    			MainActivity.this.removeDialog(MainActivity.STATATISTICS_ID);
	    		}
	    	})
	    	.setMessage(DataRecorder.getInstance().niceContentOutput());
		
		return builder.create();
	}

	private Dialog getDoneDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
		.setTitle("Thank you")
		.setPositiveButton("View Log", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				showDialog(STATATISTICS_ID);
			}
		})
		.setMessage("The experiment is done.");

		return builder.create();
	}

	private Dialog getUsernameInputDialog() {
		
		final EditText input = new EditText(this);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
		.setTitle("Please enter your full name")
		.setView(input)
		.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				DataRecorder.getInstance().recordName(input.getText().toString());
				input.setText("");
			}
		});

		return builder.create();
	}
	
	public void reset() {
		DataRecorder.getInstance().clear();
		start();
	}
}
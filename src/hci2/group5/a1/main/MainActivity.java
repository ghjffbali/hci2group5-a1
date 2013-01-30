package hci2.group5.a1.main;

import hci2.group5.a1.specs.User;
import hci2.group5.a1.square.MainView;
import hci2.group5.a1.util.DataRecorder;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private DialogHandler dialogHandler;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialogHandler = new DialogHandler(this);
        
        start();
    }

	private void start() {
        setContentView(new MainView(this));
        showDialog(DialogHandler.ASK_USER_NAME_ID);
	}
	
	public void reStart() {
		DataRecorder.getInstance().clear();
		User.reset();
		start();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = dialogHandler.onCreateDialog(id);
		
		return (dialog != null) ? dialog : super.onCreateDialog(id);
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
			showDialog(DialogHandler.STATATISTICS_ID);
		}
		else if (item.getTitle().equals("Restart")) {
			reStart();
		}
		return true;
	}
}
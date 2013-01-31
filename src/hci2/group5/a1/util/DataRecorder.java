package hci2.group5.a1.util;

import hci2.group5.a1.specs.User;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;

public class DataRecorder {
	
	private ArrayList<String> rawData;
	private ArrayList<String> summaryData; // record average movement time and error rate
	
	private static DataRecorder instance = new DataRecorder();

	private DataRecorder() {
		rawData = new ArrayList<String>();
		summaryData = new ArrayList<String>();
	}

	public static DataRecorder getInstance() {
		return instance;
	}

	public void recordRaw(String record) {
		Log.d(record);
		rawData.add(record);
	}

	public void recordPerAWSummary(String record) {
		Log.d("Summary: " + record);
		summaryData.add(record);
	}

	public String niceTitleOutput() {
		return User.name + "'s statistics";
	}
	
	public String niceContentOutput() {

        StringBuilder sb = new StringBuilder(18);

        sb.append("Summary data:\n");
        
        for (String string : summaryData) {
			sb.append(string);
			sb.append("\n");
		}

        sb.append("\nRaw data:\n");
        
        for (String string : rawData) {
			sb.append(string);
			sb.append("\n");
		}
        
        return sb.toString();
	}
	
	public void clear() {
		rawData.clear();
		summaryData.clear();
	}

	public void email(Activity activity) {
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		
		String recipients[] = { "\"Zhao Han\" <cx.chenghai@gmail.com>", "\"Shuo Dong\" <shuodo@gmail.com>" };
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "COMP4020 - " + niceTitleOutput());
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "\n\n" + niceContentOutput()); // 2 lines used to type subjective observation
		
		activity.startActivity(emailIntent);
	}
}

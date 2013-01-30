package hci2.group5.a1.util;

import hci2.group5.a1.specs.User;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;

public class DataRecorder {
	
	private ArrayList<String> rawData;
	private ArrayList<String> avgData;
	

	private static DataRecorder instance = new DataRecorder();

	private DataRecorder() {
		rawData = new ArrayList<String>();
		avgData = new ArrayList<String>();
	}

	public static DataRecorder getInstance() {
		return instance;
	}

	public void recordRaw(String record) {
		System.out.println(record);
		rawData.add(record);
	}

	public void recordAvg(String record) {
		System.out.println(record);
		avgData.add(record);
	}

	public String niceTitleOutput() {
		return User.name + "'s statistics";
	}
	
	public String niceContentOutput() {

        StringBuilder sb = new StringBuilder(18);

        sb.append("Average data:\n");
        
        for (String string : avgData) {
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
		avgData.clear();
	}

	public void email(Activity activity) {
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		
		String recipients[] = { "\"Zhao Han\" <cx.chenghai@gmail.com>", "\"Shuo Dong\" <shuodo@gmail.com>" };
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "COMP4020 - " + niceTitleOutput());
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, niceContentOutput());
		
		activity.startActivity(emailIntent);
	}
}

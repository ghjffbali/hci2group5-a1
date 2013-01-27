package hci2.group5.a1.main;

import java.util.ArrayList;

public class DataRecorder {

	private static DataRecorder instance = new DataRecorder();

	private String name;
	private ArrayList<String> data;

	private DataRecorder() {
		data = new ArrayList<String>();
	}

	public static DataRecorder getInstance() {
		return instance;
	}

	public void recordName(String fullname) {
		System.out.println("Name: " + fullname);
		name = fullname;
	}

	public void record(String record) {
		System.out.println(record);
		data.add(record);
	}

	public String niceTitleOutput() {
		return name + "'s statistics";
	}
	
	public String niceContentOutput() {

        StringBuilder sb = new StringBuilder(18);
        
        for (String string : data) {
			sb.append(string);
			sb.append("\n");
		}
        
        return sb.toString();
	}
	
	public void saveToFile() {
		
	}

	public void clear() {
		data.clear();
	}
}

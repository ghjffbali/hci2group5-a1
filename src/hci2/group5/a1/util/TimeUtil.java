package hci2.group5.a1.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtil {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss S");;
	
	public static String currTime() {
    	return dateFormat.format(Calendar.getInstance().getTime());
    }
}

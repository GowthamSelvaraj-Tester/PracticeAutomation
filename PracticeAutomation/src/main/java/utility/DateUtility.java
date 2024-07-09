package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {
	//Creating an method to get the current date 
		public static String getStringDate(String dateFormat) {
			//Creates a SimpleDateFormat object to define the format for date and time representation.
			SimpleDateFormat date = new SimpleDateFormat(dateFormat);
			//Retrieves the current date and time using the Date class.
			Date localdate = new Date();
			//Returns the formatted date and time as a string.
			return date.format(localdate).toString();
		}
}

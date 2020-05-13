package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private DateUtils() {
	}

	public static String convertDateToString(String date, String srcFormat, String desFormat) {
		try {
			SimpleDateFormat sourceFormat = new SimpleDateFormat(srcFormat);
			SimpleDateFormat destinationFormat = new SimpleDateFormat(desFormat);
			Date dateSrc = sourceFormat.parse(date);
			return destinationFormat.format(dateSrc);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

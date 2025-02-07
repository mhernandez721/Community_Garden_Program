package gardenProgram;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

	//Utility class for formatting and parsing dates

public class DateUtils {
    private static final String DATE_PATTERN = "yyyy-MM-dd"; // Expected date format
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);

    //Converts string into date object
    public static Date stringToDate(String text) throws ParseException {
        return dateFormatter.parse(text);
    }

    //Converts date object into string
    public static String dateToString(Date date) {
        return dateFormatter.format(date);
    }
}

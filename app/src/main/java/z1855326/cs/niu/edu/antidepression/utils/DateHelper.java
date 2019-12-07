package z1855326.cs.niu.edu.antidepression.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateHelper {
    //code to create singleton class which is a design pattern to avoid redundant memory storage
    private static DateHelper ourInstance = new DateHelper();
    public static DateHelper getInstance() {
        return ourInstance;
    }

    public String convertDate(String date) { // convert date to format "yyyy-MM-dd"
        date = date.substring(0, Math.min(date.length(), 10));

        DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
        try {
            parsedDate = (Date) parser.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        return formatter.format(parsedDate);
    }

    public Long getDateInMilliSeconds(String dateInString) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateInString.substring(0, Math.min(dateInString.length(), 10)));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String currentTimestamp() { //get current time
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return s.format(new Date());
    }
}

package src.project2.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * DateFunctions provides some static methods that are useful in
 * processing and handling dates
 */

public class DateUtil{


    /* ********************** Date Parsers ************************* */

    /**
     * Returns a string representation Today's Date in the format yyyy-MM-DD.
     * MM   - 2 digit representation of the Month of the year eg, 02
     * dd   - 2 digit representation of the day of the Month, eg, 09
     * yyyy - 4 digit representation year, eg 1982
     *
     * @return a string representation of the of today's date.
     */
    public static String today(){
        Calendar today  = Calendar.getInstance();
        today.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                  today.get(Calendar.DAY_OF_MONTH), 0, 0, 1);

        String dateStr = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.ENGLISH).format(
                    Calendar.getInstance().getTime());
        return dateStr;
    }

    /**
     * Returns a date corresponding to n days before today's date
     * Please note, if n is negative the result will be n days after today
     *
     * @param numOfDays the number of days before today
     *
     * @return          the date corresponding to numOfDays before today.
     */
    public static Date dateBeforeToday(int numOfDays){
        Calendar today  = Calendar.getInstance();

        today.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH) ,
                  today.get(Calendar.DAY_OF_MONTH) - numOfDays, 0, 0, 0);

        return today.getTime();
    }

    /**
     * Returns an integer representation of the current year
     *
     * @return          an integer representation of the current year
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * Returns a date corresponding to n days after the given date
     * Please note, if n is a negative number the result will be n days before the given date
     *
     * @param date      the subject date that you want to project from
     * @param numOfDays the number of days after the given date
     *
     * @return          the date corresponding to numOfDays before today.
     */
    public static Date dateAfterDate(Date date, int numOfDays) {
        return new Date(date.getTime()+ (1000 * 60 * 60 * 24 * numOfDays));
    }

    /**
     * Returns a Date object corresponding to the input format specified
     * A default format is yyyy-MM-dd. An overloaded version of this method
     * provides handling for that format.
     *
     * @param dateStr   a string representation of the date to be converted
     * @param format    the format that the input date string is in
     *
     * MM   - 2 digit representation of the Month of the year eg, 02
     * dd   - 2 digit representation of the day of the Month, eg, 09
     * yyyy - 4 digit representation year, eg 1982
     * 
     * @return          the parsed date object corresponding to the given date string.
     * @see             java.text.SimpleDateFormat
     */
    public static Date stringToDate(String dateStr, String format) {
        try {
            Date date = new SimpleDateFormat(format,
                                             Locale.ENGLISH).parse(dateStr);
            return date;

        } catch (java.text.ParseException e) {
            
            //e.printStackTrace();
        	
            return null;
        }
    }

    public static Date stringToDate(String dateStr) {
        return stringToDate(dateStr, "yyyy-MM-dd");
    }

    /**
     * Returns a date corresponding to n days after the given date
     * Please note, if n is a negative number the result will be n days before the given date
     *
     * @param date      the date to be converted to a string representation
     * @param format    the format that the date string is to be converted to
     *
     * @return          the formatted string corresponding to the given date object.
     * @see             java.text.SimpleDateFormat
     */
    public static String dateToString(Date date, String format){
        String dateStr = new SimpleDateFormat(format, Locale.ENGLISH).format(date);

        return dateStr;
    }

    /**
     * Returns an integer counting the number of days between two dates
     * The earlier date should be specified first
     *
     * @param dateFrom  the start date for the calculation range
     * @param dateTo    end date for the calculation range
     *
     * @return          the number of days between the two dates
     *
     */
    public static int dateDiffDays(Date dateFrom, Date dateTo){
        long timeTo = dateTo.getTime(),
             timeFrom = dateFrom.getTime();
        int diff = (int)Math.floor((timeTo - timeFrom)/(1000 * 60 * 60 * 24));
        return diff;
    }

    /**
     * Returns an integer counting the number of hours between two dates
     * The earlier date should be specified first
     *
     * @param dateFrom  the start date for the calculation range
     * @param dateTo    end date for the calculation range
     *
     * @return          the number of days between the two dates
     *
     */
    public static int dateDiffHours(Date dateFrom, Date dateTo){
        long timeTo = dateTo.getTime(),
             timeFrom = dateFrom.getTime();
        int diff = (int)Math.ceil((timeTo - timeFrom)/(60*60*1000));
        return diff;
    }
    
    public static String getCurrentTime(){
    		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    		Date date = new Date();
    	//System.out.println(dateFormat.format(cal)); //2016/11/16 12:08:43
    		return dateFormat.format(date); 
    		
    }
    
}
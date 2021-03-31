package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
    public static String getFormattedCurrentDate(String datePattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        return simpleDateFormat.format(new Date());
    }

    /**
     * Return the date in the past/future
     * @param datePattern set date pattern like dd/mm/YYYY or mm.dd.YYYY
     * @param countDay to get the date in the future set the countDay in positive value like "10", in the past - negative "-10"
     */
    public static String getDate (String datePattern, Integer countDay) {
        String dt = getFormattedCurrentDate(datePattern);
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, countDay);  // number of days to add
        return sdf.format(c.getTime());  // dt is now the new date
    }
}

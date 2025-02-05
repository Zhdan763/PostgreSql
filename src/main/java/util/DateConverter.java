package util;

import exception.DateConverterException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateConverter {

    public Timestamp stringToTimeStamp(String dateString) throws DateConverterException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
        java.util.Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Error! Class: " + DateConverter.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DateConverterException("Unable to convert date because: " + e);
        }
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;

    }


}

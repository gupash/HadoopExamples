package org.ashish.pig;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 07/21/2015 3:13 AM
 */

public class DateFormatter extends EvalFunc<String> {

    @Override
    public String exec(Tuple input) throws IOException {

        if (input == null && input.size() == 0) {
            return null;
        }
        try {
            String dtime = (String) input.get(0);
            String parseDate[] = dtime.split("/");
            String tmpDate[] = parseDate[2].split(" ");
            String parsedDate = tmpDate[0]+"-"+parseDate[0]+"-"+parseDate[1]+" "+tmpDate[1]+" "+tmpDate[2];
            DateFormat customDate = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
            Date dt = customDate.parse(parsedDate);
            long timedt = dt.getTime();
            return String.valueOf(timedt);
        } catch (ParseException ex) {
            return null;
        }
    }
}

package org.ashish.pig;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverter extends EvalFunc<String>{

    @Override
    public String exec(Tuple var1) throws IOException {
        String var2 = extractStringFromTuple(var1);
        return this.convertToDate(var2);
    }

    public static String extractStringFromTuple(Tuple var0) throws IOException {
        if(var0 != null && var0.size() > 0) {
            String var1;

            try {
                var1 = (String)var0.get(0);
                return var1;
            } catch (Exception var3) {
                throw new IOException("Failed to cast the input tuple to a string. Verify the data type of the tuple being passed.", var3);
            }
        } else {
            throw new IOException("Invalid input tuple");
        }
    }

    public static boolean isNull(String var0) {
        return var0 == null || var0.length() <= 0;
    }

    private String convertToDate(String var1) throws IOException {
        String var2;
        if(isNull(var1)) {
            var2 = "1900-01-01:00";
        } else {
            try {
                var2 = (new SimpleDateFormat("yyyy-MM-dd:HH")).format(new Date(Long.parseLong(var1)));
            } catch (Exception var4) {
                var2 = "1900-01-01:00";
            }
        }

        return var2;
    }
}

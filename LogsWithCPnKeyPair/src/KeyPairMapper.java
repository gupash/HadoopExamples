
import org.apache.commons.lang3.EnumUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class KeyPairMapper extends Mapper<LongWritable, Text, IpAccessCode, Text>{



    /**
     * Input Sample : 10.223.157.186 - - [15/Jul/2009:14:58:59 -0700] "GET /favicon.ico HTTP/1.1" 404 209
     *
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString().trim();
        String words[] = line.split(" ");

        if(words.length > 3){
            String ip = words[0];
            String accessCode = words[8];

            String dt[] = words[3].split("/");

            if(dt.length > 1){
                String month = dt[1];
                if(EnumUtils.isValidEnum(MyMonths.class, month.toUpperCase())){
                    context.write(new IpAccessCode(ip, accessCode), new Text(month));
                }
            }
        }
    }
}

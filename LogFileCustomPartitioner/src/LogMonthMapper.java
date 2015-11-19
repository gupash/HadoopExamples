import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogMonthMapper extends Mapper<LongWritable, Text, Text, Text> {

    public static List<String> months = new ArrayList<>(Arrays.asList("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"));

    /**
     * Example input line:
     * 96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] "GET /cat.jpg HTTP/1.1" 200 12433
     *
     */

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String word[] = line.split(" ");
        if(word.length > 3){
            String ip = word[0];
            String temp[] = word[3].split("/");
            if(temp.length > 1) {
                String month = temp[1];
                if(months.contains(month))
                    context.write(new Text(ip), new Text(month));
            }
        }

    }
}

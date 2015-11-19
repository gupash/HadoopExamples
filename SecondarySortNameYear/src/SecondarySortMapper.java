import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SecondarySortMapper extends Mapper<LongWritable, Text, StringPair, Text> {

    /**
     * Input Data : Ashish Gupta 1988
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString().trim();

        String[] words = line.split(" ");

        if(words != null && words.length > 0){
            context.write(new StringPair(words[0], words[2]), new Text(line));
        }
    }
}

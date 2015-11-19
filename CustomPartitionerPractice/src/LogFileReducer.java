import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class LogFileReducer extends Reducer<Text, Text, Text, IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        int counter  = 0;

        for (@SuppressWarnings("value")Text value : values) {
            counter++;
        }

        context.write(key, new IntWritable(counter));
    }
}

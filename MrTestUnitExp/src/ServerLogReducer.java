import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ServerLogReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

    @Override
    public void reduce(Text key, Iterable<IntWritable> value, Context context) throws IOException, InterruptedException{

        int ipCount = 0;

        for (IntWritable counter : value) {
            ipCount += counter.get();
        }

        context.write(key, new IntWritable(ipCount));
    }
}
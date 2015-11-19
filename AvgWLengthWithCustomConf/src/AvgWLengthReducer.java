import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AvgWLengthReducer extends Reducer<Text, LongWritable, Text, DoubleWritable> {

    @Override
    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        long sum=0;
        long counter=0;

        for (LongWritable value : values) {

            sum += value.get();
            counter++;
        }
        context.write(key, new DoubleWritable((double)sum/counter));
    }
}

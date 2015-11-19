import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String words[] = line.split("\\W+");
        LongWritable count = new LongWritable(1);
        for (String word : words) {
            if(word != null || !word.equals("")){
                context.write(new Text(word), count);
            }
        }

    }
}

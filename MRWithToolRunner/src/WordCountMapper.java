import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
        String line = value.toString();
        IntWritable iw = new IntWritable(1);
        for(String word : line.split("\\W+")){
            if(word.length() > 0 && !word.equals("") && !word.equals(" ")){
                context.write(new Text(word), iw);
            }
        }
    }
}

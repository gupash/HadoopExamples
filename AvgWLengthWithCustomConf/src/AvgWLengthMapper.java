import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AvgWLengthMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    boolean caseSensitive = true;

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();

        String words[] = line.split("\\W+");

        for (String word : words) {
            if(word.length() > 0) {

                String letter;
                if (caseSensitive) {
                    letter = word.substring(0, 1);
                }else{
                    letter = word.substring(0, 1).toLowerCase();
                }

                context.write(new Text(letter), new LongWritable(word.length()));
            }
        }
    }

    @Override
    public void setup(Context context){

        Configuration conf = context.getConfiguration();
        caseSensitive = conf.getBoolean("caseSensitive", true);
    }
}

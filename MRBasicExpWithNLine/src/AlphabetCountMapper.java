import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AlphabetCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{

        String line = value.toString();
        IntWritable count = new IntWritable(1);
        for(String letter : line.split("")){
            if(letter.matches("[a-zA-Z]")){
                context.write(new Text(letter), count);
            }
        }
    }
}

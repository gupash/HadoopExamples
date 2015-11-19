import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerLogMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

    private Logger logger = Logger.getLogger(ServerLogMapper.class);

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{

        String line = value.toString();
        IntWritable counter = new IntWritable(1);
        Pattern regexp = Pattern.compile("^(\\d{1,3}\\.){3}\\d{1,3}");
        Matcher matcher = regexp.matcher(line);
        while (matcher.find()) {
            logger.info(matcher.group());
            context.write(new Text(matcher.group()), counter);
        }
    }
}

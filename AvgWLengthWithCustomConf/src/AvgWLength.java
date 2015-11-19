import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AvgWLength extends Configured implements Tool{

    public static void main(String[] args) throws Exception {

        int exitCode = ToolRunner.run(new Configuration(), new AvgWLength(), args);
        System.exit(exitCode);
    }

    @Override
    public int run(String[] args) throws Exception {

        if(args.length < 2){
            System.out.println("Usage : hadoop jar AvgWLength.jar <InputDir> <OutputDir>");
            return -1;
        }

        Job job = Job.getInstance(getConf());

        job.setJarByClass(AvgWLength.class);
        job.setJobName("Avg Word Length Using Tool Runner");

        job.setMapperClass(AvgWLengthMapper.class);
        job.setReducerClass(AvgWLengthReducer.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        boolean status = job.waitForCompletion(true);
        return status? 0 : 1;
    }
}

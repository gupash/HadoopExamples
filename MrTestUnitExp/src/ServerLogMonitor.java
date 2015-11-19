import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 Map reduce Program to calculate the no of hit requests from different IP's on a web server
*/

public class ServerLogMonitor extends Configured implements Tool{
    public static void main(String[] args) throws Exception {

        int exitCode = ToolRunner.run(new Configuration(), new ServerLogMonitor(), args);
        System.exit(exitCode);
    }

    @Override
    public int run(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Usage : hadoop jar Program.jar <MainClassName> INPUT_DIR OUTPUT_DIR");
            return -1;
        }

        Job job = Job.getInstance(getConf());

        job.setJarByClass(ServerLogMonitor.class);
        job.setJobName("Server Log Monitor");

        job.setMapperClass(ServerLogMapper.class);
        job.setReducerClass(ServerLogReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }
}

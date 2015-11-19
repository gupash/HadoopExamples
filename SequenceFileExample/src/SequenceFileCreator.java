import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class SequenceFileCreator extends Configured implements Tool{

    public static void main(String[] args) throws Exception {

        int exitCode = ToolRunner.run(new Configuration(), new SequenceFileCreator(), args);
        System.exit(exitCode);
    }

    @Override
    public int run(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Usage: CreateUncompressedSequenceFile <input dir> <output dir>");
            return -1;
        }

        Job job = Job.getInstance(getConf());
        job.setJarByClass(SequenceFileCreator.class);
        job.setJobName("Sequence File Creator");

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        job.setNumReduceTasks(0);

        boolean success = job.waitForCompletion(true);
        return success? 0 : 1;
    }
}

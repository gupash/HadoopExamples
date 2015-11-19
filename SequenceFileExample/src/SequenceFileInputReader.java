import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class SequenceFileInputReader extends Configured implements Tool {

    public static void main(String[] args) throws Exception {

        int exitCode = ToolRunner.run(new Configuration(), new SequenceFileInputReader(), args);
        System.exit(exitCode);
    }

    @Override
    public int run(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Usage : hadoop jar <Jarname.jar> <inputDir> <OutputDir>");
            System.exit(-1);
        }

        Job job = Job.getInstance(getConf());

        job.setJarByClass(SequenceFileInputReader.class);
        job.setJobName("SequenceFileInputReader");

        job.setInputFormatClass(SequenceFileInputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean exitcode = job.waitForCompletion(true);
        return exitcode ? 0 : 1;
    }
}
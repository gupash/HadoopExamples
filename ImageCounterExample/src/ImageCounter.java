import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ImageCounter {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length != 2) {
            System.out.println("Usage :hadoop jar ImageCounter.jar <INPUT Dir> <OUTPUT Dir>");
            System.exit(-1);
        }

        Job job = Job.getInstance();

        job.setJobName("Image Counter");
        job.setJarByClass(ImageCounter.class);

        job.setMapperClass(ImageCounterMapper.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean success = job.waitForCompletion(true);
        System.exit(success ? 0 : 1);
    }
}

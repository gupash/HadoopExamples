import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
    MapReduce Program to calculate the No of occurrences of different english alphabets in Input file
*/

public class AlphabetCount {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        if (args.length != 2) {
            System.out.println("Usage :hadoop jar <program>.jar <MainClass(Not Needed if added in Manifest)> <INPUT Dir> <OUTPUT Dir>");
            System.exit(-1);
        }

        /*Older way of getting job object, now deprecated*/
        @SuppressWarnings("deprecation")
        Job job = new Job(new Configuration());

        job.setJobName("Alphabet Count");
        job.setJarByClass(AlphabetCount.class);

        job.setMapperClass(AlphabetCountMapper.class);
        job.setReducerClass(AlphabetCountReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setInputFormatClass(NLineInputFormat.class);

        NLineInputFormat.setNumLinesPerSplit(job, 5);
        NLineInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean success = job.waitForCompletion(true);
        System.exit(success ? 0 : 1);

    }
}

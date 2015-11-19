import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
    MapReduce Program to calculate the No of occurrences of different words in Input file
*/

public class WordCount extends Configured implements Tool {
    public static void main(String[] args) throws Exception{

        int exitCode = ToolRunner.run(new Configuration(), new WordCount(), args);
        System.exit(exitCode);
    }

    @Override
    public int run(String[] args) throws Exception {
        if(args.length != 2){
            System.out.println("Usage: AvgWordLength <input dir> <output dir>\n");
            return -1;
        }

        Path partitionFile = new Path("PartitionFileLoc/mypartitionfile.lst");

        InputSampler.Sampler<Text, IntWritable> sampler = new InputSampler.RandomSampler<>(0.5, 300, 4);

        Job job = Job.getInstance(getConf());

        job.setJarByClass(WordCount.class);
        job.setJobName("Word Count");

        TextInputFormat.setInputPaths(job, new Path(args[0]));
        TextOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setNumReduceTasks(3);

        TotalOrderPartitioner.setPartitionFile(job.getConfiguration(), partitionFile);

        job.setPartitionerClass(TotalOrderPartitioner.class);

        InputSampler.writePartitionFile(job, sampler);

        boolean success = job.waitForCompletion(true);
        return success? 0 : 1;
    }
}

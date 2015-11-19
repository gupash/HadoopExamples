import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class SecondarySortDriver extends Configured implements Tool{

    public static void main(String[] args) throws Exception {

        int exitCode = ToolRunner.run(new Configuration(), new SecondarySortDriver(), args);
        System.exit(exitCode);
    }

    @Override
    public int run(String[] args) throws Exception {

        if(args.length != 2){
            System.out.println("Usage: AvgWordLength <input dir> <output dir>\n");
            return -1;
        }

        Job job = Job.getInstance(getConf(), "Secondary Sort");

        job.setJarByClass(SecondarySortDriver.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(SecondarySortMapper.class);
        job.setReducerClass(SecondarySortReducer.class);

        job.setMapOutputKeyClass(StringPair.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setGroupingComparatorClass(NameComparator.class);

        /**
         * If you don't override the compareTo method in composite key class, then the sortComparator is required.
         * Also if sortComparator is given then it by default overrides the compareTo in key class.
         */
        //job.setSortComparatorClass(NameYearComparator.class);

        /**
         * If the no of reducers are more than 1, then a Partitioner also needs to be written
         *
         * job.setNumReduceTasks(2);
         **/

        return job.waitForCompletion(true)? 0 : 1;
    }
}

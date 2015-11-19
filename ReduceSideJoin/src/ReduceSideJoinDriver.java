import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ReduceSideJoinDriver extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {

        if(args.length != 3){
            System.out.println("Usage: "+getClass().getSimpleName()+"<input dir1> <input dir2> <output dir>");
            return -1;
        }

        Job job = Job.getInstance(getConf(), "Map Side Join");
        job.setJarByClass(ReduceSideJoinDriver.class);

        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, TransactionMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, CustomerMapper.class);
        FileOutputFormat.setOutputPath(job, new Path(args[2]));

        job.setMapOutputKeyClass(TextPair.class);
        job.setMapOutputValueClass(Text.class);

        job.setReducerClass(EmpTxnReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setGroupingComparatorClass(GroupComparator.class);

        return job.waitForCompletion(true)? 0 : 1;
    }

    public static void main(String[] args) throws Exception {

        System.exit(ToolRunner.run(new Configuration(), new ReduceSideJoinDriver(), args));
    }
}

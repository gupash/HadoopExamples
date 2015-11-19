import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.net.URI;

public class EmpDeptDriver extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {

        if(args.length != 2){
            System.out.println("Usage: "+getClass().getSimpleName()+"<input dir1> <output dir>");
            return -1;
        }

        Job job = Job.getInstance(getConf(), "Map Side Join");
        job.setJarByClass(EmpDeptDriver.class);

        Path empPath = new Path(args[0]);
        String deptPath = "/Users/ashish/Desktop/SampleData/DeptDataMap.txt";

        FileInputFormat.setInputPaths(job, empPath);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.addCacheFile(new URI(deptPath));

        job.setMapperClass(EmployeeMapper.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setNumReduceTasks(0);

        return job.waitForCompletion(true)? 0 : 1;
    }

    public static void main(String[] args) throws Exception {

        System.exit(ToolRunner.run(new Configuration(), new EmpDeptDriver(), args));
    }
}

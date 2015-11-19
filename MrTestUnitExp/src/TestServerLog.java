import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestServerLog {
    MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
    ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
    MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

    @Before
    public void setUp() {

        ServerLogMapper mapper = new ServerLogMapper();
        ServerLogReducer reducer = new ServerLogReducer();

        mapDriver = MapDriver.newMapDriver(mapper);
        reduceDriver = ReduceDriver.newReduceDriver(reducer);
        mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }

    @Test
    public void testMapper() throws IOException {

        mapDriver.withInput(new LongWritable(), new Text("612.57.72.653   03/Jun/2012:09:12:23"));
        mapDriver.withOutput(new Text("612.57.72.653"), new IntWritable(1));
        mapDriver.runTest();
    }

    @Test
    public void testReduce() throws IOException {

        List<IntWritable> values = new ArrayList();
        values.add(new IntWritable(1));
        reduceDriver.withInput(new Text("612.57.72.653"), values);
        reduceDriver.withOutput(new Text("612.57.72.653"), new IntWritable(1));
        reduceDriver.runTest();
    }

    @Test
    public void testMapReduce() throws IOException {

        mapReduceDriver.withInput(new LongWritable(), new Text("612.57.72.653   03/Jun/2012:09:12:23"));
        mapReduceDriver.withOutput(new Text("612.57.72.653"), new IntWritable(1));
        mapReduceDriver.runTest();
    }
}

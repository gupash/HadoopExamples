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

public class TestWordCount {

    MapDriver<LongWritable, Text, Text, LongWritable> mapDriver;
    ReduceDriver<Text, LongWritable, Text, LongWritable> reduceDriver;
    MapReduceDriver<LongWritable, Text, Text, LongWritable, Text, LongWritable> mapReduceDriver;

    @Before
    public void setUp() {

        WordCountMapper mapper = new WordCountMapper();
        WordCountReducer reducer = new WordCountReducer();

        mapDriver = MapDriver.newMapDriver(mapper);
        reduceDriver = ReduceDriver.newReduceDriver(reducer);
        mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }

    @Test
    public void TestMapper() throws IOException {

        mapDriver.withInput(new LongWritable(), new Text("I am here am"));
        mapDriver.withOutput(new Text("I"), new LongWritable(1));
        mapDriver.withOutput(new Text("am"), new LongWritable(1));
        mapDriver.withOutput(new Text("here"), new LongWritable(1));
        mapDriver.withOutput(new Text("am"), new LongWritable(1));
        mapDriver.runTest();
    }

    @Test
    public void TestReducer() throws IOException {

        List<LongWritable> values = new ArrayList<LongWritable>();
        values.add(new LongWritable(1));
        values.add(new LongWritable(1));
        reduceDriver.withInput(new Text("Hi"), values);
        reduceDriver.withOutput(new Text("Hi"), new LongWritable(2));
        reduceDriver.runTest();
    }

    @Test
    public void TestMapReduce() throws IOException {

        mapReduceDriver.withInput(new LongWritable(), new Text("I am here am"));
        mapReduceDriver.withOutput(new Text("I"), new LongWritable(1));
        mapReduceDriver.withOutput(new Text("am"), new LongWritable(2));
        mapReduceDriver.withOutput(new Text("here"), new LongWritable(1));
        mapReduceDriver.runTest();
    }

}

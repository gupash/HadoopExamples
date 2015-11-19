import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CustomerMapper extends Mapper<LongWritable, Text, TextPair, Text>{

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString().trim();
        String[] custData = line.split("\\t");

        if(custData != null && custData.length > 2){
            System.out.println("Customer Mapper : Customer ID : "+custData[0]+" Customer Name --> "+custData[1]+" , Address --> "+custData[2]);
            context.write(new TextPair(custData[0], "cust"), new Text(custData[1]+" "+custData[2]));
        }
    }
}

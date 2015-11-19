import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TransactionMapper extends Mapper<LongWritable, Text, TextPair, Text> {

    /**
     *  Input Emp Data  : 1 Ashish 201
     *  Input Dept Data : 201 Entertainment Noida
     */

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString().trim();
        String[] txnData = line.split("\\t");

        if(txnData != null && txnData.length > 3){

            System.out.println("TransactionMapper : Customer ID --> "+txnData[1]+" , Amount --> "+txnData[2]);
            context.write(new TextPair(txnData[1], "txn"), new Text(txnData[2]));
        }
    }
}

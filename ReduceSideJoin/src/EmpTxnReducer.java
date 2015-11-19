import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class EmpTxnReducer extends Reducer<TextPair, Text, Text, Text> {

    int totAmt = 0;
    int totTrans = 0;
    String flag;

    @Override
    protected void reduce(TextPair key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        for (Text value : values) {
            System.out.println("key = [" + key.toString() + "], values = [" + value + "]");
            if(key.getType().equals("cust")){
                context.write(new Text(key.getCustId()), new Text(value.toString()+"\t"+totAmt+"\t"+totTrans));
                totAmt = 0; totTrans = 0;
            }
            if(key.getType().equals("txn")){
                totAmt += Integer.parseInt(value.toString());
                totTrans++;
            }
        }
    }
}

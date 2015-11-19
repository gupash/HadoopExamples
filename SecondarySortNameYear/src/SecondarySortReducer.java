import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SecondarySortReducer extends Reducer<StringPair, Text, Text, Text> {

    @Override
    protected void reduce(StringPair key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        context.write(new Text(""), new Text(values.iterator().next()));
    }
}

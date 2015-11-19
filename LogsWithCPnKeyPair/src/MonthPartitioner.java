import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

public class MonthPartitioner extends Partitioner<Text, Text> implements Configurable {

    private Configuration configuration;

    HashMap<String, Integer> months = new HashMap<>();

    @Override
    public int getPartition(Text key, Text value, int numPartitions) {

        return months.get(value.toString());
    }

    @Override
    public void setConf(Configuration configuration) {

        this.configuration = configuration;

        months.put(MyMonths.JAN.getMonth(), 0);
        months.put(MyMonths.FEB.getMonth(), 1);
        months.put(MyMonths.MAR.getMonth(), 2);
        months.put(MyMonths.APR.getMonth(), 3);
        months.put(MyMonths.MAY.getMonth(), 4);
        months.put(MyMonths.JUN.getMonth(), 5);
        months.put(MyMonths.JUL.getMonth(), 6);
        months.put(MyMonths.AUG.getMonth(), 7);
        months.put(MyMonths.SEP.getMonth(), 8);
        months.put(MyMonths.OCT.getMonth(), 9);
        months.put(MyMonths.NOV.getMonth(), 10);
        months.put(MyMonths.DEC.getMonth(), 11);
    }

    @Override
    public Configuration getConf() {
        return configuration;
    }
}

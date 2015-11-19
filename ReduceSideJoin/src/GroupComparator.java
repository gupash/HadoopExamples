import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupComparator extends WritableComparator{

    public GroupComparator() {
        super(TextPair.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {

        TextPair pair1 = (TextPair) a;
        TextPair pair2 = (TextPair) b;

        int ret = pair1.getCustId().compareTo(pair2.getCustId());
        return ret;
    }
}
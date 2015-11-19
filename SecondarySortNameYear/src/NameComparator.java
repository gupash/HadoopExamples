import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class NameComparator extends WritableComparator {


    public NameComparator() {
        super(StringPair.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {

        StringPair pair1 = (StringPair) a;
        StringPair pair2 = (StringPair) b;

        return pair1.getFname().compareTo(pair2.getFname());
    }

   /* @Override
    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {

        DataInput stream1 = new DataInputStream(new ByteArrayInputStream(b1, s1, l1));
        DataInput stream2 = new DataInputStream(new ByteArrayInputStream(b2, s2, l2));

        StringPair pair1 = new StringPair();
        StringPair pair2 = new StringPair();

        try {
            pair1.readFields(stream1);
            pair2.readFields(stream2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compare(pair1, pair2);
    }*/
}

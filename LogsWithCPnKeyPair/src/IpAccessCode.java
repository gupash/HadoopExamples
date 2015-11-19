import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IpAccessCode implements WritableComparable<IpAccessCode> {

    String left;
    String right;

    public IpAccessCode() {
    }

    public IpAccessCode(String left, String right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IpAccessCode that = (IpAccessCode) o;

        if (!left.equals(that.left)) return false;
        return right.equals(that.right);

    }

    @Override
    public String toString() {
        return left+"\t"+right;
    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }

    @Override
    public int compareTo(IpAccessCode o) {

        int ret = left.compareTo(o.left);
        if(ret == 0){
            return right.compareTo(o.right);
        }
        return ret;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(left);
        dataOutput.writeUTF(right);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

        left = dataInput.readUTF();
        right = dataInput.readUTF();
    }
}

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TextPair implements WritableComparable<TextPair> {

    String custId;
    String type;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TextPair(String custId, String type) {
        this.custId = custId;
        this.type = type;
    }

    public TextPair() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextPair textPair = (TextPair) o;

        if (custId != null ? !custId.equals(textPair.custId) : textPair.custId != null) return false;
        return !(type != null ? !type.equals(textPair.type) : textPair.type != null);

    }

    @Override
    public int hashCode() {
        int result = custId.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public int compareTo(TextPair o) {

        int check = this.custId.compareTo(o.custId);
        if(check == 0) {
            return -1 * this.type.compareTo(o.type);
        }
        return check;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(custId);
        out.writeUTF(type);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        custId = in.readUTF();
        type = in.readUTF();
    }

    @Override
    public String toString() {
        return "("+custId+","+type+")";
    }
}

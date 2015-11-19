import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StringPair implements WritableComparable<StringPair>{

    private String fname;
    private String year;

    public String getFname() {
        return fname;
    }

    public String getYear() {
        return year;
    }

    public StringPair(String fname, String year) {
        this.fname = fname;
        this.year = year;
    }

    public StringPair() {
    }

    @Override
    public int compareTo(StringPair o) {
        int ret = this.fname.compareTo(o.fname);
        if(ret == 0){
            return -1 * this.year.compareTo(o.year);
        }
        return ret;
    }


    @Override
    public void write(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(fname);
        dataOutput.writeUTF(year);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

        fname = dataInput.readUTF();
        year = dataInput.readUTF();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringPair that = (StringPair) o;

        if (fname != null ? !fname.equals(that.fname) : that.fname != null) return false;
        return !(year != null ? !year.equals(that.year) : that.year != null);

    }

    @Override
    public int hashCode() {
        int result = fname != null ? fname.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "("+fname+","+year+")";
    }
}

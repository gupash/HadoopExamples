package streams;

import java.util.Comparator;

public class StringRevComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return -1 * o1.compareTo(o2);
    }
}

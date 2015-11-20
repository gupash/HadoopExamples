package streams;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StreamingCont {

    public static void main(String[] args) {

        List<Student> students = new Student().generateData();

         Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                boolean ret = s1.getAge() == s2.getAge();
                if(ret){
                    return 0;
                }
                else{
                    ret = s1.getAge() > s2.getAge();
                }
                return ret ? 1 : -1;
            }
        });

    }
}

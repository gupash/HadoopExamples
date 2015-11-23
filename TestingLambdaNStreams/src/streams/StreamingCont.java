package streams;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        List<Integer> srtStu = students.stream()
                .filter(t -> t.getAge() < 28)
                .sorted(Comparator.comparing(Student::getAge).reversed())
                .map(Student::getAge)
                .collect(Collectors.toList());

        System.out.println("--------- Printing Array of Student Ages Below 28 ----------");
        srtStu.forEach(System.out::println);

        System.out.println("\n--------- Sum of all ages below 28, Method 1 --------");
        System.out.println(srtStu.stream().reduce(0, (a, b) -> a + b));

        System.out.println("\n--------- Sum of all ages below 28, Method 2 --------");
        System.out.println(srtStu.stream().mapToInt(Integer::intValue).sum());

        System.out.println("\n--------- Min of all ages below 28, Method 1 --------");
        System.out.println(srtStu.stream().mapToInt(Integer::intValue).min().getAsInt());

        System.out.println("\n--------- Average of all ages below 28, Method 1 --------");
        System.out.println(srtStu.stream().collect(Collectors.averagingInt(p -> p.intValue())));

        System.out.println("\n--------- Average of all ages below 28, Method 2 --------");
        System.out.println(srtStu.stream().mapToInt(x -> x).average().getAsDouble());

        System.out.println("\n---------- Printing stream of numbers with difference of 10 -----------");
        Stream<Integer> intStream = Stream.iterate(0, n -> n + 10).limit(10);
        intStream.forEach(System.out::println);

        System.out.println("\n---------- Grouping Students by Type -----------");
        Map<String, List<String>> stuMap = students.stream().collect(Collectors.groupingBy(Student::getType, Collectors.mapping(Student::getName, Collectors.toList())));
        stuMap.forEach((k, v) -> System.out.println("Key : "+k+", value :"+v));
    }
}

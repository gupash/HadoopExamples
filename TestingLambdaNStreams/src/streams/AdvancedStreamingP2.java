package streams;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class AdvancedStreamingP2 {

    public static void main(String[] args) {

        List<Student> studentList = Student.generateData();

        System.out.println("\n--------- Average of all ages below 28, Method 1 --------");
        System.out.println(studentList.stream().filter(x -> x.getAge() < 28).collect(Collectors.averagingInt(Student::getAge)));

        System.out.println("\n--------- Printing smallest Student ---------");
        Optional minAge = studentList.stream().collect(minBy(comparing(Student::getAge)));
        System.out.println(minAge.get());

        System.out.println("\n--------- Sum of all ages below 28, Method 3 --------");
        System.out.println(studentList.stream().collect(reducing(0, Student::getAge, Integer::sum)));

        System.out.println("\n---------- Grouping Students by Type -----------");
        Map<String, List<Student>> stuMap = studentList.stream().collect(groupingBy(Student::getType));
        stuMap.forEach((k, v) -> System.out.println("Key : "+k+", Value :"+v));

        System.out.println("\n---------- Grouping Students by Type and Sum Age -----------");
        Map <String, Integer> stuSum = studentList.stream().collect(groupingBy(Student::getType, summingInt(Student::getAge)));
        stuSum.forEach((k, v) -> System.out.println("Key : "+k+", Value :"+v));

        System.out.println("\n---------- Grouping Student Names by Type -----------");
        Map<String, List<String>> stuMap2 = studentList.stream().collect(groupingBy(Student::getType, mapping(Student::getName, Collectors.toList())));
        stuMap2.forEach((k, v) -> System.out.println("Key : "+k+", Value :"+v));

        //Showing use of collectingAndThen
        System.out.println("\n---------- Extracting Student Name with Max Age by Type -----------");
        Map<String, String> stuMax = studentList.stream()
                                                .collect(groupingBy(Student::getType, collectingAndThen(maxBy(comparing(Student::getAge)), s -> s.get().getName())));
        stuMax.forEach((k, v) -> System.out.println("Key : " + k + ", Value :" + v));
    }
}

package streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class AdvancedStreamingP1 {

    public static void main(String[] args) throws IOException {

        /* Displaying all distinct alphabets in a File */

        System.out.println("---------- Displaying all distinct alphabets in a File ------------");

        Files.lines(Paths.get("/Users/ashish/Desktop/SampleData/poems"))
                .map(line -> line.split("\\W+"))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);

        /* Counting all alphabets in a File */

        System.out.println("\n------------ Counting all alphabets in a Array Stream ------------");

        Stream<String> names = Stream.of("Ashishs", "Amit", "Aman", "Rahul", "Rohit", "Modi", "Aamir");

        Map<String, Long> letterCount = names.map(s -> s.split("\\W*?"))
                                             .flatMap(Arrays::stream)
                                             .collect(groupingBy(identity(), counting()));

        letterCount.forEach((k, v) -> System.out.println("Key : "+k+" , Value : "+v));
    }
}
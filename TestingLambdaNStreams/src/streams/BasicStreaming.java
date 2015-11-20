package streams;

import java.util.*;
import java.util.stream.Collectors;

public class BasicStreaming {

    static String name[] = {"Ashish", "Aman", "Amar", "Akhbar", "Anthony", "Ashok", "Aditya", "Ashwini"};

    static List<String> names = Arrays.asList(name);

    public static void main(String[] args) {

        List<String> st = new ArrayList<>();

        for (String name : names) {
            if(name.contains("sh"))
                st.add(name);
        }

        System.out.println("********* Print from normal method - Descending *********");

        Collections.sort(st, new StringRevComp());

        for (String s : st) {
            System.out.println(s);
        }

        System.out.println("\n\n********* Print Through Streams - Ascending **********");

        names.stream()
                .filter(t -> t.contains("sh"))
                .sorted(Comparator.comparing(String::toString))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}

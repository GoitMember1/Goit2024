package homeworkModule11;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Task2 {
    public static List<String> processStrings(List<String> inputStrings) {
        List<String> result = inputStrings.stream()
                .map(String::toUpperCase)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
        return result;
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ivan", "Peter", "Roman", "Oleg", "Oleksandr");
        List<String> processedStrings = processStrings(names);
        System.out.println(processedStrings);
    }
}

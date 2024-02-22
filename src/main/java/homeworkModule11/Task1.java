package homeworkModule11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task1 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ivan", "Peter", "Roman", "Oleg", "Oleksandr");
        String result = filterAndFormatNames(names);
        System.out.println(result);
    }

    public static String filterAndFormatNames(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(i -> i % 2 == 0) // фільтруємо непарні індекси
                .mapToObj(i -> (i + 1) + ". " + names.get(i))
                .collect(Collectors.joining(", "));
    }
}

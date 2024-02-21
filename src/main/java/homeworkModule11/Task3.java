package homeworkModule11;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Task3 {
    public static String sortAndFlatten(String[] inputArray) {
        // Розбиваємо кожен рядок на числа, сортуємо та об'єднуємо в рядок через кому
        String result = Arrays.stream(inputArray)
                .flatMap(s -> Arrays.stream(s.split(", ")))
                .map(Integer::parseInt)
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        return result;
    }

    public static void main(String[] args) {
        String[] inputArray = {"1, 2, 0", "4, 5"};
        String sortedNumbers = sortAndFlatten(inputArray);
        System.out.println(sortedNumbers);
    }
}

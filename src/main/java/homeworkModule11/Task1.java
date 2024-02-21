package homeworkModule11;

import java.util.Arrays;
import java.util.List;

public class Task1 {
    public static String formatNames(List<String> names) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < names.size(); i++) {
            if (i % 2 == 0) {
                result.append((i + 1)).append(". ").append(names.get(i)).append(", ");
            }
        }

        if (result.length() > 0) {
            result.delete(result.length() - 2, result.length());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ivan", "Peter", "Roman", "Oleg", "Oleksandr");
        String formattedNames = formatNames(names);
        System.out.println(formattedNames);
    }
}

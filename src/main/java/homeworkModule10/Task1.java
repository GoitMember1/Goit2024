package homeworkModule10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Task1 {

    public static void readAndPrintValidPhoneNumbers(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isValidPhoneNumber(line)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Паттерн для першого формату: (xxx) xxx-xxxx
        Pattern pattern1 = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}");

        // Паттерн для другого формату: xxx-xxx-xxxx
        Pattern pattern2 = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");

        // Використовуємо два Matcher для перевірки обох форматів
        Matcher matcher1 = pattern1.matcher(phoneNumber);
        Matcher matcher2 = pattern2.matcher(phoneNumber);

        // Повертаємо true, якщо хоча б один з форматів відповідає
        return matcher1.matches() || matcher2.matches();
    }
}
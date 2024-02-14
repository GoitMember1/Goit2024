package homeworkModule10;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainM10_2 {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("file2.txt"))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    // Пропускаємо перший рядок (заголовок)
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(" ");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);

                userList.add(new User(name, age));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Запис об'єктів в JSON файл
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(userList);

        try (FileWriter writer = new FileWriter("user.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package homeworkModule10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordFrequencyCounter {

    public static void main(String[] args) {
        try {
            Map<String, Integer> wordFrequencyMap = countWordFrequency("words.txt");
            printWordFrequency(wordFrequencyMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> countWordFrequency(String filePath) throws IOException {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
                }
            }
        }

        return wordFrequencyMap;
    }

    public static void printWordFrequency(Map<String, Integer> wordFrequencyMap) {
        wordFrequencyMap.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }
}

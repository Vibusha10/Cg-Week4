import java.io.*;
import java.util.*;

public class Program1 {

    public static Map<String, Integer> countWordFrequency(String fileName) throws IOException {
        Map<String, Integer> wordCountMap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("[^a-zA-Z]+");
            for (String word : words) {
                if (!word.isEmpty()) {
                    word = word.toLowerCase();
                    wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                }
            }
        }
        reader.close();
        return wordCountMap;
    }

    public static void main(String[] args) {
        try {
            String fileName = "input.txt";
            Map<String, Integer> wordCount = countWordFrequency(fileName);
            System.out.println(wordCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

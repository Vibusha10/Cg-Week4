import java.util.*;

public class Program2 {
    public static Map<String, Integer> getFrequency(List<String> items) {
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String item : items) {
            frequencyMap.put(item, frequencyMap.getOrDefault(item, 0) + 1);
        }

        return frequencyMap;
    }

    public static void main(String[] args) {
        List<String> input = Arrays.asList("apple", "banana", "apple", "orange");

        Map<String, Integer> frequency = getFrequency(input);
        System.out.println("Frequency: " + frequency);
    }
}

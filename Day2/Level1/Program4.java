import java.util.*;

public class Program4 {
    public static <T> List<T> removeDuplicates(List<T> list) {
        Set<T> seen = new LinkedHashSet<>();
        seen.addAll(list);
        return new ArrayList<>(seen);
    }

    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(3, 1, 2, 2, 3, 4);

        System.out.println("Original List: " + data);
        List<Integer> uniqueList = removeDuplicates(data);
        System.out.println("List after removing duplicates: " + uniqueList);
    }
}

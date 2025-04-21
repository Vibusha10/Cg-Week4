import java.util.HashSet;
import java.util.Set;

public class Program2 {
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(3);
        set2.add(4);
        set2.add(5);

        Set<Integer> result = symmetricDifference(set1, set2);

        System.out.println(result);
    }

    public static Set<Integer> symmetricDifference(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<>(set1);
        for (Integer elem : set2) {
            if (!result.add(elem)) {
                result.remove(elem);
            }
        }
        return result;
    }
}
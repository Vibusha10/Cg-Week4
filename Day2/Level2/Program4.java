import java.util.HashSet;
import java.util.Set;

public class Program4 {
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        set1.add(2);
        set1.add(3);

        set2.add(1);
        set2.add(2);
        set2.add(3);
        set2.add(4);

        boolean isSubset = set2.containsAll(set1);

        System.out.println(isSubset);
    }
}

import java.util.*;

public class Program3 {
    public static <T> void rotateLeft(List<T> list, int positions) {
        int size = list.size();
        if (size == 0 || positions <= 0) return;

        positions = positions % size;

        List<T> rotated = new ArrayList<>();
        rotated.addAll(list.subList(positions, size));
        rotated.addAll(list.subList(0, positions));

        for (int i = 0; i < size; i++) {
            list.set(i, rotated.get(i));
        }
    }

    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        int rotateBy = 2;

        System.out.println("Original List: " + data);
        rotateLeft(data, rotateBy);
        System.out.println("Rotated List: " + data);
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SplitInteger {

    public static List<Integer> splitInteger(int num, int parts) {
        int base = num / parts;
        int remainder = num % parts;

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < remainder; i++) {
            result.add(base + 1);
        }

        for (int i = remainder; i < parts; i++) {
            result.add(base);
        }
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        // Examples
        System.out.println(splitInteger(10, 5));  // Output: [2, 2, 2, 2, 2]
        System.out.println(splitInteger(20, 6));  // Output: [3, 3, 3, 3, 4, 4]
    }
}

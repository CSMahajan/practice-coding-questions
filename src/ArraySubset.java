import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArraySubset {
    /*
    Array Subset of another array

    Given two arrays: a1[0..n-1] of size n and a2[0..m-1] of size m. Task is to check whether a2[] is a subset of a1[] or not. Both the arrays can be sorted or unsorted.

    Example 1:

    Input:
    a1[] = {11, 1, 13, 21, 3, 7}
    a2[] = {11, 3, 7, 1}
    Output:
    Yes
    Explanation:
    a2[] is a subset of a1[]

    Note: Exact subset should be considered with at least count of duplicate elements of set 2 should be present in set 1
    Example 2:
    Input:
    a1[] = {11, 1, 13, 21, 3, 7}
    a2[] = {1, 3, 7, 1}
    Output: No
    Explanation: 1 is present twice in set 2 but only once in set 1*/

    public static String isSubset(long[] a1, long[] a2, long n, long m) {
        Map<Long, Integer> map1 = new HashMap<>();
        Map<Long, Integer> map2 = new HashMap<>();
        for (long i : a1) {
            map1.put(i, map1.getOrDefault(i, 0) + 1);
        }
        for (long i : a2) {
            map2.put(i, map2.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Long, Integer> entry : map2.entrySet()) {
            if (!map1.containsKey(entry.getKey()) || (map1.get(entry.getKey()) < entry.getValue())) {
                return "No";
            }
        }
        return "Yes";
    }

    /* -------------------- Other solutions --------------------*/

    public String isSubset1(long[] a1, long[] a2, long n, long m) {
        Map<Long, Integer> map = new HashMap<>();
        for (long i : a2) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (long i : a1) {
            if (map.containsKey(i))
                map.put(i, map.get(i) - 1);
        }
        for (Map.Entry<Long, Integer> e : map.entrySet()) {
            if (e.getValue() > 0)
                return "No";
        }
        return "Yes";
    }
    /* -------------------- Other solutions --------------------*/
    public String isSubset2(long[] a1, long[] a2, long n, long m) {
        Arrays.sort(a1);
        Arrays.sort(a2);
        int i = 0;
        for (int j = 0; j < n; j++) {
            if (a1[j] == a2[i]) {
                i++;
            }
            if (i == m) {
                return "Yes";
            }
        }
        return "No";
    }
}
import java.util.Arrays;

public class UncommonCharacters {

    /*
    Uncommon characters

    Given two strings A and B. Find the characters that are not common in the two strings.
    Note :- Return the string in sorted order.

    Example 1:
    Input:
    A = geeksforgeeks
    B = geeksquiz
    Output: fioqruz
    Explanation:
    The characters 'f', 'i', 'o', 'q', 'r', 'u','z' are either present in A or B, but not in both.*/

    public static String UncommonChars(String A, String B) {
        // code here
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        for (char x : A.toCharArray()) {
            arr1[x - 'a']++;
        }
        for (char x : B.toCharArray()) {
            arr2[x - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (arr1[i] == 0 && arr2[i] > 0) {
                sb.append((char) ('a' + i));
            } else if (arr1[i] > 0 && arr2[i] == 0) {
                sb.append((char) ('a' + i));
            }
        }
        if (sb.length() == 0) {
            return "-1";
        }
        char[] arr = sb.toString().toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}

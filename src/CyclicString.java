import java.util.Arrays;

public class CyclicString {


    public static int cyclicStringLength(String s) {
        char[] arr = s.toCharArray();
        for (int i = 1; i < arr.length; i++) {
            if (isSubArray(Arrays.copyOfRange(arr, 0, i), Arrays.copyOfRange(arr, i, arr.length))) {
                return i;
            }
        }
        return arr.length;
        // If no valid substring is found, return -1 or any other appropriate value.
    }

    public static boolean isSubArray(char[] sub, char[] candidate) {
        for (int i = 0; i < candidate.length; i++) {
            if (candidate[i] != sub[i % sub.length]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Examples
        System.out.println(cyclicStringLength("aadddaaa"));  // Output: 6
        System.out.println(cyclicStringLength("ababa"));      // Output: 2
    }

    public static String getMiddle( String str ) {
        int n = str.length();
        if(n%2!=0){
            return str.substring(n/2, n/2+1);
        }
        String middle = str.substring(n/2-1,n/2+1);
        return middle;
    }

    /*
    You're given a substring s of some cyclic string. What's the length of the smallest possible string that can be concatenated to itself many times to obtain this cyclic string?

Example
Fors = "cabca", the output should be 3

In this case "cabca" is a substring of a cycle string "abcabcabcabc..." that can be obtained by concatenating "abc" to itself. Thus, the answer is 3.
     */

    public static int cyclicString(String s) {
        int length = s.length();

        for (int i = 1; i <= length; i++) {
            if (length % i == 0) {
                String substring = s.substring(0, i);
                boolean isCyclic = true;

                for (int j = i; j < length; j += i) {
                    int end = Math.min(j + i, length);
                    if (!s.substring(j, end).equals(substring)) {
                        isCyclic = false;
                        break;
                    }
                }

                if (isCyclic) {
                    return i;
                }
            }
        }

        return 0;
    }
}
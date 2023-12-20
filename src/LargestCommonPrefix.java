public class LargestCommonPrefix {

    /*
    Longest Common Prefix in an Array

    Given a array of N strings, find the longest common prefix among all strings present in the array.
    Example 1:

    Input:
    N = 4
    arr[] = {geeksforgeeks, geeks, geek,
            geezer}
    Output: gee
    Explanation: "gee" is the longest common
    prefix in all the given strings.*/

    public static String longestCommonPrefix(String[] arr, int n) {
        // code here
        // take temp_prefix string and assign first element of arr : a.
        String result = arr[0];

        // Iterate for rest of element in string.
        for (int i = 1; i < n; i++) {
            // .indexOf() return index of that substring from string.
            while (arr[i].indexOf(result) != 0) {

                // update matched substring prefx
                result = result.substring(0, result.length() - 1);

                // check for empty case. direct return if true..
                if (result.isEmpty()) {
                    return "-1";
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] arr = {"geeksforgeeks", "geeks", "geek", "geezer"};
        int n = arr.length;
        System.out.println(longestCommonPrefix(arr, n));
    }
}
public class StrStrImplementation {

    /*
    Implement strstr

    Your task is to implement the function strstr. The function takes two strings as arguments (s,x) and
    locates the occurrence of the string x in the string s.
    The function returns and integer denoting the first occurrence of the string x in s (0 based indexing).

    Note: You are not allowed to use inbuilt function.
    Example 1:

    Input:
    s = GeeksForGeeks, x = Fr
    Output: -1
    Explanation: Fr is not present in the string GeeksForGeeks as substring.*/

    public static //Function to locate the occurrence of the string x in the string s.
    int strstr(String s, String x) {
        // Your code here
        // if `s` is null or if s's length is less than that of x's
        if (s == null || x.length() > s.length()) {
            return -1;
        }
        // if `x` is null or is empty
        if (x == null || x.length() == 0) {
            return 0;
        }
        for (int i = 0; i <= s.length() - x.length(); i++) {
            int j;
            for (j = 0; j < x.length(); j++) {
                if (x.charAt(j) != s.charAt(i + j)) {
                    break;
                }
            }
            if (j == x.length()) {
                return i;
            }
        }
        return -1;
    }
}

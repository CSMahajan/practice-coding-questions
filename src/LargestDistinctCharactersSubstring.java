import java.util.Arrays;

public class LargestDistinctCharactersSubstring {

    /*
    Longest Distinct characters in string

    Given a string S, find length of the longest substring with all distinct characters.

    Example 1:

    Input:
    S = "geeksforgeeks"
    Output: 7
    Explanation: "eksforg" is the longest substring with all distinct characters.*/

    public static int longestSubstrDistinctChars(String S) {
        // code here
        int[] str = new int[26];
        int start = -1;
        int max = 0;
        Arrays.fill(str, -1);
        for (int i = 0; i < S.length(); i++) {
            int currentCharacterHash = S.charAt(i) - 'a';
            if (str[currentCharacterHash] > start) {
                start = str[currentCharacterHash];
            }
            str[currentCharacterHash] = i;
            max = Math.max(max, i - start);
        }
        return max;
    }
}

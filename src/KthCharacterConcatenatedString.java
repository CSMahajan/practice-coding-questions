/*
Determine the K^th character in the concatenated string

Given a list that contains N strings of lowercase English alphabets. Any number of contiguous strings can be found together to form a new string. The grouping function accepts two integers X and Y and concatenates all strings between indices X and Y (inclusive) and returns a modified string in which the alphabets of the concatenated string are sorted.

You are asked Q questions each containing two integers L and R. Determine the $K^{th}$. character in the concatenated string if we pass L and R to the grouping function.

Input Format
First Line: N(number of strings in the list)
Next N lines: String $S_i$
Next line Q(number of questions)
Next Q lines : Three space-separated integers L, R and K
Output Format
For each question, print the K^th character of the concatenated string in a new line.
Sample Test Cases
Sample Input                 Sample Output
5                                 c
aaaaa                             d
bbbbb                             e
ccccc
ddddd
eeeee
3
3 3 3
1 5 16
3 5 15
Explanation
Q1 Grouped String - ccccc. 3rd character is c
Q2 Grouped String - aaaaabbbbbcccccdddddeeeee. 16th character is d
Q3 Grouped String - cccccdddddeeeee. 15th character is e
Note: It is always guaranteed that the K^th position is valid
*/

import java.util.Arrays;

public class KthCharacterConcatenatedString {

    public static char[] kthCharacterString(int[][] queries, int q, String[] allStrings, int n) {
        int[][] mat = new int[n][26];
        for (int i = 0; i < n; i++) {
            String currentString = allStrings[i];
            for (int j = 0; j < currentString.length(); j++) {
                mat[i][currentString.charAt(i) - 'a']++;
            }
        }
        char[] kthCharArr = new char[q];
        for (int i = 0; i < q; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            int kthPosition = queries[i][2];
            kthCharArr[i] = getKthCharacter(mat, start, end, kthPosition);
        }
        return kthCharArr;
    }

    private static char getKthCharacter(int[][] mat, int start, int end, int kthPosition) {
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = start - 1; j <= end - 1; j++) {
                sum += mat[j][i];
            }
            if (sum >= kthPosition)
                return (char) ('a' + i);
        }
        return 'z';
    }

    public static void main(String[] args) {
        int n = 5;
        String[] allStrings = {"aaaaa", "bbbbb", "ccccc", "ddddd", "eeeee"};
        int q = 3;
        int[][] queries = {{3, 3, 3}, {1, 5, 16}, {3, 5, 15}};
        char[] kCharArr = kthCharacterString(queries, q, allStrings, n);
        System.out.println(Arrays.toString(kCharArr));
    }
}

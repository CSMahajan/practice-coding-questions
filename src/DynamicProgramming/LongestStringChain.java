package DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;

/*
Longest String Chain

You are given an array of words where each word consists of lowercase English letters.
wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA
without changing the order of the other characters to make it equal to wordB.
For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1,
where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on.
A single word is trivially a word chain with k == 1.
Return the length of the longest possible word chain with words chosen from the given list of words.
*/
public class LongestStringChain {

    //Time Complexity: O(N*N * l)
    //Reason: We are setting up two nested loops and the compare function can be estimated to l,
    //where l is the length of the longest string in the words [ ] array.
    //Also, we are sorting so the time complexity will be (N^2 * l + NlogN)
    //Space Complexity: O(N)
    //Reason: We are only using a single array of size n.
    public int longestStrChain(String[] words) {
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maximumLengthOfLIS = 1;
        Arrays.sort(words, Comparator.comparingInt(String::length));
        for (int i = 0; i < n; i++) {
            for (int previousIndex = 0; previousIndex < i; previousIndex++) {
                if (isValidStringChain(words[i], words[previousIndex]) && dp[i] < 1 + dp[previousIndex]) {
                    dp[i] = 1 + dp[previousIndex];
                }
            }
            if (maximumLengthOfLIS < dp[i]) {
                maximumLengthOfLIS = dp[i];
            }
        }
        return maximumLengthOfLIS;
    }

    private boolean isValidStringChain(String s1, String s2) {
        //two strings are said to be in string chain if two strings(s1,s2) differ with each other by exactly one letter
        //which is inserted at any index in smaller string(s2) to form larger string(s1)
        if (s1.length() != 1 + s2.length()) {
            return false;
        }
        int first = 0;
        int second = 0;
        while (first < s1.length()) {
            if (second < s2.length() && s1.charAt(first) == s2.charAt(second)) {
                first++;
                second++;
            } else {
                first++;
            }
        }
        return first == s1.length() && second == s2.length();
    }

    public static void main(String[] args) {
        String[] words = {"a","b","ba","bca","bda","bdca"};
        LongestStringChain lsc = new LongestStringChain();
        System.out.println(lsc.longestStrChain(words));
    }
}

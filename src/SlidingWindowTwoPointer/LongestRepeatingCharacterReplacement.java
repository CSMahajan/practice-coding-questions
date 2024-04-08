package SlidingWindowTwoPointer;

import java.util.HashMap;
import java.util.Map;

/*
Longest Repeating Character Replacement

You are given a string s and an integer k.
You can choose any character of the string and change it to any other uppercase English character.
You can perform this operation at most k times.
Return the length of the longest substring containing the same letter
you can get after performing the above operations.
Example 1:
Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.
*/
public class LongestRepeatingCharacterReplacement {

    //TC:O(N)
    //SC:O(1)
    public int characterReplacement(String s, int k) {
return 0;
    }

    public static void main(String[] args) {
        String s = "ABAB";
        int k = 2;
        LongestRepeatingCharacterReplacement lrcr = new LongestRepeatingCharacterReplacement();
        System.out.println(lrcr.characterReplacement(s, k));
    }
}

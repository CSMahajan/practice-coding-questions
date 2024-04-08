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
        int n = s.length();
        int l = 0, r = 0, maxFrequency = 0, maxLength = 0;
        //maxFrequency denotes the maximum frequency of any character in the string
        //s is going to be of only uppercase letters, so hash of 26 is sufficient
        //the number of operations required to make the longest string with same character
        //can be calculated by length of window - maximum frequency
        //where current window calculated by r-l+1
        //so this difference signifies the minimum possible operations to be performed
        //for maximising string of one character repeated
        int[] hash = new int[26];
        while (r < n) {
            int rHash = s.charAt(r) - 'A';
            int lHash = s.charAt(l) - 'A';
            hash[rHash]++;
            maxFrequency = Math.max(maxFrequency, hash[rHash]);
            if ((r - l + 1) - maxFrequency > k) {
                //if required number of replacement operations cross the limit k,
                //then reduce from left side of window
                hash[lHash]--;
                maxFrequency = 0;
                //moving the window from left side
                l++;
            }
            if ((r - l + 1) - maxFrequency <= k) {
                //updating the maxLength if replacement operations are within the limit
                maxLength = Math.max(maxLength, r - l + 1);
            }
            r++;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "ABAB";
        int k = 2;
        LongestRepeatingCharacterReplacement lrcr = new LongestRepeatingCharacterReplacement();
        System.out.println(lrcr.characterReplacement(s, k));
    }
}

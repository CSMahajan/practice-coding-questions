package SlidingWindowTwoPointer;

import java.util.Arrays;

/*
Longest Substring Without Repeating Characters

Given a string s, find the length of the longest substring without repeating characters.
Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int l = 0, r = 0, maxLength = 0;
        int[] hash = new int[256];
        Arrays.fill(hash, -1);
        //logic is hash array stores the characters position
        while (r < n) {
            //if the character is present in hash
            if (hash[s.charAt(r)] != -1) {
                //if previous occurrence of character is behind/past our current window,
                //then make l stand at next to current occurrence of character
                if (hash[s.charAt(r)] >= l) {
                    l = hash[s.charAt(r)] + 1;
                }
            }
            //calculating the length l and r forms the window
            int length = r - l + 1;
            maxLength = Math.max(maxLength, length);
            //update the hash of current character in the window(basically considered in longest substring
            hash[s.charAt(r)] = r;
            r++;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        LongestSubstringWithoutRepeatingCharacters lswrc = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(lswrc.lengthOfLongestSubstring(s));
    }
}

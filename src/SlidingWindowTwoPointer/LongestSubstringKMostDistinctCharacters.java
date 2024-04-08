package SlidingWindowTwoPointer;

import java.util.HashMap;
import java.util.Map;

/*
Longest Substring with At Most K Distinct Characters

You are given a string 'str' and an integer ‘K’.
Your task is to find the length of the largest substring with at most ‘K’ distinct characters.
For example:
You are given ‘str’ = ‘abbbbbbc’ and ‘K’ = 2,
then the substrings that can be formed are [‘abbbbbb’, ‘bbbbbbc’]. Hence the answer is 7.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 10
1 <= K <= 26
1 <= |str| <= 10^6
The string str will contain only lowercase alphabets.
Sample Input 1:
2
2
abbbbbbc
3
abcddefg
Sample Output 1:
7
4
Explanation:
For the first test case, ‘str’ = ‘abbbbbbc’ and ‘K’ = 2,
then the substrings that can be formed are [‘abbbbbb’, ‘bbbbbbc’]. Hence the answer is 7.
For the second test case, ‘str’ = ‘abcddefg’ and ‘K’ = 3,
then the substrings that can be formed is [‘cdde’, ‘ddef’]. Hence the answer is 4.
Sample Input 2:
2
3
aaaaaaaa
1
abcefg
Sample Output 2:
8
1
*/
public class LongestSubstringKMostDistinctCharacters {

    //TC:O(N)
    //SC:O(1)
    public int kDistinctChars(String s, int k) {
        //logic is similar to FruitIntoBaskets, only difference is k is 2 (only 2 baskets) in fruit basket example
        int n = s.length();
        int l = 0, r = 0, maxLength = -1;
        Map<Character, Integer> map = new HashMap<>();
        while (r < n) {
            map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0) + 1);
            if (map.size() > k) {
                //remove only once instead of while loop for optimal solution
                map.put(s.charAt(l), map.get(s.charAt(l)) - 1);
                if (map.get(s.charAt(l)) == 0) {
                    //if current character is not available in map, remove it from map
                    map.remove(s.charAt(l));
                }
                //shifting window by moving left side of window
                l++;
            }
            if (map.size() == k) {
                maxLength = Math.max(maxLength, r - l + 1);
            }
            r++;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abbbbbbc";
        int k = 2;
        LongestSubstringKMostDistinctCharacters lrcr = new LongestSubstringKMostDistinctCharacters();
        System.out.println(lrcr.kDistinctChars(s, k));
    }
}

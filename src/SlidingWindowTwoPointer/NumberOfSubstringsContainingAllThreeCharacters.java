package SlidingWindowTwoPointer;

/*
Number of Substrings Containing All Three Characters

Given a string s consisting only of characters a, b and c.
Return the number of substrings containing at least one occurrence of all these characters a, b and c.
Example 1:
Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are
"abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
Example 2:
Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are
"aaacb", "aacb" and "acb".
Example 3:
Input: s = "abc"
Output: 1
*/
public class NumberOfSubstringsContainingAllThreeCharacters {

    //TC:O(N)
    //SC:O(1)
    public int numberOfSubstrings(String s) {
        //Here we are trying to find the minimum window possible for every element towards its left side
        //so l to r denoting the minimum window possible where 0 to l if included would also make substring
        //with all 3 characters.
        //This can be achieved by last index of all 3 characters(minimum window can be of size 3 onwards)
        //lastSeen array denotes the last found occurrence of all 3 characters (a,b,c)
        //initialised with -1 for all three characters
        int[] lastSeen = {-1, -1, -1};
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            lastSeen[s.charAt(i) - 'a'] = i;
            //below if condition is redundant, we can directly calculate the count,
            //because even if 1 or more character if not found yet,
            //count will be same as 1 added extra is removed by -1 as min value
            //from lastseen of all 3 characters
            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
                count += 1 + Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "abcabc";
        NumberOfSubstringsContainingAllThreeCharacters ns = new NumberOfSubstringsContainingAllThreeCharacters();
        System.out.println(ns.numberOfSubstrings(s));
    }
}

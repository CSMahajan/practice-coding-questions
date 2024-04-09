package SlidingWindowTwoPointer;

import java.util.HashMap;

/*
Minimum Window Substring

Given two strings s and t of lengths m and n respectively, return the minimum window
substring of s such that every character in t (including duplicates) is included in the window.
If there is no such substring, return the empty string "".
The testcases will be generated such that the answer is unique.
Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
*/
public class MinimumWindowSubstring {

    //TC:O(2N) + O(M)
    //SC:O(256)
    public String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) {
            return "";
        }
        int[] hash = new int[256];
        int l = 0, r = 0;
        int minLength = (int) 1e9;
        int startingIndex = -1;
        int count = 0;
        int n = s.length();
        int m = t.length();
        //adding all characters into hash array with frequency
        for (int i = 0; i < m; i++) {
            hash[t.charAt(i)]++;
        }
        while (r < n) {
            //if we found letter from t in s, then increment the count
            if (hash[s.charAt(r)] > 0) {
                count++;
            }
            //reducing the frequency/count of character at index r in s
            hash[s.charAt(r)]--;
            while (count == m) {
                //if we found that count matches to t length then recursively check to minimise length
                //noting the startingIndex which can be as later as possible
                if (minLength > (r - l + 1)) {
                    minLength = (r - l + 1);
                    startingIndex = l;
                }
                //while match is still true, increase hash count/frequency
                hash[s.charAt(l)]++;
                //if it surpasses 0 means it was present already due to t string then reducing the count
                if (hash[s.charAt(l)] > 0) {
                    count--;
                }
                //shifting the window
                l++;
            }
            r++;
        }
        //startingIndex with added minLength gives our minimum string containing all characters from t in s
        return startingIndex == -1 ? "" : s.substring(startingIndex, startingIndex + minLength);
    }

    public String minWindowUsingHashMap(String s, String t) {
        int l=0;
        int r=0;
        HashMap<Character,Integer> mpp=new HashMap<>();
        int cnt=0;
        int sindex=-1;
        int minlen=Integer.MAX_VALUE;
        for(int i=0;i<t.length();i++){
            mpp.put(t.charAt(i), mpp.getOrDefault(t.charAt(i), 0) + 1);
        }
        while(r<s.length()){
            char ch=s.charAt(r);
            if(mpp.containsKey(ch) && mpp.get(ch)>0){
                cnt++;
            }
            mpp.put(ch,mpp.getOrDefault(ch,0)-1);
            while(cnt==t.length()){
                if(r-l+1<minlen){
                    minlen=r-l+1;
                    sindex=l;
                }
                mpp.put(s.charAt(l),mpp.get(s.charAt(l))+1);
                if(mpp.get(s.charAt(l))>0){
                    cnt--;
                }
                l++;
            }
            r++;
        }
        return sindex==-1?"":s.substring(sindex,sindex+minlen);
    }



    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        MinimumWindowSubstring mws = new MinimumWindowSubstring();
        System.out.println(mws.minWindow(s, t));
        System.out.println(mws.minWindowUsingHashMap(s, t));
    }
}

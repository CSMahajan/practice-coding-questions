package DynamicProgramming;

/*
Minimum Insertion Steps to Make a String Palindrome

Given a string s. In one step you can insert any character at any index of the string.
Return the minimum number of steps to make s palindrome.
A Palindrome String is one that reads the same backward as well as forward.
Example 1:
Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we do not need any insertions.
Example 2:
Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".
Example 3:
Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".
*/
public class MinimumInsertionsToMakePalindrome {

    public int minInsertionsSpaceOptimised(String s) {
        //If we carefully observe, keeping pointers at start and end to make a given string as palindrome
        //First we will identify longest palindromic subsequence already present and we will write/count remaining characters as non matching
        //these are going to be reversed and placed at appropriate location to make it palindrome
        //so these remaining charcters is nothing but difference between length of given string and
        //length of longest palindromic subsequence present in that string
        return s.length() - longestPalindromeSubsequenceSpaceOptimised(s);
    }

    public int longestPalindromeSubsequenceSpaceOptimised(String s) {
        //If we find the length of longest common subsequence between given string and reverse of given string,
        //that itself is the length of palindromic subsequence
        String reverse = new StringBuilder(s).reverse().toString();
        return longestCommonSubsequenceSpaceOptimisedTwoArrays(s, reverse);
    }

    //Time Complexity: O(N*M)
    //Reason: There are two nested loops.
    //Space Complexity: O(M) + O(M)
    //Reason: We are using an external array of size ‘M+1’ to store only previous and current rows.
    public int longestCommonSubsequenceSpaceOptimisedTwoArrays(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[] previousRow = new int[m + 1];
        int[] currentRow = new int[m + 1];

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    currentRow[j] = 1 + previousRow[j - 1];
                } else {
                    currentRow[j] = Math.max(previousRow[j],currentRow[j-1]);
                }
            }
            previousRow = currentRow.clone();
        }
        return previousRow[m];
    }

    public static void main(String[] args) {
        MinimumInsertionsToMakePalindrome mitmp = new MinimumInsertionsToMakePalindrome();
        String s = "leetcode";
        System.out.println(mitmp.minInsertionsSpaceOptimised(s));
    }
}

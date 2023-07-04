package DynamicProgramming;

/*
Shortest Common Supersequence

Given two strings X and Y of lengths m and n respectively,
find the length of the smallest string which has both, X and Y as its sub-sequences.
Note: X and Y can have both uppercase and lowercase letters.
Example 1
Input:
X = abcd, Y = xycd
Output: 6
Explanation: Shortest Common Supersequence would be abxycd which is of length 6 and has both the strings as its subsequences.
Example 2
Input:
X = efgh, Y = jghi
Output: 6
Explanation: Shortest Common Supersequence would be ejfghi which is of length 6 and has both the strings as its subsequences.
*/
public class ShortestCommonSupersequence {

    public static int shortestCommonSupersequence(String str1,String str2,int m,int n)
    {
        //Your code here
        //In order to form a shortest common supersequence between two strings,
        //we can have common characters (taken only once) as it is between two strings and uncommon characters as it is
        //so the supersequence will have all the common (taken only once) as well as uncommon characters
        //so supersequence length becomes first string length + second string length - lcs of both strings

        return str1.length() + str2.length() - longestCommonSubsequenceSpaceOptimisedTwoArrays(str1,str2);
    }

    //Time Complexity: O(N*M)
    //Reason: There are two nested loops.
    //Space Complexity: O(M) + O(M)
    //Reason: We are using an external array of size ‘M+1’ to store only previous and current rows.
    public static int longestCommonSubsequenceSpaceOptimisedTwoArrays(String text1, String text2) {
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

        String str1 = "abcd", str2 = "xycd";
        System.out.println(shortestCommonSupersequence(str1, str2, str1.length(),str2.length()));
    }
}

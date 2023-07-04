package DynamicProgramming;

/*
Minimum number of deletions and insertions.

Given two strings str1 and str2.
The task is to remove or insert the minimum number of characters from/in str1 so as to transform it into str2.
It could be possible that the same character needs to be removed/deleted from one point of str1 and inserted to some another point.
Example 1:
Input: str1 = "heap", str2 = "pea"
Output: 3
Explanation: 2 deletions and 1 insertion p and h deleted from heap.
Then, p is inserted at the beginning One thing to note,
though p was required yet it was removed/deleted first from its position and then it is inserted to some other position.
Thus, p contributes one to the deletion_count and one to the insertion_count.
Example 2:
Input : str1 = "geeksforgeeks"
str2 = "geeks"
Output: 8
Explanation: 8 deletions
*/
public class MinimumDeletionsInsertionsToMakeStringsEqual {

    public int minOperations(String str1, String str2)
    {
        // Your code goes here
        //After observing the given two strings in order to make two strings equal
        //total number of deletions done from first string = str1.length() - lcs(str1,str2)
        //total number of insertions done from second string  = str2.length() - lcs(str1,str2)
        //total operations = total deletions + total insertions
        //which sums up to (s1 length + s2 length - 2 times of lcs)

        return str1.length() + str2.length() - 2 * longestCommonSubsequenceSpaceOptimisedTwoArrays(str1,str2);
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
        MinimumDeletionsInsertionsToMakeStringsEqual mditmse = new MinimumDeletionsInsertionsToMakeStringsEqual();
        String str1 = "heap", str2 = "pea";
        System.out.println(mditmse.minOperations(str1, str2));
    }
}

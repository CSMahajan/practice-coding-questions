import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Power Set

Given a string S, Find all the possible subsequences of the String in lexicographically-sorted order.

Example 1:

Input : str = "abc"
Output: a ab abc ac b bc c
Explanation : There are 7 subsequences that
can be formed from abc.
Example 2:

Input: str = "aa"
Output: a a aa
Explanation : There are 3 subsequences that
can be formed from aa.
*/
public class PowerSetAllPossibleSubsequences {

    public static List<String> AllPossibleStrings(String s) {
        // Code here
        List<String> list = new ArrayList<>();
        int n = s.length();
        for (int num = 0; num < Math.pow(2, n); num++) {
            String str = "";
            for (int i = 0; i < n; i++) {
                if ((num & (1 << i)) != 0) {
                    str += s.charAt(i);
                }
            }
            if (str.length() > 0) {
                list.add(str);
            }
        }
        Collections.sort(list);
        return list;
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsetsList = new ArrayList<>();
        int n = nums.length;
        for (int num = 0; num < Math.pow(2, n); num++) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((num & (1 << i)) != 0) {
                    list.add(nums[i]);
                }
            }
            subsetsList.add(list);
        }
        return subsetsList;
    }

    public static void main(String args[]) {
        String s = "abc";
        //print all the subsequence.
        //System.out.println("All possible subsequences are ");
        //System.out.print(AllPossibleStrings(s));
        int nums[] = {1, 2, 3};
        System.out.println(subsets(nums));
    }
}

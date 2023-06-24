import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Combination Sum II

GeeksForGeeks Problem Statement
You are given a collection of numbers (candidates) and a target number (target),
find all unique combinations in candidates where the candidate numbers sum to the target.
Each number in candidates may only be used once in the combination.
Note: The solution set must not contain duplicate combinations.
Example:
Input:  candidates = [10,1,2,7,6,1,5], target = 8.
Output:  [[1,1,6], [1,2,5], [1,7], [2,6]].
Explanation:  These are the unique combinations whose sum is equal to the target.
Your Task:
Complete the function vector> combinationSum2(), which takes a vector "candidates" and an integer target and
returns a vector of vector consisting of all unique combinations whose sum is equal to the target.

LeetCode Problem Statement
Given a collection of candidate numbers (candidates) and a target number (target),
find all unique combinations in candidates where the candidate numbers sum to target.
Each number in candidates may only be used once in the combination.
Note: The solution set must not contain duplicate combinations.
Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:
Input: candidates = [2,5,2,1,2], target = 5
Output:
[
[1,2,2],
[5]
]
*/
public class CombinationSum2 {

    //Note:We can take any element only given number of times
    public static List<List<Integer>> combinationSum2(int[] a, int s) {
        //Sorting will enable us finding all unique distinct combinations in lexicographical order
        Arrays.sort(a);
        List<List<Integer>> allCombinations = new ArrayList<>();
        getAllCombinationsGeeksForGeeks(0, a, s, allCombinations, new ArrayList<>());
        return allCombinations;
    }

    private static void getAllCombinationsGeeksForGeeks(int index, int[] candidates, int target, List<List<Integer>> allCombinations, ArrayList<Integer> storingList) {
        if (target == 0) {
            allCombinations.add(new ArrayList<>(storingList));
            return;
        }
        //As we take any element only given number of times at max to make it unique we either have the option to pick or not pick but
        //any element at index will be increased by 1
        //so at start,we will have option of 0th,1st,2nd,....(n-1)th index,
        //but at index 1, we have option 1st,2nd onwards and not to any previous indexes
        for (int i = index; i < candidates.length; i++) {
            //Below condition is added to avoid duplicates combinations in non-decreasing order to take same element multiple times
            //i > index added to check this from indexes if they have already been, for 0 we are at start,so from greater we need to check
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            //While execution of recursive calls in for loop if we surpass our target,
            //then further elements are anyways greater or equal than current element candidate[i],
            //so they will also surpass the target
            if (candidates[i] > target) {
                break;
            }
            storingList.add(candidates[i]);
            getAllCombinationsGeeksForGeeks(i + 1, candidates, target - candidates[i], allCombinations, storingList);
            storingList.remove(storingList.size() - 1);
        }
    }

    public static void main(String[] args) {
        //For GeeksForGeeks
        int a[] = {10, 1, 2, 7, 6, 1, 5};
        int s = 8;
        System.out.println(combinationSum2(a, s));

        //For LeetCode
        CombinationSum2 cs = new CombinationSum2();
        int candidates[] = {2, 3, 6, 7};
        int target = 7;
        //System.out.println(cs.combinationSum(candidates,target));
    }
}

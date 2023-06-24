import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
Combination Sum

GeeksForGeeks Problem Statement
Given an array of integers and a sum B, find all unique combinations in the array where the sum is equal to B.
The same number may be chosen from the array any number of times to make B.
Note:   1. All numbers will be positive integers.
        2. Elements in a combination (a1, a2, …, ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
        3. The combinations themselves must be sorted in ascending order.
Example 1:
Input:
N = 4
arr[] = {7,2,6,5}
B = 16
Output:
(2 2 2 2 2 2 2 2)
(2 2 2 2 2 6)
(2 2 2 5 5)
(2 2 5 7)
(2 2 6 6)
(2 7 7)
(5 5 6)
Example 2:
Input:
N = 11
arr[] = {6,5,7,1,8,2,9,9,7,7,9}
B = 6
Output:
(1 1 1 1 1 1)
(1 1 1 1 2)
(1 1 2 2)
(1 5)
(2 2 2)
(6)

LeetCode Problem Statement
Given an array of distinct integers candidates and a target integer target,
return a list of all unique combinations of candidates where the chosen numbers sum to target.
You may return the combinations in any order.
The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
frequency of at least one of the chosen numbers is different.
The test cases are generated such that the number of unique combinations
that sum up to target is less than 150 combinations for the given input.
Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:
Input: candidates = [2], target = 1
Output: []
*/
public class CombinationSum {

    //GeeksForGeeks method
    public static ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {
        // add your code here
        //allCombinations for storing all of the possible combinations which adds up to target B
        //Note:We can add any element any number of times
        //We will pass extra arraylist to store current combination of elements
        //index required to calculate recursively till final element of array to check all possible combination
        ArrayList<ArrayList<Integer>> allCombinations = new ArrayList<>();
        getAllCombinationsGeeksForGeeks(0, A, B, allCombinations, new ArrayList<>());

        for(ArrayList<Integer> currentList : allCombinations) {
            Collections.sort(currentList);
        }
        allCombinations = (ArrayList<ArrayList<Integer>>) allCombinations.stream().distinct().collect(Collectors.toList());
        Comparator<List<Integer>> comparator = (x, y) -> {
            for (int i = 0; i < Math.min(x.size(), y.size()); i++) {
                if (x.get(i) != y.get(i)) {
                    return x.get(i) - y.get(i);
                }
            }
            return Integer.compare(x.size(), y.size());
        };
        allCombinations.sort(comparator);
        return allCombinations;
    }

    //GeeksForGeeks method
    private static void getAllCombinationsGeeksForGeeks(int index, ArrayList<Integer> givenList, int target, ArrayList<ArrayList<Integer>> allCombinations, ArrayList<Integer> storingList) {
        if (index == givenList.size()) {
            //As we have reached the final element we add the list into our allCombinations list
            if (target == 0) {
                //We can only add this current list into allCombinations if it makes the required target
                allCombinations.add(new ArrayList<>(storingList));
            }
            //We will return as reached the last element
            return;
        }

        /*
        Case for picking up current index element
        if current element is not crossing target sum (even after recursive calls),
        as we are adding current element into temporary list of current combinations of elements and
        reducing target if consider our current element into our possible combination to check,
        after that we need to remove that element to consider the case of not pick
        we need to remove inside if block because if we are adding then only we can remove,
        if we place the removing step after the if condition and if element is present, then it makes no sense
        */
        if (givenList.get(index) <= target) {
            storingList.add(givenList.get(index));
            getAllCombinationsGeeksForGeeks(index, givenList, target - givenList.get(index), allCombinations, storingList);
            storingList.remove(storingList.size() - 1);
        }

        /*
        Case for not picking current element
        We will increase index to next element index, keeping target sum as it is
        */
        getAllCombinationsGeeksForGeeks(index + 1, givenList, target, allCombinations, storingList);
    }

    //LeetCode method
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> allCombinations = new ArrayList<>();
        getAllCombinationsLeetCode(0, candidates, target, allCombinations, new ArrayList<>());
        return allCombinations;
    }

    private void getAllCombinationsLeetCode(int index, int[] candidates, int target, List<List<Integer>> allCombinations, ArrayList<Integer> storingList) {
        if (index == candidates.length) {
            //As we have reached the final element we add the list into our allCombinations list
            if (target == 0) {
                //We can only add this current list into allCombinations if it makes the required target
                allCombinations.add(new ArrayList<>(storingList));
            }
            return;
        }

        /*
        Case for picking up current index element
        if current element is not crossing target sum (even after recursive calls),
        as we are adding current element into temporary list of current combinations of elements and
        reducing target if consider our current element into our possible combination to check,
        after that we need to remove that element to consider the case of not pick
        we need to remove inside if block because if we are adding then only we can remove,
        if we place the removing step after the if condition and if element is present, then it makes no sense
        */
        if (candidates[index] <= target) {
            storingList.add(candidates[index]);
            getAllCombinationsLeetCode(index, candidates, target - candidates[index], allCombinations, storingList);
            storingList.remove(storingList.size() - 1);
        }

        /*
        Case for not picking current element
        We will increase index to next element index, keeping target sum as it is
        */
        getAllCombinationsLeetCode(index + 1, candidates, target, allCombinations, storingList);
    }

    public static void main(String[] args) {
        //int arr[] = {7,2,6,5};
        //For GeeksForGeeks
        /*ArrayList<Integer> A = new ArrayList<>();
        A.add(7);
        A.add(2);
        A.add(6);
        A.add(5);
        int B = 16;*/
        ArrayList<Integer> A = new ArrayList<>();
        A.add(8);
        A.add(1);
        A.add(8);
        A.add(6);
        A.add(8);
        int B = 12;
        System.out.println(combinationSum(A, B));

        //For LeetCode
        CombinationSum cs = new CombinationSum();
        int candidates[] = {2,3,6,7};
        int target = 7;
        System.out.println(cs.combinationSum(candidates,target));
    }
}

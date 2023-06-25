import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Subset II

GeeksForGeeks Problem Statement
You are given an integer array nums that may contain duplicates. Your task is to return all possible subsets.
Return only unique subsets and they can be in any order.
Example:
Input:
nums = [1,2,2]
Output:
[[],[1],[1,2],[1,2,2],[2],[2,2]]
Explanation:
We can have subsets ranging from length 0 to 3. which are listed above.
Also the subset [1,2] appears twice but is printed only once as we require only unique subsets.

LeetCode Problem Statement
Given an integer array nums that may contain duplicates, return all possible
subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.
Example 1:
Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:
Input: nums = [0]
Output: [[],[0]]
*/
public class Subset2 {

    //GeeksForGeeks
    public static ArrayList<ArrayList<Integer>> printUniqueSubsets(int[] nums) {
        ArrayList<ArrayList<Integer>> allSubsetList = new ArrayList<>();
        //Sorting will help us in keeping duplicate elements together which will help in avoiding duplicate subsets without any extra operations
        Arrays.sort(nums);
        findAllSubsets(0, nums, new ArrayList<>(), allSubsetList);
        return allSubsetList;
    }

    private static void findAllSubsets(int index, int[] nums, ArrayList<Integer> currentSubsetList, ArrayList<ArrayList<Integer>> allSubsetList) {
        //Below adding into allSubsetList will occur whenever i crosses nums.length,
        //so no further element present to add and at every possible unique combination we wil add
        allSubsetList.add(new ArrayList<>(currentSubsetList));
        for (int i = index; i < nums.length; i++) {
            //The condition (i != index) is used for avoiding duplicates from next index of the start
            //Only at the start we will allow to add that element for the same size
            //he condition (nums[i] == nums[i - 1]) is used for checking if the current element is duplicate or not
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            //We need to add current element to consider the case for pick
            //Case-Pick
            currentSubsetList.add(nums[i]);
            //Recursive call with next index
            findAllSubsets(i + 1, nums, currentSubsetList, allSubsetList);
            //We need to remove currently added element to consider the case for not pick
            //Case-Not Pick
            currentSubsetList.remove(currentSubsetList.size() - 1);
        }
    }

    //LeetCode
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> allSubsetList = new ArrayList<>();
        //Sorting will help us in keeping duplicate elements together which will help in avoiding duplicate subsets without any extra operations
        Arrays.sort(nums);
        findAllSubsetsLeetCode(0, nums, new ArrayList<>(), allSubsetList);
        return allSubsetList;
    }

    private static void findAllSubsetsLeetCode(int index, int[] nums, ArrayList<Integer> currentSubsetList, List<List<Integer>> allSubsetList) {
        //Below adding into allSubsetList will occur whenever i crosses nums.length,
        //so no further element present to add and at every possible unique combination we wil add
        allSubsetList.add(new ArrayList<>(currentSubsetList));
        for (int i = index; i < nums.length; i++) {
            //The condition (i != index) is used for avoiding duplicates from next index of the start
            //Only at the start we will allow to add that element for the same size
            //he condition (nums[i] == nums[i - 1]) is used for checking if the current element is duplicate or not
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            //We need to add current element to consider the case for pick
            //Case-Pick
            currentSubsetList.add(nums[i]);
            //Recursive call with next index
            findAllSubsetsLeetCode(i + 1, nums, currentSubsetList, allSubsetList);
            //We need to remove currently added element to consider the case for not pick
            //Case-Not Pick
            currentSubsetList.remove(currentSubsetList.size() - 1);
        }
    }

    public static void main(String[] args) {
        int nums[] = {1,2,2};
        System.out.println(printUniqueSubsets(nums));

        Subset2 subset2 = new Subset2();
        System.out.println(subset2.subsetsWithDup(nums));
    }
}

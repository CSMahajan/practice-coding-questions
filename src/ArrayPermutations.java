import java.util.ArrayList;
import java.util.List;

/*
Permutations

Given an array nums of distinct integers, return all the possible permutations.
You can return the answer in any order.All the integers of nums are unique.
Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:
Input: nums = [1]
Output: [[1]]
*/
public class ArrayPermutations {

    //TC:O(n!)*n, where n!(n factorial) for all permutations possible calculated recursively and multiplied by n because of for loop
    //Example: if n=3,arr={1,2,3}, total permutations = 3!= 6
    //SC:O(n)+O(n), where first O(n) for currentStoringList and second for frequency array
    public List<List<Integer>> permuteWithHashing(int[] nums) {
        List<List<Integer>> allPermutationList = new ArrayList<>();
        List<Integer> currentStoringList = new ArrayList<>();
        boolean[] frequency = new boolean[nums.length];
        findAllPermutations(nums, allPermutationList, currentStoringList, frequency);
        return allPermutationList;
    }

    private static void findAllPermutations(int[] nums, List<List<Integer>> allPermutationList, List<Integer> currentStoringList, boolean[] frequency) {
        if (currentStoringList.size() == nums.length) {
            allPermutationList.add(new ArrayList<>(currentStoringList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!frequency[i]) {
                //If element is not visited then mark it as visited, add the current element to our current list and keep checking recursively
                frequency[i] = true;
                //case pick element
                currentStoringList.add(nums[i]);
                findAllPermutations(nums, allPermutationList, currentStoringList, frequency);
                //removing the current element for not pick case
                currentStoringList.remove(currentStoringList.size() - 1);
                //Again marking it as unvisited for other elements second onwards element possible permutation
                frequency[i] = false;
            }
        }
    }

    //TC:O(n!)*n, where n!(n factorial) for all permutations possible calculated recursively and multiplied by n because of for loop
    //Example: if n=3,arr={1,2,3}, total permutations = 3!= 6
    //SC:O(n), where first O(n) for currentStoringList
    public List<List<Integer>> permuteWithSwapping(int[] nums) {
        List<List<Integer>> allPermutationList = new ArrayList<>();
        findAllPermutationsSwapping(0, nums,allPermutationList);
        return allPermutationList;
    }

    private void findAllPermutationsSwapping(int index, int[] nums, List<List<Integer>> allPermutationList) {
        if(index == nums.length) {
            //At end of every permutation after swapping we will add the array into current list
            List<Integer> currentStoringList = new ArrayList<>();
            for(int i = 0; i < nums.length; i++) {
                currentStoringList.add(nums[i]);
            }
            //add current permutation into final list
            allPermutationList.add(new ArrayList<>(currentStoringList));
            return;
        }
        for(int i = index; i < nums.length; i++) {
            //Swapping logic is done for every starting i to start to swap from itself with every other element in array
            swap(i, index, nums);
            /*While calling recursively, we are giving first parameter as index + 1 because firstly we have swapped index and i and secondly if we pass i + 1, it will cause stack overflow or segmentation fault or ArrayIndexOutOfBoundsException
            Example: let i = 0, and index = nums.length -1(index at last element) and
            we swapped it and then calling with i + 1 would make i = nums.length which gives ArrayIndexOutOfBoundsException or
            stack overflow error while accessing or later we are swapping again to consider all permutations*/
            findAllPermutationsSwapping(index + 1, nums, allPermutationList);
            //To consider other permutations by swapping again we will keep the array as it is
            swap(i, index, nums);
        }
    }

    private void swap(int i, int index, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[index];
        nums[index] = temp;
    }

    public static void main(String[] args) {
        ArrayPermutations ap = new ArrayPermutations();
        int nums[] = {1, 2, 3};
        System.out.println(ap.permuteWithHashing(nums));
        System.out.println(ap.permuteWithSwapping(nums));
    }
}

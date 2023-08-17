package Stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
Sliding Window Maximum

Given an array arr[] of size N and an integer K. Find the maximum for each and every contiguous sub-array of size K.
Example 1:
Input:
N = 9, K = 3
arr[] = 1 2 3 1 4 5 2 3 6
Output:
3 3 4 5 5 5 6
Explanation:
1st contiguous subarray = {1 2 3} Max = 3
2nd contiguous subarray = {2 3 1} Max = 3
3rd contiguous subarray = {3 1 4} Max = 4
4th contiguous subarray = {1 4 5} Max = 5
5th contiguous subarray = {4 5 2} Max = 5
6th contiguous subarray = {5 2 3} Max = 5
7th contiguous subarray = {2 3 6} Max = 6
Example 2:
Input:
N = 10, K = 4
arr[] = 8 5 10 7 9 4 15 12 90 13
Output:
10 10 10 15 15 90 90
Explanation:
1st contiguous subarray = {8 5 10 7}, Max = 10
2nd contiguous subarray = {5 10 7 9}, Max = 10
3rd contiguous subarray = {10 7 9 4}, Max = 10
4th contiguous subarray = {7 9 4 15}, Max = 15
5th contiguous subarray = {9 4 15 12},
Max = 15
6th contiguous subarray = {4 15 12 90},
Max = 90
7th contiguous subarray = {15 12 90 13},
Max = 90
You are given an array of integers nums, there is a sliding window of size k
which is moving from the very left of the array to the very right.
You can only see the k numbers in the window.
Each time the sliding window moves right by one position.
Return the max sliding window.
Example 1:
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Example 2:
Input: nums = [1], k = 1
Output: [1]
*/
public class SlidingWindowMaximumOfSubarrayKSize {

    //Time Complexity: O(N)
    //Space Complexity: O(K)
    public int[] maxSlidingWindow(int[] arr, int k) {
        int n = arr.length;
        //total n-k+1 sub-arrays will be formed from k size window on n size array
        int[] maxSubArray = new int[n - k + 1];
        int subarrayIndex = 0;
        //We are using deque which is a combination of stack and queue, also known as doubly ended queue
        //in deque, we can insert and remove elements from the head and tail of its data structure
        //deque.offerFirst(),deque.offerLast(),deque.pollFirst(),deque.pollLast(),deque.peekFirst(),deque.peekLast()
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            //remove numbers out of range k
            while (!deque.isEmpty() && (deque.peekFirst() == i - k)) {
                //if deque has more than k elements then,
                //start removing elements from head as they can not be our answer(maximum) for current window of k size
                deque.pollFirst();
            }
            //remove smaller numbers in range k as they are useless to find maximum
            while (!deque.isEmpty() && (arr[deque.peekLast()] <= arr[i])) {
                //as we are storing elements in decreasing order in our deque, start removing smaller elements first,
                //which are present from tail of the deque
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                maxSubArray[subarrayIndex++] = arr[deque.peekFirst()];
            }
        }
        return maxSubArray;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        SlidingWindowMaximumOfSubarrayKSize swm = new SlidingWindowMaximumOfSubarrayKSize();
        System.out.println(Arrays.toString(swm.maxSlidingWindow(arr, k)));
    }
}
/*
Minimum number of jumps

Given an array of N integers arr[] where each element represents the maximum length of the jump
that can be made forward from that element.
This means if arr[i] = x, then we can jump any distance y such that y ≤ x.
Find the minimum number of jumps to reach the end of the array (starting from the first element).
If an element is 0, then you cannot move through that element.

Note: Return -1 if you can't reach the end of the array.

Example 1:

Input:
N = 11
arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
Output: 3
Explanation:
First jump from 1st element to 2nd element with value 3. Now, from here
we jump to 5th element with value 9, and from here we will jump to the last.
*/

public class MinimumJumps {
    public static int minJumps(int[] arr) {
        int jump = 0, pos = 0, des = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (i == pos && arr[pos] == 0 && pos == des) return -1;
            des = Math.max(des, arr[i] + i);
            if (pos == i) {
                pos = des;
                jump++;
            }
        }
        return jump;
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        System.out.println(minJumps(arr));
    }
}

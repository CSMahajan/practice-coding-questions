/*
Longest Sub-Array with Sum K

Given an array containing N integers and an integer K.,
Your task is to find the length of the longest Sub-Array with the sum of the elements equal to the given value K.
The array contains only positive elements
Example 1:
Input :
A[] = {10, 5, 2, 7, 1, 9}
K = 15
Output : 4
Explanation:The sub-array is {5, 2, 7, 1}.
*/
public class LargetSubarrayWithSumKPositives {

    //TC:O(N), SC:O(1)
    public static int longestSubarrayWithSumK(int[] a, long k) {
        // Write your code here
        int maxLength = 0;
        long sum = a[0];
        int n = a.length;
        int left = 0;
        int right = 0;
        while (right < n) {
            while (sum > k) {
                //If sum surpasses k then remove from left pointer side and move left to next position
                sum -= a[left];
                left++;
            }
            if (sum == k) {
                //left to right range is our possible maximum length of subarray with sum k
                maxLength = Math.max(maxLength, right - left + 1);
            }
            right++;
            //Here we are checking right<n before adding it to sum because if it is at last index
            //and we had just increase right by 1 will cause ArrayIndexOutOfBoundsException
            if (right < n) {
                sum += a[right];
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int a[] = {10, 5, 2, 7, 1, 9};
        int k = 15;
        System.out.println(longestSubarrayWithSumK(a,k));
    }
}

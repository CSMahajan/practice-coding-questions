/*

Flip Bits

You are given an array of integers ARR[] of size N consisting of zeros and ones.
You have to select a subset and flip bits of that subset.
You have to return the count of maximum one’s that you can obtain by flipping chosen sub-array at most once.
A flip operation is one in which you turn 1 into 0 and 0 into 1.

If you are given an array {1, 1, 0, 0, 1} then you will have to
return the count of maximum one’s you can obtain by flipping anyone chosen sub-array at most once,
so here you will clearly choose sub-array from the index 2 to 3 and then flip it's bits.
So, the final array comes out to be {1, 1, 1, 1, 1} which contains five ones and so you will return 5.

Example:
Input: arr= {1, 0, 0, 1, 0}
Output:4
Explanation: if we flip the subarray {0,0,1,0} which gives the total array {1,1,1,0,1} hence total 1's are 4
*/
public class FlipBits {
    public static int flipBits(int[] arr,int n) {
        //Write your code here
        int max = 0, currMax = 0, totalOnes = 0;
        for(int i = 0; i < n; i++){
            if(arr[i] == 1){
                totalOnes++;
            }
            int val = arr[i]==1 ? -1 : 1;
            currMax = Math.max(val, currMax + val);
            max = Math.max(max, currMax);
        }
        return totalOnes + max;
    }

    public static void main(String[] args) {
        int[] arr= {1, 1, 1,0};
        System.out.println(flipBits(arr,4));
    }
}

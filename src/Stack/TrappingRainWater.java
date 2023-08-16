package Stack;

/*
Trapping Rain Water

Given an array arr[] of N non-negative integers representing the height of blocks.
If width of each block is 1, compute how much water can be trapped between the blocks during the rainy season.
Example 1:
Input:
N = 6
arr[] = {3,0,0,2,0,4}
Output:
10
Explanation:
Example 2:
Input:
N = 4
arr[] = {7,4,0,9}
Output:
10
Explanation:
Water trapped by above
block of height 4 is 3 units and above block of height 0 is 7 units. So, the total unit of water trapped is 10 units.
Example 3:
Input:
N = 3
arr[] = {6,9,9}
Output:
0
Explanation:
No water will be trapped.
*/
public class TrappingRainWater {

    //Time Complexity: O(N) because we are using 2 pointer approach.
    //Space Complexity: O(1) because we are not using anything extra.
    public int trap(int[] arr) {
        int n = arr.length;
        int left = 0;
        int right = n - 1;
        int maxLeft = 0;
        int maxRight = 0;
        int totalWaterTrapped = 0;
        while (left <= right) {
            if (arr[left] <= arr[right]) {
                if (maxLeft <= arr[left]) {
                    //updating maxLeft
                    maxLeft = arr[left];
                } else {
                    //adding to trapped rain water
                    totalWaterTrapped += maxLeft - arr[left];
                }
                left++;
            } else {
                if (maxRight <= arr[right]) {
                    //updating maxRight
                    maxRight = arr[right];
                } else {
                    //adding to trapped rain water
                    totalWaterTrapped += maxRight - arr[right];
                }
                right--;
            }
        }
        return totalWaterTrapped;
    }

    public static void main(String[] args) {
        int[] height = {3,0,0,2,0,4};
        TrappingRainWater trw = new TrappingRainWater();
        System.out.println(trw.trap(height));
    }
}
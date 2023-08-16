package Stack;

import java.util.Stack;

/*
Find the largest rectangular area possible in a given histogram where
the largest rectangle can be made of a number of contiguous bars.
For simplicity, assume that all bars have the same width and the width is 1 unit,
there will be N bars height of each bar will be given by the array arr.
Example 1:
Input:
N = 7
arr[] = {6,2,5,4,5,1,6}
Output: 12
Explanation: In this example the largest area would be 12 of height 4 and width 3.
We can achieve this area by choosing 3rd, 4th and 5th bars.
Example 2:
Input:
N = 8
arr[] = {7 2 8 9 1 3 6 5}
Output: 16
Explanation: Maximum size of the histogram will be 8  and there will be 2 consecutive histogram.
And hence the area of the histogram will be 8x2 = 16.
*/
public class LargestRectangleAreaInHistogram {

    //Time Complexity: O( N ) + O (N)
    //Space Complexity: O(N)
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int maximumArea = 0;
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || heights[stack.peek()] >= heights[i])) {
                int height = heights[stack.peek()];
                stack.pop();
                int width;
                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }
                maximumArea = Math.max(maximumArea, width * height);
            }
            stack.push(i);
        }
        return maximumArea;
    }

    public static void main(String[] args) {
        int[] arr = {6, 2, 5, 4, 5, 1, 6};
        LargestRectangleAreaInHistogram lrah = new LargestRectangleAreaInHistogram();
        System.out.println(lrah.largestRectangleArea(arr));
    }
}
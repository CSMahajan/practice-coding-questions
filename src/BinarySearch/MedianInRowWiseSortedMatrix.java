package BinarySearch;

/*
Median in a row-wise sorted Matrix

Given a row wise sorted matrix of size R*C where R and C are always odd, find the median of the matrix.
Example 1:
Input:
R = 3, C = 3
M = [[1, 3, 5],
     [2, 6, 9],
     [3, 6, 9]]
Output: 5
Explanation: Sorting matrix elements gives us {1,2,3,3,5,6,6,9,9}. Hence, 5 is median.
Example 2:
Input:
R = 3, C = 1
M = [[1], [2], [3]]
Output: 2
Explanation: Sorting matrix elements gives us {1,2,3}. Hence, 2 is median.
*/
public class MedianInRowWiseSortedMatrix {

    //TC:O(log2(10^9) * n * log2(m))
    //SC:O(1)
    public int median(int[][] matrix, int n, int m) {
        // code here
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        //as the matrix is row wise sorted so 0 th row will always have the smallest element and
        //last(i.e.(n-1)-th) row will have the largest element for that row
        //so our search space for binary search to find median will lie between smallest and largest element
        for (int i = 0; i < n; i++) {
            low = Math.min(matrix[i][0], low);
            high = Math.max(matrix[i][m - 1], high);
        }
        //below required variable decides how much of the total numbers of elements required which
        //are smaller or equal than the current element in order to declare the number as median
        int required = (n * m) / 2;
        while (low <= high) {
            int mid = (low + high) / 2;
            int smallerEqual = countOfSmallerOrEqualElementsThanMid(matrix, n, m, mid);
            if (smallerEqual <= required) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private int countOfSmallerOrEqualElementsThanMid(int[][] matrix, int n, int m, int number) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            //upperBound method will give the index of the first element greater than the given number in an array
            //so its index itself is number of elements smaller or equal than the number as the array is already sorted
            count += upperBound(matrix[i], m, number);
        }
        return count;
    }

    private int upperBound(int[] matrix, int m, int number) {
        int low = 0;
        int high = m - 1;
        int upperBoundElementIndex = m;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (matrix[mid] > number) {
                upperBoundElementIndex = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return upperBoundElementIndex;
    }

    public static void main(String[] args) {
        int R = 3, C = 3;
        int[][] matrix = {
                {1, 3, 5},
                {2, 6, 9},
                {3, 6, 9}
        };
        MedianInRowWiseSortedMatrix mrwsm = new MedianInRowWiseSortedMatrix();
        System.out.println(mrwsm.median(matrix, R, C));
    }
}
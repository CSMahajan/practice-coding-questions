package BinarySearch;

/*
Row with max 1s

Given a boolean 2D array of n x m dimensions where each row is sorted. Find the 0-based index of the first row that has the maximum number of 1's.

Example 1:

Input:
N = 4 , M = 4
Arr[][] = {{0, 1, 1, 1},
           {0, 0, 1, 1},
           {1, 1, 1, 1},
           {0, 0, 0, 0}}
Output: 2
Explanation: Row 2 contains 4 1's (0-based
indexing).
Example 2:
Input:
N = 2, M = 2
Arr[][] = {{0, 0}, {1, 1}}
Output: 1
Explanation: Row 1 contains 2 1's (0-based
indexing).
*/
public class RowWithMaximum1s {

    //Time complexity: O(n*log2(m))
    //Space complexity: O(1)
    public int rowWithMax1s(int[][] arr, int n, int m) {
        // code here
        int maximumOneCount = 0;
        int maximumOneRow = -1;
        for (int i = 0; i < n; i++) {
            //lowerBound is going to give us the first occurrence of 1's in the current row
            //we can use (lower bound of 1) or (upper bound of 0) or (first occurrence of 1) any of these 3 ways
            int currentOneCount = m - getLowerBound(arr[i], m, 1);
            if (maximumOneCount < currentOneCount) {
                maximumOneCount = currentOneCount;
                maximumOneRow = i;
            }
        }
        return maximumOneRow;
    }

    public int getLowerBound(int[] arr, int n, int x) {
        int low = 0, high = n - 1, lowerBound = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= x) {
                //look for greater index on the right
                lowerBound = mid;
                high = mid - 1;
            } else {
                //look for smaller index on the left
                low = mid + 1;
            }
        }
        return lowerBound;
    }

    public static void main(String[] args) {
        int n = 4, m = 4;
        int[][] arr = {
                {0, 1, 1, 1},
                {0, 0, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 0, 0}
        };
        RowWithMaximum1s rwm1 = new RowWithMaximum1s();
        System.out.println(rwm1.rowWithMax1s(arr, n, m));
    }
}
package Graph;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
Path With Minimum Effort

You are a hiker preparing for an upcoming hike.
You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col).
You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
Example 1:
heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
*/
public class PathWithMinimumEffort {

    //Time Complexity: O( 4*N*M * log( N*M) )
    //{ N*M are the total cells, for each of which we also check 4 adjacent nodes for the minimum effort and
    //additional log(N*M) for insertion-deletion operations in a priority queue }
    //Where, N = No. of rows of the binary maze and M = No. of columns of the binary maze.
    //Space Complexity: O( N*M )
    //{ Distance matrix containing N*M cells + priority queue in the worst case containing all the nodes ( N*M) }.
    //Where, N = No. of rows of the binary maze and M = No. of columns of the binary maze.
    public int minimumEffort(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        int[][] effort = new int[n][m];
        for (int[] row : effort) {
            Arrays.fill(row, (int) 1e9);
        }
        //source is always going to be (0,0)(top-left corner of grid) and
        //destination is always going to be (n-1,m-1)(bottom-right corner of grid)
        //marking the effort to reach from source to itself as 0
        effort[0][0] = 0;
        PriorityQueue<Tuple> priorityQueue = new PriorityQueue<>((tuple1, tuple2) -> tuple1.distance - tuple2.distance);
        //initializing the priority queue with 0 as the effort of height and 0,0 as the source coordinates
        priorityQueue.add(new Tuple(0, 0, 0));
        int[] deltaRow = {-1, 0, +1, 0};
        int[] deltaColumn = {0, +1, 0, -1};
        //Performing Dijkstra's algorithm
        while (!priorityQueue.isEmpty()) {
            Tuple tuple = priorityQueue.peek();
            int currentCellDistance = tuple.distance;
            int row = tuple.row;
            int column = tuple.column;
            priorityQueue.remove();
            if (row == n - 1 && column == m - 1) {
                return currentCellDistance;
            }
            for (int i = 0; i < 4; i++) {
                int neighbouringRow = row + deltaRow[i];
                int neighbouringColumn = column + deltaColumn[i];
                if (neighbouringRow >= 0 && neighbouringRow < n && neighbouringColumn >= 0 && neighbouringColumn < m) {
                    int newEffort = Math.max(Math.abs(heights[row][column] - heights[neighbouringRow][neighbouringColumn]), currentCellDistance);
                    if (effort[neighbouringRow][neighbouringColumn] > newEffort) {
                        effort[neighbouringRow][neighbouringColumn] = newEffort;
                        priorityQueue.add(new Tuple(newEffort, neighbouringRow, neighbouringColumn));
                    }
                }
            }
        }
        return 0;
    }

    static class Tuple {
        int distance;
        int row;
        int column;

        public Tuple(int distance, int row, int column) {
            this.distance = distance;
            this.row = row;
            this.column = column;
        }
    }

    public static void main(String[] args) {
        PathWithMinimumEffort pwme = new PathWithMinimumEffort();
        int[][] heights = {
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        };
        System.out.println(pwme.minimumEffort(heights));
    }
}

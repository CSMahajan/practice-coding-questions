package Graph;

/*
Distance of nearest cell having 1

Given a binary grid of n*m. Find the distance of the nearest 1 in the grid for each cell.
The distance is calculated as |i1  - i2| + |j1 - j2|,
where i1, j1 are the row number and column number of the current cell,
and i2, j2 are the row number and column number of the nearest cell having value 1.
There should be the least one 1 in the grid.
Example 1:
Input: grid = {{0,1,1,0},{1,1,0,0},{0,0,1,1}}
Output: {{1,0,0,1},{0,0,1,1},{1,1,0,0}}
Explanation: The grid is-
0 1 1 0
1 1 0 0
0 0 1 1
0's at (0,0), (0,3), (1,2), (1,3), (2,0) and (2,1)
are at a distance of 1 from 1's at (0,1),(0,2), (0,2), (2,3), (1,0) and (1,1) respectively.
Example 2:
Input: grid = {{1,0,1},{1,1,0},{1,0,0}}
Output: {{0,1,0},{0,0,1},{0,1,2}}
Explanation: The grid is-
1 0 1
1 1 0
1 0 0
0's at (0,1), (1,2), (2,1) and (2,2) are at a
distance of 1, 1, 1 and 2 from 1's at (0,0), (0,2), (2,0) and (1,1) respectively.
*/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DistanceNearestCellHaving1 {

    //Time Complexity: O(NxM + NxMx4) ~ O(N x M)
    //For the worst case, the BFS function will be called for (N x M) nodes, and for every node,
    //we are traversing for 4 neighbors, so it will take O(N x M x 4) time.
    //Space Complexity: O(N x M) + O(N x M) + O(N x M) ~ O(N x M)
    //O(N x M) for the visited array, distance matrix, and queue space takes up N x M locations at max.
    public int[][] nearest(int[][] grid) {
        // Code here
        int n = grid.length;
        int m = grid[0].length;
        int[][] visited = new int[n][m];
        int[][] distanceMatrix = new int[n][m];
        Queue<Node> queue = new LinkedList<>();
        //we will initially add all the cells of value as 1 in the queue with distance of nearest cell having 1 as 0
        //because these cell themselves are 1 and mark them as visited
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new Node(i, j, 0));
                    visited[i][j] = 1;
                } else {
                    visited[i][j] = 0;
                }
            }
        }
        //To calculate neighbouring row wise and column wise neighbours(diagonal neighbours not allowed)
        int[] deltaRow = {-1, 0, +1, 0};
        int[] deltaColumn = {0, +1, 0, -1};
        while (!queue.isEmpty()) {
            int row = queue.peek().row;
            int column = queue.peek().column;
            int distance = queue.peek().distance;
            //populating the distance required to reach that cell from nearest cell having 1 as value
            distanceMatrix[row][column] = distance;
            queue.remove();
            for (int i = 0; i < 4; i++) {
                int neighbouringRow = row + deltaRow[i];
                int neighbouringColumn = column + deltaColumn[i];
                if (neighbouringRow >= 0 && neighbouringRow < n && neighbouringColumn >= 0 && neighbouringColumn < m &&
                        visited[neighbouringRow][neighbouringColumn] == 0) {
                    //marking valid neighbour nodes as 1 and adding into queue with distance+1 as these are cell with 0 value
                    visited[neighbouringRow][neighbouringColumn] = 1;
                    queue.add(new Node(neighbouringRow, neighbouringColumn, distance + 1));
                }
            }
        }
        return distanceMatrix;
    }

    static class Node {
        int row;
        int column;
        int distance;

        public Node(int row, int column, int distance) {
            this.row = row;
            this.column = column;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        DistanceNearestCellHaving1 dnch = new DistanceNearestCellHaving1();
        int[][] grid = {{0,1,1,0},{1,1,0,0},{0,0,1,1}};
        int[][] distanceMatrix = dnch.nearest(grid);
        System.out.println(Arrays.deepToString(distanceMatrix));
    }
}

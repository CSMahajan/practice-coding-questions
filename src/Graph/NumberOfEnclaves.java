package Graph;

import java.util.LinkedList;
import java.util.Queue;

/*
Number Of Enclaves

You are given an n x m binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
A move consists of walking from one land cell to another adjacent (4-directionally) land cell
or walking off the boundary of the grid.
Find the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
Example 1:
Input:
grid[][] = {{0, 0, 0, 0},
            {1, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}}
Output:
3
Explanation:
0 0 0 0
1 0 1 0
0 1 1 0
0 0 0 0
The highlighted cells represents the land cells.
Example 2:
Input:
grid[][] = {{0, 0, 0, 1},
            {0, 1, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 1},
            {0, 1, 1, 0}}
Output:
4
Explanation:
0 0 0 1
0 1 1 0
0 1 1 0
0 0 0 1
0 1 1 0
The highlighted cells represents the land cells.
*/
public class NumberOfEnclaves {

    //Time Complexity: O(NxMx4) ~ O(N x M), For the worst case,
    //assuming all the pieces as land, the BFS function will be called for (N x M) nodes and for every node,
    //we are traversing for 4 neighbors, so it will take O(N x M x 4) time.
    //Space Complexity ~ O(N x M), O(N x M) for the visited array, and queue space takes up N x M locations at max.
    int numberOfEnclaves(int[][] grid) {
        // Your code here
        int n = grid.length;
        int m = grid[0].length;
        Queue<Pair> queue = new LinkedList<>();
        int[] deltaRow = {-1, 0, +1, 0};
        int[] deltaColumn = {0, +1, 0, -1};
        int[][] visited = new int[n][m];
        int offBoundaryLandCellCount = 0;
        //As 1 is land cell and 0 is sea cell, our required off boundary land cell
        //will neither be on the boundary nor neighbours to land cells which are boundary
        //so we will mark the land cell at the boundary as visited
        //and later subsequently through BFS we will mark their neighbours as visited
        //at last whoever remains (which are land cells and are not visited yet) will be added in our off boundary count
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //first row, first column, last row, last column respectively
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    if (grid[i][j] == 1) {
                        queue.add(new Pair(i, j));
                        visited[i][j] = 1;
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            int row = queue.peek().row;
            int column = queue.peek().column;
            queue.remove();
            for (int i = 0; i < 4; i++) {
                int neighbouringRow = row + deltaRow[i];
                int neighbouringColumn = column + deltaColumn[i];
                if (neighbouringRow >= 0 && neighbouringRow < n && neighbouringColumn >= 0 && neighbouringColumn < m &&
                        visited[neighbouringRow][neighbouringColumn] == 0 && grid[neighbouringRow][neighbouringColumn] == 1) {
                    queue.add(new Pair(neighbouringRow, neighbouringColumn));
                    visited[neighbouringRow][neighbouringColumn] = 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //1 is land cell and visited as 0 at last indicates these land cells are not connected to
                //any boundary land cell nor are the neighbours of such land cells
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    offBoundaryLandCellCount++;
                }
            }
        }
        return offBoundaryLandCellCount;
    }

    static class Pair {
        int row;
        int column;

        public Pair(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    public static void main(String[] args) {
        NumberOfEnclaves noe = new NumberOfEnclaves();
        int[][] grid = {{0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}};
        System.out.println(noe.numberOfEnclaves(grid));
    }

}

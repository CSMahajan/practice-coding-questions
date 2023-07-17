package Graph;

import java.util.LinkedList;
import java.util.Queue;

/*
Find the number of islands

GeeksForGeeks Problem Statement
Given a grid of size n*m (n is the number of rows and m is the number of columns in the grid)
consisting of '0's (Water) and '1's(Land). Find the number of islands.
Note: An island is either surrounded by water or boundary of grid and
is formed by connecting adjacent lands horizontally or vertically or diagonally i.e., in all 8 directions.
Example 1:
Input:
grid = {{0,1},{1,0},{1,1},{1,0}}
Output:
1
Explanation:
The grid is-
0 1
1 0
1 1
1 0
All lands are connected.
Example 2:
Input:
grid = {{0,1,1,1,0,0,0},{0,0,1,1,0,1,0}}
Output:
2
Explanation:
The grid is-
0 1 1 1 0 0 0
0 0 1 1 0 1 0
There are two islands :- one is colored in blue
and other in orange.

Number of Islands

LeetCode Problem Statement
Given an m x n 2D binary grid, grid which represents a map of '1's (land) and '0's (water),
return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.
Example 1:
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
*/
public class NumberOfIslands {

    static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    //Time Complexity: O(N*M)
    //Space Complexity: O(N*M)
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int noOfIslands = 0;
        int[][] visited = new int[n][m];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (visited[row][col] == 0 && grid[row][col] == '1') {
                    noOfIslands++;
                    bfs(row, col, visited, grid);
                }
            }
        }
        return noOfIslands;
    }

    private void bfs(int r, int c, int[][] visited, char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        //marking current grid as visited
        visited[r][c] = 1;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(r, c));
        while (!queue.isEmpty()) {
            int row = queue.peek().first;
            int column = queue.peek().second;
            queue.remove();
            //search for islands in the neighbouring positions in the grid
            for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
                for (int deltaColumn = -1; deltaColumn <= 1; deltaColumn++) {
                    //Below commented if condition is for avoiding neighbours diagonally
                    //Condition needed for leetcode solution
                    /*if ((Math.abs(deltaRow - deltaColumn) == 2 || Math.abs(deltaRow - deltaColumn) == 0) && deltaRow != 0 && deltaColumn != 0) {
                        continue;
                    }*/
                    int neighbouringRow = row + deltaRow;
                    int neighbouringColumn = column + deltaColumn;
                    if (neighbouringRow >= 0 && neighbouringRow < n && neighbouringColumn >= 0 && neighbouringColumn < m &&
                            visited[neighbouringRow][neighbouringColumn] == 0 && grid[neighbouringRow][neighbouringColumn] == '1') {
                        visited[neighbouringRow][neighbouringColumn] = 1;
                        queue.add(new Pair(neighbouringRow, neighbouringColumn));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        NumberOfIslands noi = new NumberOfIslands();
        System.out.println(noi.numIslands(grid));

        /*char[][] grid2 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(noi.numIslands(grid2));*/
    }
}

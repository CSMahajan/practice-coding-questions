package Graph;

import java.util.LinkedList;
import java.util.Queue;

/*
Rotting Oranges

You are given an m x n grid where each cell can have one of three values:
0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
Return the minimum number of minutes that must elapse until no cell has a fresh orange.
If this is impossible, return -1.
Example 1:
Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:
Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten,
because rotting only happens 4-directionally.
Example 3:
Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
*/
public class RottingOranges {

    //Time Complexity: O ( n x m ) x 4
    //Reason: Worst-case – We will be making each fresh orange rotten in the grid and
    //for each rotten orange will check in 4 directions
    //Space Complexity: O ( n x m )
    //Reason: worst-case –  If all oranges are Rotten,
    //we will end up pushing all rotten oranges into the Queue data structure
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int[][] visited = new int[n][m];
        Queue<Pair> queue = new LinkedList<>();
        int freshOrangesCount = 0;
        //initially we add all the rotten oranges in the queue and then find neighbouring fresh oranges which are represented by 1
        //and mark them as visited and rotten
        //all the neighbours of rotten oranges which are fresh oranges are marked as rotten and visited parallel in 1 unit(second) timeframe
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //grid value 2 indicates rotten orange
                if (grid[i][j] == 2) {
                    queue.add(new Pair(i, j, 0));
                    visited[i][j] = 2;
                } else {
                    visited[i][j] = 0;
                }
                if (grid[i][j] == 1) {
                    freshOrangesCount++;
                }
            }
        }
        int rottenOrangesCount = 0;
        int minimumTime = 0;
        int[] deltaRow = {-1, 0, +1, 0};
        int[] deltaColumn = {0, +1, 0, -1};
        //Performing BFS to traverse all the neighbouring oranges as rotten
        //DFS can not be used in this case because we want to mark them in minimum time
        //As in BFS(Breadth),all the neighbouring nodes can be reached simultaneously which is not possible in DFS(Depth)
        while (!queue.isEmpty()) {
            //One pair of triplet(row,column,time) indicates the row,column and the time required to mark that grid/cell as rotten
            int row = queue.peek().row;
            int column = queue.peek().column;
            int time = queue.peek().time;
            minimumTime = Math.max(time, minimumTime);
            queue.remove();
            for (int i = 0; i < 4; i++) {
                int neighbouringRow = row + deltaRow[i];
                int neighbouringColumn = column + deltaColumn[i];
                if (neighbouringRow >= 0 && neighbouringRow < n && neighbouringColumn >= 0 && neighbouringColumn < m &&
                        grid[neighbouringRow][neighbouringColumn] == 1 && visited[neighbouringRow][neighbouringColumn] == 0) {
                    queue.add(new Pair(neighbouringRow, neighbouringColumn, time + 1));
                    visited[neighbouringRow][neighbouringColumn] = 2;
                    rottenOrangesCount++;
                }
            }
        }
        //even though after marking all the fresh oranges as rotten and if count does not match for both of them
        //that means that fresh orange/oranges can not be reached or is not neighbour of any of rotten oranges,
        //or neighbouring to any fresh orange which is not neighbour of rotten orange
        if (rottenOrangesCount != freshOrangesCount) {
            return -1;
        }
        return minimumTime;
    }

static class Pair {
    int row, column, time;

    public Pair(int row, int column, int time) {
        this.row = row;
        this.column = column;
        this.time = time;
    }
}

    public static void main(String[] args) {
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        RottingOranges ro = new RottingOranges();
        System.out.println(ro.orangesRotting(grid));
        //int[][] grid2 = {{2,1,1},{0,1,1},{1,0,1}};
        //System.out.println(ro.orangesRotting(grid2));
    }
}

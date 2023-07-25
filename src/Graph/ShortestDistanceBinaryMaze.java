package Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Shortest Distance in a Binary Maze

Given a n * m matrix grid where each element can either be 0 or 1.
You need to find the shortest distance between a given source cell to a destination cell.
The path can only be created out of a cell if its value is 1.
If the path is not possible between source cell and destination cell, then return -1.
Note : You can move into an adjacent cell if that adjacent cell is filled with element 1.
Two cells are adjacent if they share a side. In other words, you can move in one of the four directions, Up, Down, Left and Right.
The source and destination cell are based on the zero based indexing.
Example 1:
Input:
grid[][] = {{1, 1, 1, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 1},
            {1, 1, 0, 0},
            {1, 0, 0, 1}}
source = {0, 1}
destination = {2, 2}
Output: 3
Explanation:
1 1 1 1
1 1 0 1
1 1 1 1
1 1 0 0
1 0 0 1
The highlighted part in the matrix denotes the
shortest path from source to destination cell.
Example 2:
Input:
grid[][] = {{1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0},
            {1, 0, 1, 0, 1}}
source = {0, 0}
destination = {3, 4}
Output: -1
Explanation:
The path is not possible between source and destination, hence return -1.
*/
public class ShortestDistanceBinaryMaze {

    //Time Complexity: O( 4*N*M )
    //{N*M are the total cells, for each of which we also check 4 adjacent nodes for the shortest path length},
    //where N = No. of rows of the binary maze and M = No. of columns of the binary maze.
    //Space Complexity: O( N*M )
    //where N = No. of rows of the binary maze and M = No. of columns of the binary maze.
    public int shortestPath(int[][] grid, int[] source, int[] destination) {
        // Your code here
        int n = grid.length;
        int m = grid[0].length;
        int sourceRow = source[0];
        int sourceColumn = source[1];
        int destinationRow = destination[0];
        int destinationColumn = destination[1];
        //source and destination are at the same cell then we need to return 0 otherwise this edge case will create problem ahead
        if (sourceRow == destinationRow && sourceColumn == destinationColumn) {
            return 0;
        }
        int[] deltaRow = {-1, 0, +1, 0};
        int[] deltaColumn = {0, +1, 0, -1};
        // Create a queue for storing cells with their distances from source
        // in the form {dist,{cell coordinates pair}}.
        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(0, sourceRow, sourceColumn));
        int[][] distance = new int[n][m];
        //Initially populating the distance required to reach each cell as INFINITY
        for (int[] row : distance) {
            Arrays.fill(row, (int) 1e9);
        }
        //marking the distance required to reach source cell from source cell itself as 0
        distance[sourceRow][sourceColumn] = 0;
        //Performing Dijkstra's algorithm BFS traversal
        //that is getting the element which is removed from the queue and look for its neighbouring cells with the distance condition
        while (!queue.isEmpty()) {
            Tuple tuple = queue.peek();
            queue.remove();
            int currentNodeDistance = tuple.distance;
            int row = tuple.row;
            int column = tuple.column;
            for (int i = 0; i < 4; i++) {
                int neighbouringRow = row + deltaRow[i];
                int neighbouringColumn = column + deltaColumn[i];
                if (neighbouringRow >= 0 && neighbouringRow < n && neighbouringColumn >= 0 && neighbouringColumn < m &&
                        grid[neighbouringRow][neighbouringColumn] == 1 && distance[neighbouringRow][neighbouringColumn] > 1 + currentNodeDistance) {
                    //below distance for neighbouring grid(cell) position's step needs to be done before pushing it into the queue and
                    //if condition to check against matching destination grid(cell) position
                    distance[neighbouringRow][neighbouringColumn] = 1 + currentNodeDistance;
                    queue.add(new Tuple(distance[neighbouringRow][neighbouringColumn], neighbouringRow, neighbouringColumn));
                    if (neighbouringRow == destinationRow && neighbouringColumn == destinationColumn) {
                        //We found the matching destination, need to return the distance required to reach that cell
                        return distance[neighbouringRow][neighbouringColumn];
                    }
                }
            }
        }
        return -1;
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
        int[][] grid = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {1, 0, 0, 1}
        };
        int[] source = {0, 1};
        int[] destination = {2, 2};
        ShortestDistanceBinaryMaze sdbm = new ShortestDistanceBinaryMaze();
        System.out.println(sdbm.shortestPath(grid, source, destination));
    }
}

package Graph;

import java.util.ArrayList;
import java.util.HashSet;

/*
Number of Distinct Islands

Given a boolean 2D matrix grid of size n * m.
You have to find the number of distinct islands where a group of connected 1s (horizontally or vertically) forms an island.
Two islands are considered to be distinct if and only if one island is not equal to another (not rotated or reflected).
Example 1:
Input:
grid[][] = {{1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1}}
Output:
1
Explanation:
grid[][] = {{1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1}}
Same colored islands are equal.
We have 2 equal islands, so we have only 1 distinct island.
Example 2:
Input:
grid[][] = {{1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1},
            {1, 1, 0, 1, 1}}
Output:
3
Explanation:
grid[][] = {{1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1},
            {1, 1, 0, 1, 1}}
Same colored islands are equal.
We have 4 islands, but 2 of them are equal, So we have 3 distinct islands.
*/
public class NumberOfDistinctIslands {

    //Time Complexity:O(N*M*log(N*M)) + O(N*M*4)
    //O(N*M*4) for 4 dfs calls and O(N*M*log(N*M)) for 2 for loops call and adding into set requires logarithmic time
    //Space Complexity:O(N*M)+O(N*M)
    //one for visited 2-D array and another for hashset of arraylist of string
    public int countDistinctIslands(int[][] grid) {
        // Your Code here
        int n = grid.length;
        int m = grid[0].length;
        int[][] visited = new int[n][m];
        //Here set is used for identifying unique/distinct islands in the grid
        //To represent one unique distinct island we will use array list of string
        //which helps us in keeping the common string of row,column indexes of same island
        HashSet<ArrayList<String>> set = new HashSet<>();
        int[] deltaRow = {-1, 0, +1, 0};
        int[] deltaColumn = {0, +1, 0, -1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0 && grid[i][j] == 1) {
                    ArrayList<String> list = new ArrayList<>();
                    //if we find an island recursively traverse through its neighbours
                    //add their row,column indexes relative from base row and column indexes and mark them as visited
                    dfs(i, j, visited, grid, list, deltaRow, deltaColumn, n, m, i, j);
                    //add this unique island(1 or more(group of) island(s)) into set
                    //set will help in keeping only distinct islands
                    //as their relative row,column indexes are handled while adding it into arraylist of string
                    set.add(list);
                }
            }
        }
        return set.size();
    }

    private void dfs(int row, int column, int[][] visited, int[][] grid, ArrayList<String> list, int[] deltaRow, int[] deltaColumn, int n, int m, int baseRow, int baseColumn) {
        visited[row][column] = 1;
        //convertToString gives us "row column" string for which we will subtract baseRow and baseColumn,
        //so whenever any duplicate island comes with same configuration,
        //their common base indexes of row and column can be identified
        list.add(convertToString(row - baseRow, column - baseColumn));
        for (int i = 0; i < 4; i++) {
            int neighbouringRow = row + deltaRow[i];
            int neighbouringColumn = column + deltaColumn[i];
            if (neighbouringRow >= 0 && neighbouringRow < n && neighbouringColumn >= 0 && neighbouringColumn < m &&
                    visited[neighbouringRow][neighbouringColumn] == 0 && grid[neighbouringRow][neighbouringColumn] == 1) {
                dfs(neighbouringRow, neighbouringColumn, visited, grid, list, deltaRow, deltaColumn, n, m, baseRow, baseColumn);
            }
        }
    }

    private String convertToString(int row, int column) {
        return row + " " + column;
    }

    public static void main(java.lang.String[] args) {
        int[][] grid = {{1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}};
        NumberOfDistinctIslands nodi = new NumberOfDistinctIslands();
        System.out.println(nodi.countDistinctIslands(grid));
    }

}

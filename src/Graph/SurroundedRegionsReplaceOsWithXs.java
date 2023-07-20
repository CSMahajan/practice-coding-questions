package Graph;

import java.util.Arrays;

/*
Replace O's with X's

Given a matrix mat of size N x M where every element is either O or X.
Replace all O with X that are surrounded by X.
A O (or a set of O) is considered to be surrounded by X
if there are X at locations just below, just above, just left and just right of it.
Example 1:
Input: n = 5, m = 4
mat = {{'X', 'X', 'X', 'X'},
       {'X', 'O', 'X', 'X'},
       {'X', 'O', 'O', 'X'},
       {'X', 'O', 'X', 'X'},
       {'X', 'X', 'O', 'O'}}
Output: ans = {{'X', 'X', 'X', 'X'},
               {'X', 'X', 'X', 'X'},
               {'X', 'X', 'X', 'X'},
               {'X', 'X', 'X', 'X'},
               {'X', 'X', 'O', 'O'}}
Explanation: Following the rule the above matrix is the resultant matrix.
*/
public class SurroundedRegionsReplaceOsWithXs {

    //Time Complexity: O(N) + O(M) + O(NxMx4) ~ O(N x M),
    //For the worst case, every element will be marked as ‘O’ in the matrix,
    //and the DFS function will be called for (N x M) nodes and for every node,
    //we are traversing for 4 neighbors, so it will take O(N x M x 4) time.
    //Also, we are running loops for boundary elements so it will take O(N) + O(M)
    //Space Complexity ~ O(N x M), O(N x M) for the visited array,
    //and auxiliary stack space takes up N x M locations at max.
    static char[][] fill(int n, int m, char[][] a) {
        // code here
        int[][] visited = new int[n][m];
        int[] deltaRow = {-1, 0, +1, 0};
        int[] deltaColumn = {0, +1, 0, -1};

        //marking boundary 'O' and its neighbours as visited
        //these 'O's will not be replaced with 'X's
        //recursively visiting all the neighbours through dfs traversal

        //visiting first row and last row
        for (int j = 0; j < m; j++) {
            //visiting first row
            if (visited[0][j] == 0 && a[0][j] == 'O') {
                dfs(0, j, visited, a, deltaRow, deltaColumn, n, m);
            }
            //visiting last row
            if (visited[n - 1][j] == 0 && a[n - 1][j] == 'O') {
                dfs(n - 1, j, visited, a, deltaRow, deltaColumn, n, m);
            }
        }

        //visiting first column and last column
        for (int i = 0; i < n; i++) {
            //visiting first column
            if (visited[i][0] == 0 && a[i][0] == 'O') {
                dfs(i, 0, visited, a, deltaRow, deltaColumn, n, m);
            }
            //visiting last column
            if (visited[i][m - 1] == 0 && a[i][m - 1] == 'O') {
                dfs(i, m - 1, visited, a, deltaRow, deltaColumn, n, m);
            }
        }

        //Replacing non boundary 'O's with 'X's
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0 && a[i][j] == 'O') {
                    a[i][j] = 'X';
                }
            }
        }
        return a;
    }

    private static void dfs(int row, int column, int[][] visited, char[][] a, int[] deltaRow, int[] deltaColumn, int n, int m) {
        visited[row][column] = 1;
        for (int i = 0; i < 4; i++) {
            int neighbouringRow = row + deltaRow[i];
            int neighbouringColumn = column + deltaColumn[i];
            if (neighbouringRow >= 0 && neighbouringRow < n && neighbouringColumn >= 0 && neighbouringColumn < m &&
                    visited[neighbouringRow][neighbouringColumn] == 0 && a[neighbouringRow][neighbouringColumn] == 'O') {
                dfs(neighbouringRow, neighbouringColumn, visited, a, deltaRow, deltaColumn, n, m);
            }
        }
    }

    public static void main(String[] args) {
        char[][] mat =
                {{'X', 'X', 'X', 'X'},
                        {'X', 'O', 'X', 'X'},
                        {'X', 'O', 'O', 'X'},
                        {'X', 'O', 'X', 'X'},
                        {'X', 'X', 'O', 'O'}};
        int n = 5, m = 4;
        System.out.println(Arrays.deepToString(fill(n, m, mat)));
    }
}

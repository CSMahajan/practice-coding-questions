package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Maximum Connected group

You are given an n x n binary grid. A grid is said to be binary if every value in grid is either 1 or 0.
You can change at most one cell in grid from 0 to 1.
You need to find the largest group of connected  1's.
Two cells are said to be connected if both are adjacent to each other and both have same value.
Example 1
Input:
2
1 1
0 1
Output: 4
Explanation:
By changing cell (2,1) ,we can obtain a connected group of 4 1's
1 1
1 1
Example 2
Input:
3
1 0 1
1 0 1
1 0 1
Output: 7
Explanation:
By changing cell (3,2) ,we can obtain a connected group of 7 1's
1 0 1
1 0 1
1 1 1
*/
public class LargestIsland {

    //Time Complexity: O(N2)+O(N2) ~ O(N2) where N = total number of rows of the grid.
    //Inside those nested loops, all the operations are taking apparently constant time.
    //So, O(N2) for the nested loop only, is the time complexity.
    //Space Complexity: O(2*N2) where N = the total number of rows of the grid.
    //This is for the two arrays i.e. parent array and size array of size N2 inside the Disjoint set.
    public int MaxConnection(int[][] grid) {
        //Your code here
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n * n);
        int[] deltaRow = {-1, 0, +1, 0};
        int[] deltaColumn = {0, +1, 0, -1};
        //Step 1: making the connected islands components to form disjoint set graph
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                if (grid[row][column] == 0) {
                    //sea cell(not an island cell), so need to continue
                    continue;
                }
                for (int i = 0; i < 4; i++) {
                    int adjacentRow = row + deltaRow[i];
                    int adjacentColumn = column + deltaColumn[i];
                    //check if neighbouring cell is valid(inside the grid) and if its a land cell
                    if (isValidCell(adjacentRow, adjacentColumn, n) && grid[adjacentRow][adjacentColumn] == 1) {
                        int currentNode = row * n + column;
                        int adjacentNode = adjacentRow * n + adjacentColumn;
                        if (ds.findUltimateParent(currentNode) != ds.findUltimateParent(adjacentNode)) {
                            ds.unionBySize(currentNode, adjacentNode);
                        }
                    }
                }
            }
        }

        int sizeOfMaximumIsland = 0;
        //Step 2: identifying the ultimate parents of adjacent land cells for every sea cells calculating in order to access size after connection(marking from 0 to 1)
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                if (grid[row][column] == 1) {
                    //an island cell(not a sea cell), so need to continue
                    continue;
                }
                Set<Integer> set = new HashSet<>();
                //need to find sea cell(value as 0) and check for neighbouring land cells and check the possible size after mergine
                //to identify the size of each newly connected island components,
                //we will store the ultimate parents of the ongoing cell to avoid duplicate counting of size
                for (int i = 0; i < 4; i++) {
                    int adjacentRow = row + deltaRow[i];
                    int adjacentColumn = column + deltaColumn[i];
                    if (isValidCell(adjacentRow, adjacentColumn, n) && grid[adjacentRow][adjacentColumn] == 1) {
                        int adjacentNode = adjacentRow * n + adjacentColumn;
                        //adding the ultimate parent of adjacent land cell
                        //set ensures unique ultimate parents
                        set.add(ds.findUltimateParent(adjacentNode));
                    }
                }
                //Calculating the size after connection from sea cell(which is now considered to be converted to land cell) to its adjacent land cell
                int totalSizeOfCurrentSeaCell = 0;
                for (Integer node : set) {
                    totalSizeOfCurrentSeaCell += ds.size.get(node);
                }
                //We are adding 1 to size because our current cell is sea cell and that we are converting to land cell
                sizeOfMaximumIsland = Math.max(sizeOfMaximumIsland, totalSizeOfCurrentSeaCell + 1);
            }
        }

        //Step 3:Identifying the maximum size from every node(either land or sea) in the grid
        for (int cell = 0; cell < n * n; cell++) {
            int ultimateParentOfCurrentCell = ds.findUltimateParent(cell);
            int sizeOfUltimateParentOfCurrentCell = ds.size.get(ultimateParentOfCurrentCell);
            sizeOfMaximumIsland = Math.max(sizeOfMaximumIsland, sizeOfUltimateParentOfCurrentCell);
        }
        return sizeOfMaximumIsland;
    }

    private boolean isValidCell(int adjacentRow, int adjacentColumn, int n) {
        return adjacentRow >= 0 && adjacentRow < n &&
                adjacentColumn >= 0 && adjacentColumn < n;
    }

    static class DisjointSet {
        List<Integer> size = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();

        public DisjointSet(int n) {
            for (int i = 0; i <= n; i++) {
                size.add(1);
                parent.add(i);
            }
        }

        public int findUltimateParent(int node) {
            if (node == parent.get(node)) {
                return node;
            }
            int parentOfNode = findUltimateParent(parent.get(node));
            parent.set(node, parentOfNode);
            return parent.get(node);
        }

        public void unionBySize(int u, int v) {
            int ultimateParentOf_U = findUltimateParent(u);
            int ultimateParentOf_V = findUltimateParent(v);
            if (ultimateParentOf_U == ultimateParentOf_V) {
                return;
            }
            int sizeOfUltimateParentOf_U = size.get(ultimateParentOf_U);
            int sizeOfUltimateParentOf_V = size.get(ultimateParentOf_V);
            if (sizeOfUltimateParentOf_U < sizeOfUltimateParentOf_V) {
                parent.set(ultimateParentOf_U, ultimateParentOf_V);
                size.set(ultimateParentOf_V, sizeOfUltimateParentOf_U + sizeOfUltimateParentOf_V);
            } else {
                parent.set(ultimateParentOf_V, ultimateParentOf_U);
                size.set(ultimateParentOf_U, sizeOfUltimateParentOf_U + sizeOfUltimateParentOf_V);
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 0, 1},
                {1, 0, 1},
                {1, 0, 1}
        };
        LargestIsland li = new LargestIsland();
        System.out.println(li.MaxConnection(grid));
    }
}

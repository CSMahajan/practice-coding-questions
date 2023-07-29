package Graph;

import java.util.ArrayList;
import java.util.List;

/*
Number Of Islands

You are given a n,m which means the row and column of the 2D matrix and an array of size k denoting the number of operations.
Matrix elements is 0 if there is water or 1 if there is land.
Originally, the 2D matrix is all 0 which means there is no land in the matrix.
The array has k operator(s) and each operator has two integer A[i][0], A[i][1]
means that you can change the cell matrix[A[i][0]][A[i][1]] from sea to island.
Return how many island are there in the matrix after each operation.You need to return an array of size k.
Note : An island means group of 1s such that they share a common side.
Example 1:
Input: n = 4
m = 5
k = 4
A = {{1,1},{0,1},{3,3},{3,4}}
Output: 1 1 2 2
Explanation:
0.  00000
    00000
    00000
    00000
1.  00000
    01000
    00000
    00000
2.  01000
    01000
    00000
    00000
3.  01000
    01000
    00000
    00010
4.  01000
    01000
    00000
    00011
Example 2:
Input: n = 4
m = 5
k = 4
A = {{0,0},{1,1},{2,2},{3,3}}
Output: 1 2 3 4
Explanation:
0.  00000
    00000
    00000
    00000
1.  10000
    00000
    00000
    00000
2.  10000
    01000
    00000
    00000
3.  10000
    01000
    00100
    00000
4.  10000
    01000
    00100
    00010
*/
public class NumberOfIslands2 {

    static class DisjointSet {
        List<Integer> parent = new ArrayList<>();
        List<Integer> size = new ArrayList<>();

        public DisjointSet(int n) {
            for (int i = 0; i <= n; i++) {
                parent.add(i);
                size.add(1);
            }
        }

        public int findUltimateParent(int node) {
            if (node == parent.get(node)) {
                return node;
            } else {
                int parentOfNode = findUltimateParent(parent.get(node));
                parent.set(node, parentOfNode);
                return parent.get(node);
            }
        }

        //Time Complexity:  The time complexity is O(4) which is very small and close to 1.
        //So, we can consider 4 as a constant.
        public void unionBySize(int u, int v) {
            int ultimateParentOf_U = findUltimateParent(u);
            int ultimateParentOf_V = findUltimateParent(v);
            if (ultimateParentOf_U == ultimateParentOf_V) {
                return;
            }
            int sizeOfUltimateParentOf_U = size.get(ultimateParentOf_U);
            int sizeOfUltimateParentOf_V = size.get(ultimateParentOf_V);
            if (size.get(ultimateParentOf_U) < size.get(ultimateParentOf_V)) {
                parent.set(ultimateParentOf_U, ultimateParentOf_V);
                size.set(ultimateParentOf_V, sizeOfUltimateParentOf_U + sizeOfUltimateParentOf_V);
            } else {
                parent.set(ultimateParentOf_V, ultimateParentOf_U);
                size.set(ultimateParentOf_U, sizeOfUltimateParentOf_U + sizeOfUltimateParentOf_V);
            }
        }
    }

    //Time Complexity: O(Q*4*alpha) ~ O(Q) where Q = no. of queries. The term 4*alpha is so small that it can be considered constant.
    //Space Complexity: O(Q) + O(N*M) + O(N*M), where Q = no. of queries, N = total no. of rows, M = total no. of columns.
    //The last two terms are for the parent and the size array used inside the Disjoint set data structure.
    //The first term is to store the answer.
    public List<Integer> numOfIslands(int n, int m, int[][] operators) {
        //Your code here
        DisjointSet ds = new DisjointSet(n * m);
        int[][] visited = new int[n][m];
        int countOfIslands = 0;
        List<Integer> noOfIslands = new ArrayList<>();
        int[] deltaRow = {-1, 0, +1, 0};
        int[] deltaColumn = {0, +1, 0, -1};
        int totalOperations = operators.length;
        //iterate over all the query operations given and dynamically make the graph
        //here every island node is made from row and column number which is numbered from 0 to n*m-1
        //so to understand any nodeNo for grid[row][column], we can access that using nodeNo = row*m+column,
        //where m is total number of columns in the given grid
        for (int i = 0; i < totalOperations; i++) {
            int row = operators[i][0];
            int column = operators[i][1];
            if (visited[row][column] == 1) {
                //that cell is already been visited(marked as an island), so no need to make it an island again
                noOfIslands.add(countOfIslands);
                continue;
            }
            //marking the given cell as visited
            visited[row][column] = 1;
            //increasing count by 1 to understand it as the different cell
            countOfIslands++;
            //look for neighbouring islands to the current island cell
            for (int j = 0; j < 4; j++) {
                int adjacentRow = row + deltaRow[j];
                int adjacentColumn = column + deltaColumn[j];
                //look for validity of the adjacent cell and unite them and modify the countOfIslands
                if (isValidAndVisitedCell(adjacentRow, adjacentColumn, n, m, visited)) {
                    int currentNode = row * m + column;
                    int adjacentNode = adjacentRow * m + adjacentColumn;
                    if (ds.findUltimateParent(currentNode) != ds.findUltimateParent(adjacentNode)) {
                        //if these cell nodes don't have same ultimate parent means these are not yet united
                        //so unite these nodes and reduce the count of total islands by 1 as they are merged islands
                        countOfIslands--;
                        ds.unionBySize(currentNode, adjacentNode);
                    }
                }
            }
            noOfIslands.add(countOfIslands);
        }
        return noOfIslands;
    }

    private boolean isValidAndVisitedCell(int adjacentRow, int adjacentColumn, int n, int m, int[][] visited) {
        return adjacentRow >= 0 && adjacentRow < n &&
                adjacentColumn >= 0 && adjacentColumn < m &&
                visited[adjacentRow][adjacentColumn] == 1;
    }

    public static void main(String[] args) {
        int n = 4;
        int m = 5;
        int[][] A = {{1, 1}, {0, 1}, {3, 3}, {3, 4}};
        NumberOfIslands2 noi2 = new NumberOfIslands2();
        System.out.println(noi2.numOfIslands(n, m, A));
    }
}
package Graph;

import java.util.Arrays;

/*
Floyd Warshall

The problem is to find the shortest distances between every pair of vertices in a given edge-weighted directed graph.
The graph is represented as an adjacency matrix of size n*n. Matrix[i][j] denotes the weight of the edge from i to j.
If Matrix[i][j]=-1, it means there is no edge from i to j.
Do it in-place.
Example 1:
Input: matrix = {{0,25},{-1,0}}
Output: {{0,25},{-1,0}}
Explanation: The shortest distance between every pair is already given(if it exists).
Example 2:
Input: matrix = {{0,1,43},{1,0,6},{-1,-1,0}}
Output: {{0,1,7},{1,0,6},{-1,-1,0}}
Explanation: We can reach 2 from 0 as 0->1->2 and the cost will be 1+6=7 which is less than 43.
*/
public class FloydWarshallAlgorithm {

    //Time Complexity: O(V*V*V), as we have three nested loops each running for V times, where V = no. of vertices.
    //Space Complexity: O(V*V), where V = no. of vertices.
    //This space complexity is due to storing the adjacency matrix of the given graph.
    public void shortestDistance(int[][] matrix) {
        // Code here
        int n = matrix.length;
        //first 2 for loops to assign if some value is -1 then updating it to INFINITY
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = (int) 1e9;
                }
                if (i == j) {
                    matrix[i][j] = 0;
                }
            }
        }
        //outer k loop is via node loop, means through that k node, we need to calculate edge weight for any two vertices
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        //reversing INFINITY to -1 which we had done at starting
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == (int) 1e9) {
                    matrix[i][j] = -1;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 43}, {1, 0, 6}, {-1, -1, 0}};
        FloydWarshallAlgorithm fwa = new FloydWarshallAlgorithm();
        fwa.shortestDistance(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
package Graph;

import java.util.ArrayList;

/*
Number of Provinces

There are n cities. Some of them are connected, while some are not.
If city a is connected directly with city b, and city b is connected directly with city c,
then city a is connected indirectly with city c.
A province is a group of directly or indirectly connected cities and no other cities outside of the group.
You are given an n x n matrix isConnected where isConnected[i][j] = 1
if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
Return the total number of provinces.
Example 1:
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Example 2:
Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3
*/
public class NumberOfProvinces {

    //Time Complexity: O(N) + O(V+2E),
    //Where O(N) is for outer loop and inner loop runs in total a single DFS over entire graph,
    //and we know DFS takes a time of O(V+2E).
    //Space Complexity: O(N) + O(N),Space for recursion stack space and visited array.
    public int findCircleNum(int[][] isConnected) {
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
        int V = isConnected.length;
        for (int i = 0; i < V; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        // to change adjacency matrix to list
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                // self nodes are not considered
                if (isConnected[i][j] == 1 && i != j) {
                    adjacencyList.get(i).add(j);
                    adjacencyList.get(j).add(i);
                }
            }
        }
        boolean[] visited = new boolean[V];
        int noOfProvinces = 0;
        for (int currentNode = 0; currentNode < V; currentNode++) {
            if (!visited[currentNode]) {
                noOfProvinces++;
                dfs(currentNode, adjacencyList, visited);
            }
        }
        return noOfProvinces;
    }

    private void dfs(int node, ArrayList<ArrayList<Integer>> adjLs, boolean[] visited) {
        visited[node] = true;
        for (Integer neigbour : adjLs.get(node)) {
            if (!visited[neigbour]) {
                dfs(neigbour, adjLs, visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        NumberOfProvinces nop = new NumberOfProvinces();
        System.out.println(nop.findCircleNum(isConnected));
    }
}

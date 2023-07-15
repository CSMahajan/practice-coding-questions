package Graph;

import java.util.ArrayList;

/*
DFS of Graph

You are given a connected undirected graph. Perform a Depth First Traversal of the graph.
Note: Use a recursive approach to find the DFS traversal of the graph
starting from the 0th vertex from left to right according to the graph.
Example 1:
Input: V = 5 , adj = [[2,3,1] , [0], [0,4], [0], [2]]
Output: 0 2 4 3 1
Explanation:
0 is connected to 2, 3, 1.
1 is connected to 0.
2 is connected to 0 and 4.
3 is connected to 0.
4 is connected to 2.
so starting from 0, it will go to 2 then 4, and then 3 and 1. Thus dfs will be 0 2 4 3 1.
Example 2:
Input: V = 4, adj = [[1,3], [2,0], [1], [0]]
Output: 0 1 2 3
Explanation:
0 is connected to 1 , 3.
1 is connected to 0, 2.
2 is connected to 1.
3 is connected to 0.
so starting from 0, it will go to 1 then 2 then back to 0 then 0 to 3 thus dfs will be 0 1 2 3.
*/
public class DFS {

    // Function to return a list containing the DFS traversal of the graph.
    //Time Complexity: For an undirected graph, O(N) + O(2E), For a directed graph, O(N) + O(E),
    //Because for every node we are calling the recursive function once,
    //the time taken is O(N) and 2E is for total degrees as we traverse for all adjacent nodes.
    //Space Complexity: O(3N) ~ O(N), Space for dfs stack space, visited array and an adjacency list.
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        boolean[] visited = new boolean[V + 1];
        visited[0] = true;
        ArrayList<Integer> dfsTraversal = new ArrayList<>();
        dfs(0, adj, visited, dfsTraversal);
        return dfsTraversal;
    }

    private void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> dfsTraversal) {
        visited[node] = true;
        dfsTraversal.add(node);
        //getting neighbour nodes
        for (Integer neighbour : adj.get(node)) {
            if (!visited[neighbour]) {
                dfs(neighbour, adj, visited, dfsTraversal);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(3);
        adj.get(3).add(0);
        adj.get(2).add(4);
        adj.get(4).add(2);
        DFS dfs = new DFS();
        ArrayList<Integer> ans = dfs.dfsOfGraph(5, adj);
        for (Integer number : ans) {
            System.out.print(number + " ");
        }
    }
}

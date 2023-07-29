package Graph;

import java.util.ArrayList;
import java.util.Stack;

/*
Strongly Connected Components (Kosaraju's Algo)

Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges,
Find the number of strongly connected components in the graph.
Example 1:
Input:
Output:
3
Explanation:
We can clearly see that there are 3 Strongly Connected Components in the Graph
Example 2:
Input:
Output: 1
Explanation:
All the nodes are connected to each other. So, there's only one SCC.
*/
public class StronglyConnectedComponentsKosarajusAlgorithm {

    //Time Complexity: O(V+E) + O(V+E) + O(V+E) ~ O(V+E) , where V = no. of vertices, E = no. of edges.
    //The first step is a simple DFS, so the first term is O(V+E).
    //The second step of reversing the graph and the third step, containing DFS again, will take O(V+E) each.
    //Space Complexity: O(V)+O(V)+O(V+E), where V = no. of vertices, E = no. of edges.
    //Two O(V) for the visited array and the stack we have used. O(V+E) space for the reversed adjacent list.
    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        //code here
        //Kosaraju's algorithm works only on directed graphs
        //Step 1: Perform DFS on the directed graph according to their finishing time
        int[] visited = new int[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                dfs(i, adj, visited, stack);
            }
        }
        //Step 2: Reverse all the edges of the given directed graph
        ArrayList<ArrayList<Integer>> adjReverse = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjReverse.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            //In order to reuse visited array in step 3 for dfs traversal again, marking all the nodes as 0 in this step only
            visited[i] = 0;
            for (Integer adjacentNode : adj.get(i)) {
                adjReverse.get(adjacentNode).add(i);
            }
        }
        int noOfStronglyConnectedComponents = 0;
        //Step 3: Perform DFS again on the reversed directed graph from order in stack
        while (!stack.isEmpty()) {
            int node = stack.peek();
            stack.pop();
            if (visited[node] == 0) {
                //after reversal,if we can not reach the node means it is part of different strongly connected component
                //so increasing the count of strongly connected components(scc)
                noOfStronglyConnectedComponents++;
                dfsWithReversedDirectedGraph(node, adjReverse, visited);
            }
        }
        return noOfStronglyConnectedComponents;
    }

    private void dfsWithReversedDirectedGraph(int node, ArrayList<ArrayList<Integer>> adjReverse, int[] visited) {
        visited[node] = 1;
        for (int adjacentNode : adjReverse.get(node)) {
            if (visited[adjacentNode] == 0) {
                dfsWithReversedDirectedGraph(adjacentNode, adjReverse, visited);
            }
        }
    }

    private void dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] visited, Stack<Integer> stack) {
        visited[node] = 1;
        for (int adjacentNode : adj.get(node)) {
            if (visited[adjacentNode] == 0) {
                dfs(adjacentNode, adj, visited, stack);
            }
        }
        stack.push(node);
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {
                {1, 0}, {0, 2},
                {2, 1}, {0, 3},
                {3, 4}
        };
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
        }
        StronglyConnectedComponentsKosarajusAlgorithm sccka = new StronglyConnectedComponentsKosarajusAlgorithm();
        System.out.println(sccka.kosaraju(n, adj));
    }
}
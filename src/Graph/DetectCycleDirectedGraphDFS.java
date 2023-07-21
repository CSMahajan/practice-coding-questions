package Graph;

import java.util.ArrayList;

/*
Detect cycle in a directed graph

Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges,
check whether it contains any cycle or not.
Example 1: graph with a cycle given
Output: 1
Explanation: 3 -> 3 is a cycle
Example 2: graph without a cycle given
Input:
Output: 0
Explanation: no cycle in the graph
*/
public class DetectCycleDirectedGraphDFS {

    //Time Complexity: O(V+E)+O(V) , where V = no. of nodes and E = no. of edges.
    //There can be at most V components. So, another O(V) time complexity.
    //Space Complexity: O(2N) + O(N) ~ O(2N): O(2N) for two visited arrays and O(N) for recursive stack space.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        int[] visited = new int[V];
        //in case of directed graph, pathVisited is required to check the node if reachable using same path that we are traversing
        int[] pathVisited = new int[V];
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                //recursively check for every node (as starting node) if cycle is present
                if (checkCycleUsingDFS(i, adj, visited, pathVisited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkCycleUsingDFS(int node, ArrayList<ArrayList<Integer>> adj, int[] visited, int[] pathVisited) {
        visited[node] = 1;
        pathVisited[node] = 1;
        for (int adjacentNode : adj.get(node)) {
            if (visited[adjacentNode] == 0) {
                if (checkCycleUsingDFS(adjacentNode, adj, visited, pathVisited)) {
                    return true;
                }
            }
            // if the node has been previously visited
            // but it has to be visited on the same path
            else if (pathVisited[adjacentNode] == 1) {
                //through the same path, we have reached this adjacent node, it indicates presence of cycle
                return true;
            }
        }
        //as through current node, no cycle found, so marking pathVisited for that node as 0
        //so as not to consider current node in cycle
        pathVisited[node] = 0;
        return false;
    }

    public static void main(String[] args) {
        int V = 11;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(3).add(7);
        adj.get(4).add(5);
        adj.get(5).add(6);
        adj.get(7).add(5);
        adj.get(8).add(9);
        adj.get(9).add(10);
        adj.get(10).add(8);
        DetectCycleDirectedGraphDFS dcdg = new DetectCycleDirectedGraphDFS();
        System.out.println(dcdg.isCyclic(V, adj));
    }
}
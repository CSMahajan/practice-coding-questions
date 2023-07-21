package Graph;

import java.util.ArrayList;
import java.util.List;

/*
Eventual Safe States

A directed graph of V vertices and E edges is given in the form of an adjacency list adj.
Each node of the graph is labelled with a distinct integer in the range 0 to V - 1.
A node is a terminal node if there are no outgoing edges.
A node is a safe node if every possible path starting from that node leads to a terminal node.
You have to return an array containing all the safe nodes of the graph.
The answer should be sorted in ascending order.
Example 1:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
Example 2:
Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
Output: [4]
Explanation:
Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
*/
public class EventualSafeStatesDFS {

    //Time Complexity: O(V+E)+O(V) , where V = no. of nodes and E = no. of edges.
    //There can be at most V components. So, another O(V) time complexity.
    //Space Complexity: O(2N) + O(N) ~ O(2N): O(2N) for two visited arrays and O(N) for recursive stack space.
    public List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
        // Your code here
        int[] visited = new int[V];
        //in case of directed graph, pathVisited is required to check the node if reachable using same path that we are traversing
        int[] pathVisited = new int[V];
        int[] checkSafeNodeVisited = new int[V];
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                //recursively check for every node (as starting node) if cycle is present
                checkCycleUsingDFS(i, adj, visited, pathVisited, checkSafeNodeVisited);
            }
        }
        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < checkSafeNodeVisited.length; i++) {
            if (checkSafeNodeVisited[i] == 1) {
                safeNodes.add(i);
            }
        }
        return safeNodes;
    }

    private boolean checkCycleUsingDFS(int node, List<List<Integer>> adj, int[] visited, int[] pathVisited, int[] checkSafeNodeVisited) {
        visited[node] = 1;
        pathVisited[node] = 1;
        //checkSafeNodeVisited is used to identify all the safe nodes, initialising every node starting with 0
        checkSafeNodeVisited[node] = 0;
        for (int adjacentNode : adj.get(node)) {
            if (visited[adjacentNode] == 0) {
                if (checkCycleUsingDFS(adjacentNode, adj, visited, pathVisited, checkSafeNodeVisited)) {
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
        //if at the end of the node, true is not returned means that
        //node is neither part of a cycle nor leads to cycle(no edge from that node towards any one of cycle nodes)
        checkSafeNodeVisited[node] = 1;
        pathVisited[node] = 0;
        return false;
    }

    public static void main(String[] args) {
        int V = 11;
        List<List<Integer>> adj = new ArrayList<>();
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
        EventualSafeStatesDFS ess = new EventualSafeStatesDFS();
        System.out.println(ess.eventualSafeNodes(V, adj));
    }
}
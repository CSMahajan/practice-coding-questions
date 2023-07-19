package Graph;

import java.util.ArrayList;

/*
Detect cycle in an undirected graph

Given an undirected graph with V vertices and E edges, check whether it contains any cycle or not.
Graph is in the form of adjacency list where adj[i] contains all the nodes ith node is having edge with.
Example 1:
Input:
V = 5, E = 5
adj = {{1}, {0, 2, 4}, {1, 3}, {2, 4}, {1, 3}}
Output: 1
Explanation:1->2->3->4->1 is a cycle.
Example 2:
Input:
V = 4, E = 2
adj = {{}, {2}, {1, 3}, {2}}
Output: 0
Explanation: No cycle in the graph.
*/
public class DetectCycleUndirectedGraphDFS {

    //Time Complexity: O(N + 2E) + O(N), Where N = Nodes, 2E is for total degrees as we traverse all adjacent nodes.
    //In the case of connected components of a graph, it will take another O(N) time.
    //Space Complexity: O(N) + O(N) ~ O(N), Space for recursive stack space and visited array.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        //We are using parent node as the source node
        //This is used to know if given node is already visited or not through another node
        //If so, it indicates the presence of a cycle in the graph
        int[] visited = new int[V];
        //In case of separate connected components,
        //calling for all components if cycle is present would give us result for cycle presence in whole graph
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                if (isCyclePresentFromNodeUsingDFS(i, -1, adj, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCyclePresentFromNodeUsingDFS(int sourceNode, int parentNode, ArrayList<ArrayList<Integer>> adj, int[] visited) {
        visited[sourceNode] = 1;
        //Looking in the neighbouring nodes if they are visited or not
        for (Integer adjacentNode : adj.get(sourceNode)) {
            if (visited[adjacentNode] == 0) {
                //if the adjacent node is not visited recursively check if all the neighbours are visited and reaches
                if (isCyclePresentFromNodeUsingDFS(adjacentNode, sourceNode, adj, visited)) {
                    return true;
                }
            } else if (adjacentNode != parentNode) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int V = 5, E = 5;
        //int[][] adj = {{1}, {0, 2, 4}, {1, 3}, {2, 4}, {1, 3}};
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(1).add(2);
        adj.get(1).add(4);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);
        adj.get(3).add(4);
        adj.get(4).add(1);
        adj.get(4).add(3);
        DetectCycleUndirectedGraphDFS dcugDFS = new DetectCycleUndirectedGraphDFS();
        System.out.println(dcugDFS.isCycle(V, adj));
    }
}

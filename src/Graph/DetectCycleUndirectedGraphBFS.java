package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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
public class DetectCycleUndirectedGraphBFS {

    //Time Complexity: O(N + 2E) + O(N), Where N = Nodes, 2E is for total degrees as we traverse all adjacent nodes.
    //In the case of connected components of a graph, it will take another O(N) time.
    //Space Complexity: O(N) + O(N) ~ O(N), Space for queue data structure and visited array.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        //We are using parent node as the source node
        //This is used to know if given node is already visited or not through another node
        //If so, it indicates the presence of a cycle in the graph
        int[] parent = new int[V];
        Arrays.fill(parent, -1);
        boolean[] visited = new boolean[V];
        //In case of separate connected components,
        //calling for all components if cycle is present would give us result for cycle presence in whole graph
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (isCyclePresentFromNodeUsingBFS(i, adj, visited, parent)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCyclePresentFromNodeUsingBFS(int source, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int[] parent) {
        Queue<Node> queue = new LinkedList<>();
        //our starting node is initialised with parent as -1
        queue.add(new Node(source, -1));
        visited[source] = true;
        while (!queue.isEmpty()) {
            int node = queue.peek().first;
            int parentNode = queue.peek().second;
            queue.remove();
            //Looking in the neighbouring nodes if they are visited or not
            for (Integer adjacentNode : adj.get(node)) {
                if (!visited[adjacentNode]) {
                    visited[adjacentNode] = true;
                    queue.add(new Node(adjacentNode, node));
                } else if (adjacentNode != parentNode) {
                    //This condition checks that the adjacent node is visited and its parent(source) node is different
                    //That means through bfs traversal, this node must have been visited through different node before
                    return true;
                }
            }
        }
        return false;
    }

    static class Node {
        int first, second;

        public Node(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        int V = 5, E = 5;
        //int[][] adj = {{1}, {0, 2, 4}, {1, 3}, {2, 4}, {1, 3}};
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            adj.add(new ArrayList < > ());
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
        DetectCycleUndirectedGraphBFS dcugBFS = new DetectCycleUndirectedGraphBFS();
        System.out.println(dcugBFS.isCycle(V,adj));
    }
}

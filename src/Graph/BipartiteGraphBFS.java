package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Bipartite Graph

Given an adjacency list of a graph adj  of V no. of vertices having 0 based index.
Check whether the graph is bipartite or not.
Example 1:
Input:
Output: 1
Explanation: The given graph can be colored in two colors so, it is a bipartite graph.
Example 2:
Input:
Output: 0
Explanation: The given graph cannot be colored in two colors such that color of adjacent vertices differ.
*/
public class BipartiteGraphBFS {

    //Time Complexity: O(V + 2E), Where V = Vertices, 2E is for total degrees as we traverse all adjacent nodes.
    //Space Complexity: O(3V) ~ O(V), Space for DFS stack space, colour array and an adjacency list.
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        int[] color = new int[V];
        //Initially marking every vertex's color as -1
        Arrays.fill(color, -1);
        //for connected components, need to add for loop as vertexes might not be connected
        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!isGraphBipartiteUsingBFS(i, V, color, adj)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isGraphBipartiteUsingBFS(int startingNode, int V, int[] color, ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startingNode);
        //we start with giving the startingNode's color as 0
        color[startingNode] = 0;
        while (!queue.isEmpty()) {
            int node = queue.peek();
            queue.remove();
            //check for all neighbours and color them with different color than current node's color
            for (int adjacentNode : adj.get(node)) {
                if (color[adjacentNode] == -1) {
                    //This adjacent node is not colored yet, so mark the color different from current node
                    color[adjacentNode] = 1 - color[node];
                    queue.add(adjacentNode);
                } else if (color[adjacentNode] == color[node]) {
                    //this node has been colored previously through some other node adjacency
                    //which violates the condition for bipartite graph(that every adjacent node must be of different colors)
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // V = 4, E = 4
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(0).add(3);
        adj.get(3).add(0);
        adj.get(1).add(3);
        adj.get(3).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);

        BipartiteGraphBFS bgBFS = new BipartiteGraphBFS();
        if (bgBFS.isBipartite(4, adj)) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }
}

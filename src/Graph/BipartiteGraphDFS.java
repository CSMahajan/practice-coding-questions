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
Input:graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
Output: false
Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
Example 2:
Input:graph = [[1,3],[0,2],[1,3],[0,2]]
Output: true
Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
*/
public class BipartiteGraphDFS {

    //Time Complexity: O(V + 2E), Where V = Vertices, 2E is for total degrees as we traverse all adjacent nodes.
    //Space Complexity: O(3V) ~ O(V), Space for DFS stack space, colour array and an adjacency list.
    //GeeksForGeeks solution
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        int[] color = new int[V];
        //Initially marking every vertex's color as -1
        Arrays.fill(color, -1);
        //for connected components, need to add for loop as vertexes might not be connected
        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!isGraphBipartiteUsingDFS(i, 0, color, adj)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isGraphBipartiteUsingDFS(int startingNode, int startColor, int[] color, ArrayList<ArrayList<Integer>> adj) {
        color[startingNode] = startColor;
        for (int adjacentNode : adj.get(startingNode)) {
            if (color[adjacentNode] == -1) {
                //This adjacent node is not colored yet, so mark the color different from current node
                if (!isGraphBipartiteUsingDFS(adjacentNode, 1 - color[startingNode], color, adj)) {
                    return false;
                }
            } else if (color[adjacentNode] == color[startingNode]) {
                //this node has been colored previously through some other node adjacency
                //which violates the condition for bipartite graph(that every adjacent node must be of different colors)
                return false;
            }
        }
        return true;
    }

    //LeetCode solution
    public boolean isBipartite(int[][] graph) {
        int V = graph.length;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int n = graph.length;
        int m = graph[0].length;
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> list = adj.get(i);
            for (int j = 0; j < graph[i].length; j++) {
                list.add(graph[i][j]);
            }
            adj.add(list);
        }
        int[] color = new int[V];
        //Initially marking every vertex's color as -1
        Arrays.fill(color, -1);
        //for connected components, need to add for loop as vertexes might not be connected
        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!isGraphBipartiteUsingDFS(i, 0, color, adj)) {
                    return false;
                }
            }
        }
        return true;
    }

    //Codestudio solution
    public boolean isGraphBirpatite(ArrayList<ArrayList<Integer>> edges) {
        // Write your code here
        int V = edges.size();
        int[] color = new int[V];
        //Initially marking every vertex's color as -1
        Arrays.fill(color, -1);
        int n = edges.size();
        int m = edges.get(0).size();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        //converting edges into adjacency list
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (edges.get(i).get(j) == 1) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }
        //for connected components, need to add for loop as vertexes might not be connected
        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!isGraphBipartiteUsingDFS(i, 0, color, adj)) {
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

        BipartiteGraphDFS bgBFS = new BipartiteGraphDFS();
        if (bgBFS.isBipartite(4, adj)) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }
}

package Graph;

import java.util.ArrayList;

/*
Articulation Point - I

Given an undirected connected graph with V vertices and adjacency list adj.
You are required to find all the vertices removing which
(and edges through it) disconnects the graph into 2 or more components.
Note: Indexing is zero-based i.e nodes numbering from (0 to V-1). There might be loops present in the graph.
Example 1:
Input:Graph image shown
Output:{1,4}
Explanation: Removing the vertex 1 will disconnect the graph as-Graph image shown
Removing the vertex 4 will disconnect the graph as-Graph image shown
*/
public class ArticulationPoint {

    private int timer = 0;

    //Time Complexity: O(V+2E),
    //where V = no. of vertices, E = no. of edges. It is because the algorithm is just a simple DFS traversal.
    //Space Complexity: O(3V), where V = no. of vertices.
    // O(3V) is for the three arrays i.e. tin, low, and vis, each of size V.
    public ArrayList<Integer> articulationPoints(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        int[] visited = new int[V];
        int[] timeOfInsertion = new int[V];
        int[] lowestTimeOfInsertion = new int[V];
        int[] markArticulationPoints = new int[V];
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                dfs(i, -1, adj, visited, timeOfInsertion, lowestTimeOfInsertion, markArticulationPoints);
            }
        }
        ArrayList<Integer> articulationPoints = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (markArticulationPoints[i] == 1) {
                //getting the vertices who have been marked as articulation points
                articulationPoints.add(i);
            }
        }
        if (articulationPoints.isEmpty()) {
            articulationPoints.add(-1);
        }
        return articulationPoints;
    }

    private void dfs(int node, int parent, ArrayList<ArrayList<Integer>> adj, int[] visited, int[] timeOfInsertion, int[] lowestTimeOfInsertion, int[] markArticulationPoints) {
        visited[node] = 1;
        timeOfInsertion[node] = lowestTimeOfInsertion[node] = timer;
        timer++;
        int noOfChild = 0;
        for (int adjacentNode : adj.get(node)) {
            if (adjacentNode == parent) {
                continue;
            }
            if (visited[adjacentNode] == 0) {
                dfs(adjacentNode, node, adj, visited, timeOfInsertion, lowestTimeOfInsertion, markArticulationPoints);
                lowestTimeOfInsertion[node] = Math.min(lowestTimeOfInsertion[node], lowestTimeOfInsertion[adjacentNode]);
                if (lowestTimeOfInsertion[adjacentNode] >= timeOfInsertion[node] && parent != -1) {
                    //as we have to reach the previous to previous of the current node from adjacent node
                    //above condition exactly checks that for non-first nodes(parent!=-1)
                    markArticulationPoints[node] = 1;
                }
                noOfChild++;
            } else {
                //we need to get minimum from lowestTimeOfInsertion of current node and timeOfInsertion of adjacentNode because
                //timeOfInsertion array actually states the actual time(or order) when this current node can be reached
                lowestTimeOfInsertion[node] = Math.min(lowestTimeOfInsertion[node], timeOfInsertion[adjacentNode]);
            }
        }
        //below is the separate case of marking starting node whose parent is -1
        //if it has more than 1 child means it is definitely an articulation point
        if (noOfChild > 1 && parent == -1) {
            markArticulationPoints[node] = 1;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {
                {0, 1}, {1, 4},
                {2, 4}, {2, 3}, {3, 4}
        };
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            int u = edges[i][0], v = edges[i][1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        ArticulationPoint ap = new ArticulationPoint();
        ArrayList<Integer> nodes = ap.articulationPoints(n, adj);
        System.out.println(nodes);
    }
}

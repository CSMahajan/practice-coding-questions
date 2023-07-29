package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Critical Connections in a Network

There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network
where connections[i] = [ai, bi] represents a connection between servers ai and bi.
Any server can reach other servers directly or indirectly through the network.
A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
Return all critical connections in the network in any order.
Example 1:
Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
Example 2:
Input: n = 2, connections = [[0,1]]
Output: [[0,1]]
*/
public class CriticalConnectionsInNetworkOrBridgesInGraphTarjansAlgorithm {

    private int timer = 1;

    //Time Complexity: O(V+2E), where V = no. of vertices, E = no. of edges.
    //It is because the algorithm is just a simple DFS traversal.
    //Space Complexity: O(V+2E) + O(3V),
    //where V = no. of vertices, E = no. of edges. O(V+2E) to store the graph in an adjacency list and
    //O(3V) for the three arrays i.e. tin, low, and vis, each of size V.
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        int m = connections.size();
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        //converting to adjacency list
        for (int i = 0; i < m; i++) {
            int u = connections.get(i).get(0);
            int v = connections.get(i).get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        int[] visited = new int[n];
        int[] timeOfInsertion = new int[n];
        int[] lowestTimeOfInsertion = new int[n];
        List<List<Integer>> bridges = new ArrayList<>();
        dfs(0, -1, adj, visited, timeOfInsertion, lowestTimeOfInsertion, bridges);
        return bridges;
    }

    private void dfs(int node, int parent, List<List<Integer>> adj, int[] visited, int[] timeOfInsertion, int[] lowestTimeOfInsertion, List<List<Integer>> bridges) {
        visited[node] = 1;
        timeOfInsertion[node] = lowestTimeOfInsertion[node] = timer;
        timer++;
        for (int adjacentNode : adj.get(node)) {
            if (adjacentNode == parent) {
                continue;
            }
            if (visited[adjacentNode] == 0) {
                dfs(adjacentNode, node, adj, visited, timeOfInsertion, lowestTimeOfInsertion, bridges);
                lowestTimeOfInsertion[node] = Math.min(lowestTimeOfInsertion[node], lowestTimeOfInsertion[adjacentNode]);
                if (lowestTimeOfInsertion[adjacentNode] > timeOfInsertion[node]) {
                    //it is a bridge in a graph/critical connection
                    bridges.add(Arrays.asList(node, adjacentNode));
                }
            } else {
                lowestTimeOfInsertion[node] = Math.min(lowestTimeOfInsertion[node], lowestTimeOfInsertion[adjacentNode]);
            }
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {
                {0, 1}, {1, 2},
                {2, 0}, {1, 3}
        };
        List<List<Integer>> connections = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            connections.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            connections.get(i).add(edges[i][0]);
            connections.get(i).add(edges[i][1]);
        }
        CriticalConnectionsInNetworkOrBridgesInGraphTarjansAlgorithm ccnTa = new CriticalConnectionsInNetworkOrBridgesInGraphTarjansAlgorithm();
        List<List<Integer>> bridges = ccnTa.criticalConnections(n, connections);
        System.out.println(bridges);
    }
}
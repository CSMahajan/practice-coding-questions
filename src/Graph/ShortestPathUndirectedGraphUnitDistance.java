package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Shortest path in Undirected Graph having unit distance

You are given an Undirected Graph having unit weight,
Find the shortest path from src to all the vertex and if it is unreachable to reach any vertex,
then return -1 for that vertex.
Example:
Input:
n = 9, m= 10
edges=[[0,1],[0,3],[3,4],[4 ,5]
,[5, 6],[1,2],[2,6],[6,7],[7,8],[6,8]]
src=0
Output:
0 1 2 1 2 3 3 4 4
*/
public class ShortestPathUndirectedGraphUnitDistance {

    //Time Complexity: O(M)+O(N+2M)+O(N)
    //O(M) { for creating the adjacency list from given list ‘edges’} +
    //O(N + 2M) { for the BFS Algorithm} +
    //O(N) { for adding the final values of the shortest path in the resultant array} ~ O(N+2M).
    //Where N= number of vertices and M= number of edges.
    //Space Complexity: O(N)+O(N)+O(N)+O(N+2M) ~ O(N+M)
    //O(N) {for the stack storing the BFS} +
    //O(N) {for the resultant array} +
    //O(N) {for the dist array storing updated shortest paths} +
    //O(N+2M) {for the adjacency list} ~ O(N+M) .
    //Where N= number of vertices and M= number of edges.
    public int[] shortestPath(int[][] edges, int n, int m, int src) {
        // Code here
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        //converting to adjacency list for given undirected graph with unit weight edge as distance
        for (int i = 0; i < m; i++) {
            int first = edges[i][0];
            int second = edges[i][1];
            adj.get(first).add(second);
            adj.get(second).add(first);
        }
        int[] distance = new int[n];
        //initializing the distance to reach all the nodes as INFINITY
        Arrays.fill(distance, (int) 1e9);
        //marking the distance to reach src from src as 0
        distance[src] = 0;
        //Performing simple BFS algorithm to traverse all the nodes
        //Here we didn't require to store weights as the distance between every neighbouring node is 1
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        while (!queue.isEmpty()) {
            int node = queue.peek();
            queue.remove();
            for (int adjacentNode : adj.get(node)) {
                if (distance[adjacentNode] > 1 + distance[node]) {
                    distance[adjacentNode] = 1 + distance[node];
                    queue.add(adjacentNode);
                }
            }
        }
        //Marking as -1 for nodes which are impossible to reach
        for (int i = 0; i < n; i++) {
            if (distance[i] == 1e9) {
                distance[i] = -1;
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        ShortestPathUndirectedGraphUnitDistance spugud = new ShortestPathUndirectedGraphUnitDistance();
        int n = 9, m = 10;
        int src = 0;
        int[][] edges = {
                {0, 1}, {0, 3},
                {3, 4}, {4, 5},
                {5, 6}, {1, 2},
                {2, 6}, {6, 7},
                {7, 8}, {6, 8}
        };
        System.out.println(Arrays.toString(spugud.shortestPath(edges, n, m, src)));
    }
}
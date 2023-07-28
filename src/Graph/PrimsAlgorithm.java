package Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Minimum Spanning Tree

Given a weighted, undirected and connected graph of V vertices and E edges.
The task is to find the sum of weights of the edges of the Minimum Spanning Tree.
Example 1:
Input:
3 3
0 1 5
1 2 3
0 2 1
Output: 4
Explanation: Image of resultant minimum spanning tree graph given.
The Spanning Tree resulting in a weight of 4 is shown above.
Example 2:
Input:
2 1
0 1 5
Output: 5
Explanation: Image of resultant minimum spanning tree graph given.
Only one Spanning Tree is possible which has a weight of 5.
*/
public class PrimsAlgorithm {

    //Time Complexity: O(E*logE) + O(E*logE)~ O(E*logE), where E = no. of given edges.
    //The maximum size of the priority queue can be E so after at most E iterations,
    //the priority queue will be empty and the loop will end.
    //Inside the loop, there is a pop operation that will take logE time.
    //This will result in the first O(E*logE) time complexity.
    //Now, inside that loop, for every node,
    //we need to traverse all its adjacent nodes where the number of nodes can be at most E.
    //If we find any node unvisited, we will perform a push operation and for that, we need a logE time complexity.
    //So this will result in the second O(E*logE).
    //Space Complexity: O(E) + O(V), where E = no. of edges and V = no. of vertices.
    //O(E) occurs due to the size of the priority queue and O(V) due to the visited array.
    //If we wish to get the mst, we need an extra O(V-1) space to store the edges of the most.
    public static int minimumSpanningTree(int V, int E, int[][] edges) {
        // Code Here.
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        //converting to adjacency list
        for (int i = 0; i < E; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            adj.get(u).add(new Pair(w, v));
            adj.get(v).add(new Pair(w, u));
        }
        int[] visited = new int[V];
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(pair -> pair.weight));
        //we will start from 0 with initialized weight of 0
        priorityQueue.add(new Pair(0, 0));
        int sumOfMinimumSpanningTree = 0;
        //Performing Prims algorithm
        while (!priorityQueue.isEmpty()) {
            int node = priorityQueue.peek().node;
            int weight = priorityQueue.peek().weight;
            priorityQueue.remove();
            if (visited[node] == 1) {
                continue;
            }
            //marking the current node as visited
            visited[node] = 1;
            sumOfMinimumSpanningTree += weight;
            for (Pair pair : adj.get(node)) {
                int adjacentNode = pair.node;
                int adjacentNodeEdgeWeight = pair.weight;
                if (visited[adjacentNode] == 0) {
                    priorityQueue.add(new Pair(adjacentNodeEdgeWeight, adjacentNode));
                }
            }
        }
        return sumOfMinimumSpanningTree;
    }

    static class Pair {
        int weight;
        int node;

        public Pair(int weight, int node) {
            this.weight = weight;
            this.node = node;
        }
    }

    public static void main(String[] args) {
        int V = 3, E = 3;
        int[][] edges = {
                {0, 1, 5},
                {1, 2, 3},
                {0, 2, 1}
        };
        System.out.println(PrimsAlgorithm.minimumSpanningTree(V, E, edges));
    }
}
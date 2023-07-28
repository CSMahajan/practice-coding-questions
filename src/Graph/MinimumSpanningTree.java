package Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Minimum Spanning Tree

You are given an undirected, connected and weighted graph G(V. E), consisting of V number of vertices
(numbered from 0 to V-1) and E number of edges.
Find and print the total weight of the Minimum Spanning Tree (MST) using Kruskal's algorithm.
By definition, a minimum weight spanning tree is a subset of the edges of a connected, edge-weighted undirected
graph that connects all the vertices together, without any cycles and with the minimum possible total edge weight.
Input 1:
4 4
0 1 3
0 3 5
1 2 1
2 3 8
Output 1: 9
Explanation:
The edge (2,3) having weight 8 will be excluded from the MST. The total weight of the MST then will be 1 + 3 + 5 = 9.
*/
public class MinimumSpanningTree {

    static class Pair {
        int weight;
        int node;

        public Pair(int weight, int node) {
            this.weight = weight;
            this.node = node;
        }
    }

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
    public static int minimumSpanningTree(ArrayList<ArrayList<Integer>> edges, int n) {
        //Your code goes here
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        //converting to adjacency list
        for (ArrayList<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int w = edge.get(2);
            adj.get(u).add(new Pair(w, v));
            adj.get(v).add(new Pair(w, u));
        }
        int[] visited = new int[n];
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

    public static void main(String[] args) {
        int n = 4;
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        ArrayList<Integer> edge1 = new ArrayList<>();
        edge1.add(0);
        edge1.add(1);
        edge1.add(3);
        edges.add(edge1);
        ArrayList<Integer> edge2 = new ArrayList<>();
        edge2.add(0);
        edge2.add(3);
        edge2.add(5);
        edges.add(edge2);
        ArrayList<Integer> edge3 = new ArrayList<>();
        edge3.add(1);
        edge3.add(2);
        edge3.add(1);
        edges.add(edge3);
        ArrayList<Integer> edge4 = new ArrayList<>();
        edge4.add(2);
        edge4.add(3);
        edge4.add(8);
        edges.add(edge4);
        System.out.println(MinimumSpanningTree.minimumSpanningTree(edges, n));
    }
}
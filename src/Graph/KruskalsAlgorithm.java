package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
public class KruskalsAlgorithm {

    static class DisjointSet {
        List<Integer> rank = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();

        public DisjointSet(int n) {
            for (int i = 0; i <= n; i++) {
                rank.add(0);
                parent.add(i);
            }
        }

        public int findUPar(int node) {
            if (node == parent.get(node)) {
                return node;
            }
            int ulp = findUPar(parent.get(node));
            parent.set(node, ulp);
            return parent.get(node);
        }

        public void unionBySize(int u, int v) {
            int ultimateParentOf_U = findUPar(u);
            int ultimateParentOf_V = findUPar(v);
            if (ultimateParentOf_U == ultimateParentOf_V) return;
            if (rank.get(ultimateParentOf_U) < rank.get(ultimateParentOf_V)) {
                parent.set(ultimateParentOf_U, ultimateParentOf_V);
            } else if (rank.get(ultimateParentOf_V) < rank.get(ultimateParentOf_U)) {
                parent.set(ultimateParentOf_V, ultimateParentOf_U);
            } else {
                parent.set(ultimateParentOf_V, ultimateParentOf_U);
                int rankU = rank.get(ultimateParentOf_U);
                rank.set(ultimateParentOf_U, rankU + 1);
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int _src, int _dest, int _wt) {
            this.src = _src;
            this.dest = _dest;
            this.weight = _wt;
        }

        @Override
        public int compareTo(Edge p2) {
            return this.weight - p2.weight;
        }
    }

    //Time Complexity: O(N+E) + O(E logE) + O(E*4α*2)   where N = no. of nodes and E = no. of edges.
    //O(N+E) for extracting edge information from the adjacency list.
    //O(E logE) for sorting the array consists of the edge tuples.
    //Finally, we are using the disjoint set operations inside a loop.
    //The loop will continue to E times. Inside that loop, there are two disjoint set operations like
    //findUPar() and UnionBySize() each taking 4 and so it will result in 4*2.
    //That is why the last term O(E*4*2) is added.
    //Space Complexity: O(N) + O(N) + O(E) where E = no. of edges and N = no. of nodes.
    //O(E) space is taken by the array that we are using to store the edge information.
    //And in the disjoint set data structure, we are using two N-sized arrays
    //i.e. a parent and a size array (as we are using unionBySize() function.
    public static int minimumSpanningTree(int V, int E, int[][] edges) {
        List<Edge> edgeList = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            int src = edges[i][0];
            int dest = edges[i][1];
            int weight = edges[i][2];
            Edge temp = new Edge(src, dest, weight);
            edgeList.add(temp);
        }

        DisjointSet ds = new DisjointSet(V);
        Collections.sort(edgeList);
        int mstWt = 0;

        for (Edge edge : edgeList) {
            int wt = edge.weight;
            int u = edge.src;
            int v = edge.dest;
            if (ds.findUPar(u) != ds.findUPar(v)) {
                mstWt += wt;
                ds.unionBySize(u, v);
            }
        }
        return mstWt;
    }

    public static void main(String[] args) {
        int V = 3, E = 3;
        int[][] edges = {
                {0, 1, 5},
                {1, 2, 3},
                {0, 2, 1}
        };
        System.out.println(KruskalsAlgorithm.minimumSpanningTree(V, E, edges));
    }
}

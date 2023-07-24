package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/*
Shortest path in Directed Acyclic Graph

Given a Directed Acyclic Graph of N vertices from 0 to N-1 and a 2D Integer array(or vector) edges[ ][ ] of length M,
where there is a directed edge from edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i.
Find the shortest path from src(0) vertex to all the vertices and if it is impossible to reach any vertex,
then return -1 for that vertex.
Example1:
Input:
N = 4, M = 2
edge = [[0,1,2],[0,2,1]
Output:
0 2 1 -1
Explanation:
Shortest path from 0 to 1 is 0->1 with edge weight 2.
Shortest path from 0 to 2 is 0->2 with edge weight 1.
There is no way we can reach 3, so it's -1 for 3.
Example2:
Input:
N = 6, M = 7
edge = [[0,1,2],[0,4,1],[4,5,4],[4,2,2],[1,2,3],[2,3,6],[5,3,1]]
Output:
0 2 3 6 1 5
Explanation:
Shortest path from 0 to 1 is 0->1 with edge weight 2.
Shortest path from 0 to 2 is 0->4->2 with edge weight 1+2=3.
Shortest path from 0 to 3 is 0->4->5->3 with edge weight 1+4+1=6.
Shortest path from 0 to 4 is 0->4 with edge weight 1.
Shortest path from 0 to 5 is 0->4->5 with edge weight 1+4=5.
*/
public class ShortestPathDirectedAcyclicGraph {

    //Time Complexity: O(N+M) {for the topological sort} + O(N+M)
    //{for relaxation of vertices, each node and its adjacent nodes get traversed} ~ O(N+M).
    //Where N= number of vertices and M= number of edges.
    //Space Complexity: O(N)+O(N)+O(N)+O(N+2M) ~ O(N+M)
    //O(N) {for the stack storing the topological sort}
    //O(N) {for storing the shortest distance for each node}
    //O(N) {for the visited array}
    //O(N+2M) {for the adjacency list} ~ O(N+M) .
    //Where N= number of vertices and M= number of edges.
    public int[] shortestPath(int N, int M, int[][] edges) {
        //Code here
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ArrayList<Pair> currentVertexList = new ArrayList<>();
            adj.add(currentVertexList);
        }
        //Converting edges 2D array with weights to the adjacency list with weights
        for (int i = 0; i < M; i++) {
            int sourceVertex = edges[i][0];
            int destinationVertex = edges[i][1];
            int weight = edges[i][2];
            adj.get(sourceVertex).add(new Pair(destinationVertex, weight));
        }
        int[] visited = new int[N];
        Stack<Integer> stack = new Stack<>();
        //Performing topological sort for all nodes to find order of all the nodes in the directed graph
        //This is done to find the distance from that as starting (or source) node
        for (int i = 0; i < N; i++) {
            if (visited[i] == 0) {
                dfsTopologicalSort(i, adj, visited, stack);
            }
        }
        /*
        Stack used in Topological Sort contains the ordered list of nodes where source node is stored at the last
        */
        int[] distance = new int[N];
        //Initially marking the distance taken to reach all nodes as Infinity
        Arrays.fill(distance, (int) 1e9);
        //Require to have source node distance to reach itself as 0
        distance[0] = 0;
        while (!stack.isEmpty()) {
            int node = stack.peek();
            stack.pop();
            for (Pair adjNode : adj.get(node)) {
                int adjacentNode = adjNode.node;
                int weight = adjNode.weight;
                //If the distance required to reach adjacentNode exceeds the weight added to its previous node,
                //then updating it to minimize the distance for reaching adjacentNode
                if (distance[adjacentNode] > distance[node] + weight) {
                    distance[adjacentNode] = distance[node] + weight;
                }
            }
        }
        //if after updating the distance to reach all nodes,
        //any nodes is having value as 1e9 means that is impossible to reach
        //so marking it as -1 to denote the node as impossible to reach
        for (int i = 0; i < N; i++) {
            if (distance[i] == 1e9) {
                distance[i] = -1;
            }
        }
        return distance;
    }

    private void dfsTopologicalSort(int node, ArrayList<ArrayList<Pair>> adj, int[] visited, Stack<Integer> stack) {
        visited[node] = 1;
        for (Pair adjNode : adj.get(node)) {
            int adjacentNode = adjNode.node;
            if (visited[adjacentNode] == 0) {
                dfsTopologicalSort(adjacentNode, adj, visited, stack);
            }
        }
        stack.push(node);
    }

    static class Pair {
        int node;
        int weight;
        public Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        ShortestPathDirectedAcyclicGraph spdag = new ShortestPathDirectedAcyclicGraph();
        int N = 6, M = 7;
        int[][] edges = {
                {0, 1, 2},
                {0, 4, 1},
                {4, 5, 4},
                {4, 2, 2},
                {1, 2, 3},
                {2, 3, 6},
                {5, 3, 1}
        };
        System.out.println(Arrays.toString(spdag.shortestPath(N, M, edges)));
    }
}
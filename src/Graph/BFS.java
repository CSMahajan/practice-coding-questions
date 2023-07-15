package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
BFS of graph

Given a directed graph. The task is to do Breadth First Traversal of this graph starting from 0.
Note: One can move from node u to node v only if there's an edge from u to v.
Find the BFS traversal of the graph starting from the 0th vertex, from left to right according to the input graph.
Also, you should only take nodes directly or indirectly connected from Node 0 in consideration.
Example 1:
Input: Graph image given
Output: 0 1 2 3 4
Explanation:
0 is connected to 1 , 2 , 3.
2 is connected to 4.
so starting from 0, it will go to 1 then 2
then 3. After this 2 to 4, thus bfs will be
0 1 2 3 4.
Example 2:

Input: Graph image given
Output: 0 1 2
Explanation:
0 is connected to 1 , 2.
so starting from 0, it will go to 1 then 2,
thus bfs will be 0 1 2.
*/
public class BFS {

    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        ArrayList<Integer> bfs = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[V + 1];
        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            Integer currentVertexNode = queue.poll();
            bfs.add(currentVertexNode);
            // Get all adjacent vertices of the dequeued vertex node
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            for (Integer neighbour : adj.get(currentVertexNode)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
        return bfs;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(4);
        adj.get(4).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(1).add(3);
        adj.get(3).add(1);
        BFS bfs = new BFS();
        ArrayList<Integer> bfsTraversal = bfs.bfsOfGraph(5, adj);
        for (Integer number : bfsTraversal) {
            System.out.print(number + " ");
        }
    }
}

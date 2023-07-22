package Graph;

import java.util.*;

/*
Eventual Safe States

A directed graph of V vertices and E edges is given in the form of an adjacency list adj.
Each node of the graph is labelled with a distinct integer in the range 0 to V - 1.
A node is a terminal node if there are no outgoing edges.
A node is a safe node if every possible path starting from that node leads to a terminal node.
You have to return an array containing all the safe nodes of the graph.
The answer should be sorted in ascending order.
Example 1:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
Example 2:
Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
Output: [4]
Explanation:
Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
*/
public class EventualSafeStatesTopologicalSortBFS {

    //Time Complexity: O(V+E)+O(N*logN), where V = no. of nodes and E = no. of edges. This is a simple BFS algorithm.
    //Extra O(N*logN) for sorting the safeNodes array, where N is the number of safe nodes.
    //Space Complexity: O(N) + O(N) + O(N) ~ O(3N), O(N) for the indegree array,
    //O(N) for the queue data structure used in BFS(where N = no.of nodes),
    //and extra O(N) for the adjacency list to store the graph in a reversed order.
    public List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
        // Your code here
        //Reverse adjacency list is used for reversing the edges in directed graph
        List<List<Integer>> adjReverse = new ArrayList<>();
        int[] inDegree = new int[V];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> safeNodes = new ArrayList<>();
        /*
        Firstly we will created reverse adjacency list(basically reversing the edges)
        and calculate the inDegree of new graph, for every node whose inDegree is 0, we will add it to the queue
        and decrease the inDegree count for their neighbours
        */
        for (int i = 0; i < V; i++) {
            adjReverse.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            for (int adjacentNode : adj.get(i)) {
                // i -> adjacentNode is now changed to
                // adjacentNode -> i
                adjReverse.get(adjacentNode).add(i);
                inDegree[i]++;
            }
        }
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int node = queue.peek();
            safeNodes.add(node);
            queue.remove();
            for (int adjacentNode : adjReverse.get(node)) {
                //reducing the inDegree of adjacent node
                inDegree[adjacentNode]--;
                if (inDegree[adjacentNode] == 0) {
                    queue.add(adjacentNode);
                }
            }
        }
        Collections.sort(safeNodes);
        return safeNodes;
    }

    public static void main(String[] args) {
        int V = 11;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(3).add(7);
        adj.get(4).add(5);
        adj.get(5).add(6);
        adj.get(7).add(5);
        adj.get(8).add(9);
        adj.get(9).add(10);
        adj.get(10).add(8);
        EventualSafeStatesTopologicalSortBFS ess = new EventualSafeStatesTopologicalSortBFS();
        System.out.println(ess.eventualSafeNodes(V, adj));
    }
}
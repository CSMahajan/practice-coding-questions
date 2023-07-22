package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
Detect cycle in a directed graph

Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges,
check whether it contains any cycle or not.
Example 1: graph with a cycle given
Output: 1
Explanation: 3 -> 3 is a cycle
Example 2: graph without a cycle given
Input:
Output: 0
Explanation: no cycle in the graph
*/
public class DetectCycleDirectedGraphTopologicalSortKahnsAlgorithmBFS {

    //Time Complexity: O(V+E), where V = no. of nodes and E = no. of edges. This is a simple BFS algorithm.
    //Space Complexity: O(N) + O(N) ~ O(2N), O(N) for the indegree array,
    //and O(N) for the queue data structure used in BFS(where N = no.of nodes).
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int adjacentNode : adj.get(i)) {
                inDegree[adjacentNode]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        int countTopologicalSort = 0;
        while (!queue.isEmpty()) {
            int node = queue.peek();
            //increasing counter as virtually considering that we have added the current node into our topological array
            //since here we don't want topological sort and only want to detect presence of cycle in directed graph, counter suffices
            countTopologicalSort++;
            queue.remove();
            for (int adjacentNode : adj.get(node)) {
                inDegree[adjacentNode]--;
                if (inDegree[adjacentNode] == 0) {
                    queue.add(adjacentNode);
                }
            }
        }
        //If the elements added to the topological sort count matches that means that graph does not have a cycle
        //It proves the given graph is DAG (Directed Acyclic Graph)
        return countTopologicalSort != V;
    }

    public static void main(String[] args) {
        int V = 11;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
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
        DetectCycleDirectedGraphTopologicalSortKahnsAlgorithmBFS dcdg = new DetectCycleDirectedGraphTopologicalSortKahnsAlgorithmBFS();
        System.out.println(dcdg.isCyclic(V, adj));
    }
}

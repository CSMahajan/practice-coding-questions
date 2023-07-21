package Graph;

import java.util.*;

/*
Topological Sort

Given a Directed Acyclic Graph (DAG) with V vertices and E edges, Find any Topological Sorting of that Graph.
Example 1:
Input: Directed Acyclic Graph (DAG) given
Output:1
Explanation:
The output 1 denotes that the order is valid.
So, if you have, implemented your function correctly, then output would be 1 for all test cases.
One possible Topological order for the graph is 3, 2, 1, 0.
Example 2:
Input: Directed Acyclic Graph (DAG) given
Output:1
Explanation:
The output 1 denotes that the order is valid. So, if you have, implemented your function correctly, then output
would be 1 for all test cases. One possible Topological order for the graph is 5, 4, 2, 1, 3, 0.
*/
public class TopologicalSortKahnsAlgorithmBFS {

    //Time Complexity: O(V+E), where V = no. of nodes and E = no. of edges. This is a simple BFS algorithm.
    //Space Complexity: O(N) + O(N) ~ O(2N), O(N) for the indegree array,
    //and O(N) for the queue data structure used in BFS(where N = no.of nodes).
    public static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        // add your code here
        Queue<Integer> queue = new LinkedList<>();
        //in-degree array is incoming edge array for all the vertices
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int adjacentNode : adj.get(i)) {
                //increasing the count of every incoming edge to that vertex for every adjacent node of our current node
                indegree[adjacentNode]++;
            }
        }
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        int counter = 0;
        int[] topologicalSortArr = new int[V];
        //queue contains the elements whose in-degree is 0 and its order is maintained
        //so adding it to topological sort order
        while (!queue.isEmpty()) {
            int node = queue.peek();
            topologicalSortArr[counter++] = node;
            queue.remove();
            //node is now in our topological order, so remove it from in-degree
            for (int adjacentNode : adj.get(node)) {
                indegree[adjacentNode]--;
                //after reducing the indegree for the current adjacentNode, check if indegree is 0 means that can now be part of topological sort order
                if (indegree[adjacentNode] == 0) {
                    queue.add(adjacentNode);
                }
            }
        }
        return topologicalSortArr;
    }

    public static void main(String[] args) {
        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(2).add(3);
        adj.get(3).add(1);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(5).add(0);
        adj.get(5).add(2);

        int[] topologicalSortOrder = TopologicalSortKahnsAlgorithmBFS.topoSort(V, adj);
        System.out.println(Arrays.toString(topologicalSortOrder));
    }
}
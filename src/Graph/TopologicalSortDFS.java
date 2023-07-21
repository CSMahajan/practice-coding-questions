package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

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
public class TopologicalSortDFS {

    //Time Complexity: O(V+E)+O(V), where V = no. of nodes and E = no. of edges.
    //There can be at most V components. So, another O(V) time complexity.
    //Space Complexity: O(2N) + O(N) ~ O(2N): O(2N) for the visited array and
    //the stack carried during DFS calls and O(N) for recursive stack space, where N = no. of nodes.
    public static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        // add your code here
        int[] visited = new int[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                dfs(i, visited, adj, stack);
            }
        }
        int counter = 0;
        int[] topologicalSortArr = new int[V];
        //stack contains the elements of the topological sort in a manner such that first node
        //which is completed dfs including neighbours is pushed into stack as the first node
        //so when we pop the given stack LIFO, it gives us topological order
        while (!stack.isEmpty()) {
            topologicalSortArr[counter++] = stack.peek();
            stack.pop();
        }
        return topologicalSortArr;
    }

    private static void dfs(int node, int[] visited, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack) {
        visited[node] = 1;
        //visiting all the adjacent nodes of current node
        for (int adjacentNode : adj.get(node)) {
            if (visited[adjacentNode] == 0) {
                dfs(adjacentNode, visited, adj, stack);
            }
        }
        //when the node and all its neighbouring nodes are done visiting,add them into the stack
        stack.push(node);
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

        int[] topologicalSortOrder = TopologicalSortDFS.topoSort(V, adj);
        System.out.println(Arrays.toString(topologicalSortOrder));
    }
}
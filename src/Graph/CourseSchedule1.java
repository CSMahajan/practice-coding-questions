package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
Prerequisite Tasks

There are a total of N tasks, labeled from 0 to N-1. Some tasks may have prerequisites,
for example to do task 0 you have to first complete task 1, which is expressed as a pair: [0, 1]
Given the total number of tasks N and a list of prerequisite pairs P, find if it is possible to finish all tasks.
Example 1:
Input:
N = 4, P = 3
prerequisites = {{1,0},{2,1},{3,2}}
Output:
Yes
Explanation:
To do task 1 you should have completed task 0, and to do task 2 you should have finished task 1,
and to do task 3 you should have finished task 2. So it is possible.
Example 2:
Input:
N = 2, P = 2
prerequisites = {{1,0},{0,1}}
Output:
No
Explanation:
To do task 1 you should have completed task 0, and to do task 0 you should have finished task 1. So it is impossible.
*/
public class CourseSchedule1 {

    //Time Complexity: O(V+E), where V = no. of nodes and E = no. of edges. This is a simple BFS algorithm.
    //Space Complexity: O(N) + O(N) ~ O(2N), O(N) for the indegree array,
    //and O(N) for the queue data structure used in BFS(where N = no.of nodes).
    public boolean isPossible(int N, int P, int[][] prerequisites) {
        // Your Code goes here
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int n = prerequisites.length;
        int V = N;
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            int firstTask = prerequisites[i][0];
            int secondTask = prerequisites[i][1];
            adj.get(firstTask).add(secondTask);
        }
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
        int counterTopologicalSort = 0;
        //queue contains the elements whose in-degree is 0 and its order is maintained
        //so adding it to topological sort order
        while (!queue.isEmpty()) {
            int node = queue.peek();
            counterTopologicalSort++;
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
        return counterTopologicalSort == V;
    }

    public static void main(String[] args) {
        int N = 4, P = 3;
        int[][] prerequisites = {{1, 0}, {2, 1}, {3, 2}};
        CourseSchedule1 cs1 = new CourseSchedule1();
        System.out.println(cs1.isPossible(N, P, prerequisites));
    }
}

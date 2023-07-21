package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Course Schedule

There are a total of n tasks you have to pick, labeled from 0 to n-1. Some tasks may have prerequisites tasks, for example to pick task 0 you have to first finish tasks 1, which is expressed as a pair: [0, 1]
Given the total number of n tasks and a list of prerequisite pairs of size m. Find a ordering of tasks you should pick to finish all tasks.
Note: There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all tasks, return an empty array. Returning any correct order will give the output as 1, whereas any invalid order will give the output "No Ordering Possible".


Example 1:

Input:
n = 2, m = 1
prerequisites = {{1, 0}}
Output:
1
Explanation:
The output 1 denotes that the order is valid. So, if you have, implemented your function correctly,
then output would be 1 for all test cases. One possible order is [0, 1].
Example 2:

Input:
n = 4, m = 4
prerequisites = {{1, 0},
                 {2, 0},
                 {3, 1},
                 {3, 2}}
Output:
1
Explanation:
There are a total of 4 tasks to pick.
To pick task 3 you should have finished both tasks 1 and 2.
Both tasks 1 and 2 should be picked after you finished task 0.
So one correct task order is [0, 1, 2, 3]. Another correct ordering is [0, 2, 1, 3].
Returning any of these order will result in a Output of 1.
*/
public class CourseSchedule2 {

    //Time Complexity: O(V+E), where V = no. of nodes and E = no. of edges. This is a simple BFS algorithm.
    //Space Complexity: O(N) + O(N) ~ O(2N), O(N) for the indegree array,
    //and O(N) for the queue data structure used in BFS(where N = no.of nodes).
    public static int[] findOrder(int n, int m, ArrayList<ArrayList<Integer>> prerequisites) {
        // add your code here
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int V = n;
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int firstTask = prerequisites.get(i).get(0);
            int secondTask = prerequisites.get(i).get(1);
            adj.get(secondTask).add(firstTask);
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
        int[] topologicalSortArr = new int[V];
        //queue contains the elements whose in-degree is 0 and its order is maintained
        //so adding it to topological sort order
        while (!queue.isEmpty()) {
            int node = queue.peek();
            topologicalSortArr[counterTopologicalSort++] = node;
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
        if (counterTopologicalSort == V) return topologicalSortArr;
        return new int[]{};
    }

    public static void main(String[] args) {
        int N = 4;
        int M = 3;
        ArrayList<ArrayList<Integer>> prerequisites = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            prerequisites.add(i, new ArrayList<>());
        }


        prerequisites.get(0).add(0);
        prerequisites.get(0).add(1);

        prerequisites.get(1).add(1);
        prerequisites.get(1).add(2);

        prerequisites.get(2).add(2);
        prerequisites.get(2).add(3);

        int[] topologicalSortCourses = CourseSchedule2.findOrder(N, M, prerequisites);
        System.out.println(Arrays.toString(topologicalSortCourses));
    }
}

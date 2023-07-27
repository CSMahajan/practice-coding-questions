package Graph;

import java.util.*;

/*
Bellman-Ford Algorithm

Distance from the Source (Bellman-Ford Algorithm)
Given a weighted, directed and connected graph of V vertices and E edges,
Find the shortest distance of all the vertex's from the source vertex S.
Note: If the Graph contains a negative cycle then return an array consisting of only -1.
Example 1:
Input:
E = [[0,1,9]]
S = 0
Output:
0 9
Explanation:
Shortest distance of all nodes from
source is printed.
Example 2:
Input:
E = [[0,1,5],[1,0,3],[1,2,-1],[2,0,1]]
S = 2
Output:
1 6 0
Explanation:
For nodes 2 to 0, we can follow the path->2-0. This has a distance of 1.
For nodes 2 to 1, we can follow the path->2-0-1, which has a distance of 1+5 = 6
*/
public class BellmanFordAlgorithm {

    //Time Complexity: O(V*E), where V = no. of vertices and E = no. of Edges.
    //Space Complexity: O(V) for the distance array which stores the minimized distances.
    public static int[] bellmanFord(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        // Write your code here
        int[] distance = new int[V];
        Arrays.fill(distance, (int) 1e8);
        //Marking the distance of S as 0
        distance[S] = 0;
        //In Bellman-Ford algorithm, we need to run exactly (V-1) times(total nodes{N} - 1) to recalculate the distance array values
        for (int i = 0; i < V - 1; i++) {
            for (ArrayList<Integer> edge : edges) {
                int sourceVertex = edge.get(0);
                int destinationVertex = edge.get(1);
                int edgeWeight = edge.get(2);
                if (distance[sourceVertex] != 1e8 && distance[destinationVertex] > distance[sourceVertex] + edgeWeight) {
                    distance[destinationVertex] = distance[sourceVertex] + edgeWeight;
                }
            }
        }
        //On n-th iteration, if the values are getting updated for any edge, then that means graph contains a negative cycle
        for (ArrayList<Integer> edge : edges) {
            int sourceVertex = edge.get(0);
            int destinationVertex = edge.get(1);
            int edgeWeight = edge.get(2);
            if (distance[sourceVertex] != 1e8 && distance[destinationVertex] > distance[sourceVertex] + edgeWeight) {
                //distance array value getting updated on n-th iteration,
                //it means there is a negative cycle in the given directed graph
                return new int[]{-1};
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        int V = 6;
        int S = 0;
        ArrayList<ArrayList<Integer>> edges = new ArrayList() {
            {
                add(new ArrayList<Integer>(Arrays.asList(3, 2, 6)));
                add(new ArrayList<Integer>(Arrays.asList(5, 3, 1)));
                add(new ArrayList<Integer>(Arrays.asList(0, 1, 5)));
                add(new ArrayList<Integer>(Arrays.asList(1, 5, -3)));
                add(new ArrayList<Integer>(Arrays.asList(1, 2, -2)));
                add(new ArrayList<Integer>(Arrays.asList(3, 4, -2)));
                add(new ArrayList<Integer>(Arrays.asList(2, 4, 3)));
            }
        };
        System.out.println(Arrays.toString(bellmanFord(V, edges, S)));
    }
}

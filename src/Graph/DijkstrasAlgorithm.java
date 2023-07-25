package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Dijkstra's Algorithm

Given a weighted, undirected and connected graph of V vertices and an adjacency list adj
where adj[i] is a list of lists containing two integers where the first integer of each list j
which denotes there is edge between i and j , second integers corresponds to the weight of that  edge .
You are given the source vertex S and You to Find the shortest distance of all the vertex's from the source vertex S.
You have to return a list of integers denoting the shortest distance between each node and Source vertex S.
Note: The Graph doesn't contain any negative weight cycle.
Example 1:
Input:
V = 2
adj [] = {{{1, 9}}, {{0, 9}}}
S = 0
Output:
0 9
Explanation:
The source vertex is 0. Hence, the shortest distance of node 0 is 0 and the shortest distance from node 1 is 9.
Example 2:
Input:
V = 3, E = 3
adj = {{{1, 1}, {2, 6}}, {{2, 3}, {0, 1}}, {{1, 3}, {0, 6}}}
S = 2
Output:
4 3 0
Explanation:
For nodes 2 to 0, we can follow the path->2-1-0. This has a distance of 1+3 = 4,
whereas the path 2-0 has a distance of 6. So, the Shortest path from 2 to 0 is 4.
The shortest distance from 0 to 1 is 1 .
*/
public class DijkstrasAlgorithm {

    //Time Complexity: O( E log(V) ), Where E = Number of edges and V = Number of Nodes.
    //Space Complexity: O( |E| + |V| ), Where E = Number of edges and V = Number of Nodes.
    public static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        // Write your code here
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((x,y) -> x.distance-y.distance);
        int[] distance = new int[V];
        Arrays.fill(distance, (int) 1e9);
        distance[S] = 0;
        priorityQueue.add(new Pair(0, S));
        while (!priorityQueue.isEmpty()) {
            int node = priorityQueue.peek().node;
            int currentNodeDistance = priorityQueue.peek().distance;
            priorityQueue.remove();
            for (int i = 0; i < adj.get(node).size(); i++) {
                int adjacentNode = adj.get(node).get(i).get(0);
                int adjacentNodeDistance = adj.get(node).get(i).get(1);
                if (distance[adjacentNode] > adjacentNodeDistance + currentNodeDistance) {
                    distance[adjacentNode] = adjacentNodeDistance + currentNodeDistance;
                    priorityQueue.add(new Pair(distance[adjacentNode], adjacentNode));
                }
            }
        }
        return distance;
    }

    static class Pair {
        int distance;
        int node;
        public Pair(int distance, int node) {
            this.distance = distance;
            this.node = node;
        }
    }

    public static void main(String[] args) {
        int V = 3, E=3,S=2;
        ArrayList<Integer> node1 = new ArrayList<Integer>() {{add(1);add(1);}};
        ArrayList<Integer> node2 = new ArrayList<Integer>() {{add(2);add(6);}};
        ArrayList<Integer> node3 = new ArrayList<Integer>() {{add(2);add(3);}};
        ArrayList<Integer> node4 = new ArrayList<Integer>() {{add(0);add(1);}};
        ArrayList<Integer> node5 = new ArrayList<Integer>() {{add(1);add(3);}};
        ArrayList<Integer> node6 = new ArrayList<Integer>() {{add(0);add(6);}};

        ArrayList<ArrayList<Integer>> inter1 = new ArrayList<ArrayList<Integer>>(){
            {
                add(node1);
                add(node2);
            }
        };
        ArrayList<ArrayList<Integer>> inter2 = new ArrayList<ArrayList<Integer>>(){
            {
                add(node3);
                add(node4);
            }
        };
        ArrayList<ArrayList<Integer>> inter3 = new ArrayList<ArrayList<Integer>>(){
            {
                add(node5);
                add(node6);
            }
        };
        ArrayList<ArrayList<ArrayList<Integer>>> adj= new ArrayList<ArrayList<ArrayList<Integer>>>(){
            {
                add(inter1); // for 1st node
                add(inter2); // for 2nd node
                add(inter3); // for 3rd node
            }
        };
        //add final values of adj here.
        int[] res= DijkstrasAlgorithm.dijkstra(V,adj,S);
        System.out.println(Arrays.toString(res));
    }
}

package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
Number of Ways to Arrive at Destination

You are in a city that consists of n intersections numbered from 0 to n - 1
with bidirectional roads between some intersections.
The inputs are generated such that you can reach any intersection from any other intersection and
that there is at most one road between any two intersections.
You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that
there is a road between intersections ui and vi that takes timei minutes to travel.
You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
Return the number of ways you can arrive at your destination in the shortest amount of time.
Since the answer may be large, return it modulo 109 + 7.
Example 1:
Input:
n=7, m=10
edges= [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
Output: 4
Explanation:
The four ways to get there in 7 minutes are:
- 0  6
- 0  4  6
- 0  1  2  5  6
- 0  1  3  5  6
Example 2:
Input:
n=6, m=8
edges= [[0,5,8],[0,2,2],[0,1,1],[1,3,3],[1,2,3],[2,5,6],[3,4,2],[4,5,2]]
Output: 3
Explanation:
The three ways to get there in 8 minutes are:
- 0  5
- 0  2  5
- 0  1  3  4  5
*/
public class NumberOfWaysToArriveAtDestination {

    //Time Complexity: O( E* log(V))
    //{ As we are using simple Dijkstra’s algorithm here, the time complexity will be or the order E*log(V)}
    //Where E = Number of edges and V = No. of vertices.
    //Space Complexity :  O(N) { for dist array + ways array + approximate complexity for priority queue }
    //Where, N = Number of nodes.
    public static int countPaths(int n, List<List<Integer>> roads) {
        // Your code here
        //Source node is always 0 and destination node is always n-1
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        //Creating adjacency list from given roads list
        for (List<Integer> list : roads) {
            int firstNode = list.get(0);
            int secondNode = list.get(1);
            int time = list.get(2);
            adj.get(firstNode).add(new Pair(time, secondNode));
            adj.get(secondNode).add(new Pair(time, firstNode));
        }
        //timeRequired denotes the node wise time taken to reach that node
        int[] timeRequired = new int[n];
        Arrays.fill(timeRequired, (int) 1e9);
        //numberOfWays denotes the node wise number of ways possible to reach that node
        int[] numberOfWays = new int[n];
        //time required from 0 to 0 is always 0
        timeRequired[0] = 0;
        //number of ways possible from 0 to reach 0 is only one way possible
        numberOfWays[0] = 1;
        int mod = (int) (1e9 + 7);
        //Creating priority queue based on time
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((pair1, pair2) -> pair1.time - pair2.time);
        //time required from 0 to 0 is always 0,so adding that as the initial pair in the priority queue
        priorityQueue.add(new Pair(0, 0));
        //Performing Dijkstra's algorithm
        while (!priorityQueue.isEmpty()) {
            int timeToReachCurrentNode = priorityQueue.peek().time;
            int node = priorityQueue.peek().node;
            priorityQueue.remove();
            for (Pair pair : adj.get(node)) {
                int adjacentNode = pair.node;
                int timeToReachAdjacentNode = pair.time;
                if (timeRequired[adjacentNode] > timeToReachCurrentNode + timeToReachAdjacentNode) {
                    //This is for the first time updating the timeRequired as we are coming first time with such short time
                    timeRequired[adjacentNode] = timeToReachCurrentNode + timeToReachAdjacentNode;
                    //pushing into the priority queue for the adjacent node
                    priorityQueue.add(new Pair(timeRequired[adjacentNode], adjacentNode));
                    //as the adjacent node is reached first time, the node through which it is reached,
                    //that many number of ways can only this node be reached
                    numberOfWays[adjacentNode] = numberOfWays[node];
                } else if (timeRequired[adjacentNode] == timeToReachCurrentNode + timeToReachAdjacentNode) {
                    //if some other way or node through this adjacent node has been reached, so we should now update the total number of ways by adding new path ways possible
                    numberOfWays[adjacentNode] = (numberOfWays[adjacentNode] + numberOfWays[node]) % mod;
                }
            }
        }
        //final answer is required for destination node which is n-1
        return numberOfWays[n - 1] % mod;
    }

    static class Pair {
        int time;
        int node;

        public Pair(int time, int node) {
            this.time = time;
            this.node = node;
        }
    }

    public static void main(String[] args) {
        int n = 7, m = 10;
        int[][] edges = {
                {0, 6, 7}, {0, 1, 2},
                {1, 2, 3}, {1, 3, 3},
                {6, 3, 3}, {3, 5, 1},
                {6, 5, 1}, {2, 5, 1},
                {0, 4, 5}, {4, 6, 2}
        };
        List<List<Integer>> roads = new ArrayList() {
            {
                add(new ArrayList<Integer>(Arrays.asList(0, 6, 7)));
                add(new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
                add(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
                add(new ArrayList<Integer>(Arrays.asList(1, 3, 3)));
                add(new ArrayList<Integer>(Arrays.asList(6, 3, 3)));
                add(new ArrayList<Integer>(Arrays.asList(3, 5, 1)));
                add(new ArrayList<Integer>(Arrays.asList(6, 5, 1)));
                add(new ArrayList<Integer>(Arrays.asList(2, 5, 1)));
                add(new ArrayList<Integer>(Arrays.asList(0, 4, 5)));
                add(new ArrayList<Integer>(Arrays.asList(4, 6, 2)));

            }
        };
        System.out.println(countPaths(n, roads));
    }
}

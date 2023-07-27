package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Network Delay Time

You are given a network of n nodes, labeled from 1 to n. You are also given times,
a list of travel times as directed edges times[i] = (ui, vi, wi),
where ui is the source node, vi is the target node, and
wi is the time it takes for a signal to travel from source to target.
We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal.
If it is impossible for all the n nodes to receive the signal, return -1.
Example 1:
Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2
Example 2:
Input: times = [[1,2,1]], n = 2, k = 1
Output: 1
Example 3:
Input: times = [[1,2,1]], n = 2, k = 2
Output: -1
*/
public class NetworkDelayTime {

    //Time Complexity: O( E* log(V))
    //{ As we are using simple Dijkstra’s algorithm here, the time complexity will be or the order E*log(V)}
    //Where E = Number of edges and V = No. of vertices.
    //Space Complexity :  O(N) { for dist array + ways array + approximate complexity for priority queue }
    //Where, N = Number of nodes.
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        //Converting to adjacency list
        for (int[] vertexEdges : times) {
            int firstVertex = vertexEdges[0];
            int secondVertex = vertexEdges[1];
            int time = vertexEdges[2];
            adj.get(firstVertex).add(new Pair(time, secondVertex));
        }
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(Comparator.comparingLong(pair -> pair.time));
        priorityQueue.add(new Pair(0, k));
        long[] timeRequired = new long[n + 1];
        Arrays.fill(timeRequired, (long) 1e15);
        timeRequired[0] = 0;
        //time required from k to k is 0
        timeRequired[k] = 0;
        //Performing Dijkstra's algorithm
        while (!priorityQueue.isEmpty()) {
            int node = priorityQueue.peek().node;
            long timeToReachCurrentNode = priorityQueue.peek().time;
            priorityQueue.remove();
            for (Pair pair : adj.get(node)) {
                int adjacentNode = pair.node;
                long timeToReachAdjacentNode = pair.time;
                if (timeRequired[adjacentNode] > timeToReachCurrentNode + timeToReachAdjacentNode) {
                    timeRequired[adjacentNode] = timeToReachCurrentNode + timeToReachAdjacentNode;
                    priorityQueue.add(new Pair(timeRequired[adjacentNode], adjacentNode));
                }
            }
        }
        int minimumTime = 0;
        for (int i = 1; i <= n; i++) {
            if (timeRequired[i] == 1e15) {
                return -1;
            }
            minimumTime = (int) Math.max(minimumTime, timeRequired[i]);
        }
        return minimumTime;
    }

    static class Pair {
        long time;
        int node;

        public Pair(long time, int node) {
            this.time = time;
            this.node = node;
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int k = 2;
        NetworkDelayTime ndt = new NetworkDelayTime();
        System.out.println(ndt.networkDelayTime(times, n, k));
    }
}
package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Cheapest Flights Within K Stops

There are n cities and m edges connected by some number of flights.
You are given an array flights where flights[i] = [from(i), toi, price(i)] indicates that
there is a flight from the city from(i) to city toi with cost price(i).
You are also given three integers src, dst, and k,
return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
Note: The price from city A to B may be different From price from city B to A.
Example 1:
Input:
n = 4
flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]]
src = 0
dst = 3
k = 1
Output:
700
Explanation:
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
*/
public class CheapestFlightsWithinKStops {

    //Time Complexity: O( N )
    //{ Additional log(N) of time eliminated here because we’re using a simple queue
    //rather than a priority queue which is usually used in Dijkstra’s Algorithm }.
    //Where N = Number of flights / Number of edges.
    //Space Complexity:  O( |E| + |V| ) { for the adjacency list, priority queue, and the dist array }.
    //Where E = Number of edges (flights.size()) and V = Number of Airports.
    public int CheapestFLight(int n, int[][] flights, int src, int dst, int k) {
        // Code here
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        int m = flights.length;
        //converting to adjacency list
        for (int i = 0; i < m; i++) {
            int sourceCity = flights[i][0];
            int destinationCity = flights[i][1];
            int flightPrice = flights[i][2];
            adj.get(sourceCity).add(new Pair(destinationCity, flightPrice));
        }
        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(0, src, 0));
        int[] price = new int[n];
        Arrays.fill(price, (int) 1e9);
        //price required for src to src is 0
        price[src] = 0;
        //Performing dijkstra's algorithm based on k stops instead of price
        while (!queue.isEmpty()) {
            Tuple tuple = queue.peek();
            int stops = tuple.stops;
            int currentCityNode = tuple.city;
            int cost = tuple.price;
            queue.remove();
            if (stops > k) {
                continue;
            }
            for (Pair pair : adj.get(currentCityNode)) {
                int adjacentCityNode = pair.cityNode;
                int costToReachAdjacentNode = pair.flightPrice;
                if (price[adjacentCityNode] > cost + costToReachAdjacentNode) {
                    price[adjacentCityNode] = cost + costToReachAdjacentNode;
                    queue.add(new Tuple(stops + 1, adjacentCityNode, price[adjacentCityNode]));
                }
            }
        }
        if (price[dst] == 1e9) {
            return -1;
        }
        return price[dst];
    }

    static class Pair {
        int cityNode;
        int flightPrice;

        public Pair(int cityNode, int flightPrice) {
            this.cityNode = cityNode;
            this.flightPrice = flightPrice;
        }
    }

    static class Tuple {
        int stops;
        int city;
        int price;

        public Tuple(int stops, int city, int price) {
            this.stops = stops;
            this.city = city;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        int src = 0;
        int dst = 3;
        int k = 1;
        CheapestFlightsWithinKStops cfwks = new CheapestFlightsWithinKStops();
        System.out.println(cfwks.CheapestFLight(n, flights, src, dst, k));
    }
}

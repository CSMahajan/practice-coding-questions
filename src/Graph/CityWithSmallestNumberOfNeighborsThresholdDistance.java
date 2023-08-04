package Graph;

import java.util.Arrays;

/*
City With the Smallest Number of Neighbors at a Threshold Distance

There are n cities numbered from 0 to n-1.
Given the array edges where edges[i] = [fromi , toi ,weighti]
represents a bidirectional and weighted edge between cities fromi and toi,
and given the integer distance Threshold.
You need to find out a city with the smallest number of cities that are reachable through some path
and whose distance is at most Threshold Distance,
If there are multiple such cities, our answer will be the city with the greatest number.
Note: that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.
Example 1:
Input:
N=4,M=4
edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]]
distanceThreshold = 4
Output:3
Explanation:
The neighboring cities at a distanceThreshold = 4 for each city are:
City 0 -> [City 1, City 2]
City 1 -> [City 0, City 2, City 3]
City 2 -> [City 0, City 1, City 3]
City 3 -> [City 1, City 2]
Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4,
but we have to return city 3 since it has the greatest number.
*/
public class CityWithSmallestNumberOfNeighborsThresholdDistance {

    //Time Complexity: O(V*V*V), as we have three nested loops each running for V times, where V = no. of vertices.
    //Space Complexity: O(V*V), where V = no. of vertices.
    //This space complexity is due to storing the adjacency matrix of the given graph.
    public int findCity(int n, int m, int[][] edges, int distanceThreshold) {
        
        int[][] distance = new int[n][n];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        //Preparing the adjacency matrix
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            distance[u][v] = w;
            distance[v][u] = w;
        }
        for (int i = 0; i < n; i++) {
            distance[i][i] = 0;
        }
        //Performing Floyd-Warshall algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distance[i][k] == Integer.MAX_VALUE || distance[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
        //below steps are done to find number of cities which satisfies the criteria of threshold distance
        //We need to find the cityNo(if multiple cities nodes have same minimum count of matching threshold distance,
        //we have to get the maximum node(city) among them
        int cityNo = -1;
        int countCity = n;
        for (int city = 0; city < n; city++) {
            int counter = 0;
            for (int adjacentCity = 0; adjacentCity < n; adjacentCity++) {
                if (distance[city][adjacentCity] <= distanceThreshold) {
                    counter++;
                }
            }
            if (countCity >= counter) {
                //We found the new city who fits within threshold and connects to lesser number of cities,
                //so need to store the count of such minimum cities connections and getting which is that city
                countCity = counter;
                cityNo = city;
            }
        }
        return cityNo;
    }

    public static void main(String[] args) {
        int N = 4, M = 4;
        int[][] edges = {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        int distanceThreshold = 4;
        CityWithSmallestNumberOfNeighborsThresholdDistance cd = new CityWithSmallestNumberOfNeighborsThresholdDistance();
        System.out.println(cd.findCity(N, M, edges, distanceThreshold));
    }
}

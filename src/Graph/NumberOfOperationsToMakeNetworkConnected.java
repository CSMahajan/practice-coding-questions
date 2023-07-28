package Graph;

import java.util.ArrayList;
import java.util.List;

/*
Number of Operations to Make Network Connected

There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network
where connections[i] = [ai, bi] represents a connection between computers ai and bi.
Any computer can reach any other computer directly or indirectly through the network.
You are given an initial computer network connections.
You can extract certain cables between two directly connected computers,
and place them between any pair of disconnected computers to make them directly connected.
Return the minimum number of times you need to do this in order to make all the computers connected.
If it is not possible, return -1.
Example 1:
Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
Example 2:
Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2
Example 3:
Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.
*/
public class NumberOfOperationsToMakeNetworkConnected {

    static class DisjointSet {

        List<Integer> parent = new ArrayList<>();
        List<Integer> size = new ArrayList<>();

        public DisjointSet(int n) {
            for (int i = 0; i <= n; i++) {
                parent.add(i);
                size.add(1);
            }
        }

        public int findUltimateParent(int node) {
            if (node == parent.get(node)) {
                return node;
            } else {
                int parentOfNode = findUltimateParent(parent.get(node));
                parent.set(node, parentOfNode);
                return parent.get(node);
            }
        }

        //Time Complexity:  The time complexity is O(4) which is very small and close to 1.
        //So, we can consider 4 as a constant.
        public void unionBySize(int u, int v) {
            int ultimateParentOf_U = findUltimateParent(u);
            int ultimateParentOf_V = findUltimateParent(v);
            if (ultimateParentOf_U == ultimateParentOf_V) {
                return;
            }
            int sizeOfUltimateParentOf_U = size.get(ultimateParentOf_U);
            int sizeOfUltimateParentOf_V = size.get(ultimateParentOf_V);
            if (size.get(ultimateParentOf_U) < size.get(ultimateParentOf_V)) {
                parent.set(ultimateParentOf_U, ultimateParentOf_V);
                size.set(ultimateParentOf_V, sizeOfUltimateParentOf_U + sizeOfUltimateParentOf_V);
            } else {
                parent.set(ultimateParentOf_V, ultimateParentOf_U);
                size.set(ultimateParentOf_U, sizeOfUltimateParentOf_U + sizeOfUltimateParentOf_V);
            }
        }
    }

    //Time Complexity: O(E*4*alpha)+O(N*4*alpha) where E = no. of edges and N = no. of nodes.
    //The first term is to calculate the number of extra edges and the second term is to count the number of components.
    //4*alpha is for the disjoint set operation we have used and this term is so small that it can be considered constant.
    //Space Complexity: O(2N) where N = no. of nodes.
    //2N for the two arrays(parent and size) of size N we have used inside the disjoint set.
    public int makeConnected(int n, int[][] connections) {
        DisjointSet ds = new DisjointSet(n);
        int m = connections.length;
        int noOfExtraEdges = 0;
        for (int i = 0; i < m; i++) {
            int u = connections[i][0];
            int v = connections[i][1];
            if (ds.findUltimateParent(u) == ds.findUltimateParent(v)) {
                //both the nodes have same ultimate parent, it means this edge can be removed
                noOfExtraEdges++;
            } else {
                //otherwise formulate the graph for disjoint set
                ds.unionBySize(u, v);
            }
        }
        //If any node's parent is itself, it means that node is ultimate parent for that connected component
        int noOfConnectedComponents = 0;
        for (int i = 0; i < n; i++) {
            if (ds.parent.get(i) == i) {
                noOfConnectedComponents++;
            }
        }
        //for n connected components to connect with each other, we require n-1 edges to connect them with each other
        int minimumConnections = noOfConnectedComponents - 1;
        if (minimumConnections <= noOfExtraEdges) {
            //in this problem, we can only remove the extra edges present in the connected components and
            //reuse them to connected other (still) not connected components
            return minimumConnections;
        } else {
            //otherwise it is not possible to connect all the components of the given graph
            return -1;
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] connections = {{0, 1}, {0, 2}, {0,3},{1, 2},{1,3}};
        NumberOfOperationsToMakeNetworkConnected nomnc = new NumberOfOperationsToMakeNetworkConnected();
        System.out.println(nomnc.makeConnected(n, connections));
    }
}
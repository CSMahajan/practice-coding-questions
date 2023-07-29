package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Maximum Stone Removal

There are n stones at some integer coordinate points on a 2D plane. Each coordinate point may have at most one stone.
You need to remove some stones.
A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone,
return the maximum possible number of stones that you can remove.
Example 1:
Input:
n=6
[[0 0] ,[ 0 1], [1 0] ,[1 2] ,[2 1] ,[2 2]]
Output: 5
Example:
One way to remove 5 stones are
1--[0,0]
2--[1,0]
3--[0,1]
4--[2,1]
5--[1,2]
*/
public class MaximumStoneRemoval {

    //Time Complexity: O(N), where N = total no. of stones.
    //Here we have just traversed the given stones array several times.
    //And inside those loops, every operation is apparently taking constant time.
    //So, the time complexity is only the time of traversal of the array.
    //Space Complexity: O(2* (max row index + max column index))
    //for the parent and size array inside the Disjoint Set data structure.
    public int maxRemove(int[][] stones, int n) {
        // Code here
        int maxRow = 0;
        int maxColumn = 0;
        for (int i = 0; i < n; i++) {
            maxRow = Math.max(maxRow, stones[i][0]);
            maxColumn = Math.max(maxColumn, stones[i][1]);
        }
        //DisjointSet of maxRow + maxColumn + 1 only required because it is not necessary that till last row or column, stones are present in the grid
        DisjointSet ds = new DisjointSet(maxRow + maxColumn + 1);
        Map<Integer, Integer> stoneNodesMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            //we are only creating nodeRow at location where stones are present
            int nodeRow = stones[i][0];
            //Here we are converting nodeColumn as row numbers after given rows are over
            //e.g.if n=4,m=3, we will create nodeRows from 0 to 3(n=4) and nodeColumns from 4 to 6(m=3)
            //so in above example, 0th column is identified by 4, 1st column by 5, 2nd column by 6
            int nodeColumn = stones[i][1] + maxRow + 1;
            //connecting the nodeRows and nodeColumns to identify them
            ds.unionBySize(nodeRow, nodeColumn);
            //In map,we will put as 1 because to get unique nodeRows and nodeColumns
            stoneNodesMap.put(nodeRow, 1);
            stoneNodesMap.put(nodeColumn, 1);
        }
        //so if there are 5 stones in 1 component, then we can remove 4 stones from them
        int noOfConnectedComponents = 0;
        for (Map.Entry<Integer, Integer> entry : stoneNodesMap.entrySet()) {
            if (ds.findUltimateParent(entry.getKey()) == entry.getKey()) {
                //nodes parent is itself means that is a component
                noOfConnectedComponents++;
            }
        }
        //By mathematical logic, we can derive that maximum (n - noOfConnectedComponents) stones can be removed
        return n - noOfConnectedComponents;
    }

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

    public static void main(String[] args) {
        int n = 6;
        int[][] stones = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        MaximumStoneRemoval msr = new MaximumStoneRemoval();
        System.out.println(msr.maxRemove(stones, n));
    }
}
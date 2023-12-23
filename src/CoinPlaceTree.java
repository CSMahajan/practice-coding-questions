/*
Find Number of Coins to Place in Tree Nodes

You are given an undirected tree with n nodes labeled from 0 to n - 1, and rooted at node 0. You are given a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

You are also given a 0-indexed integer array cost of length n, where cost[i] is the cost assigned to the ith node.

You need to place some coins on every node of the tree. The number of coins to be placed at node i can be calculated as:

If size of the subtree of node i is less than 3, place 1 coin.
Otherwise, place an amount of coins equal to the maximum product of cost values assigned to 3 distinct nodes in the subtree of node i. If this product is negative, place 0 coins.
Return an array coin of size n such that coin[i] is the number of coins placed at node i.



Example 1:


Input: edges = [[0,1],[0,2],[0,3],[0,4],[0,5]], cost = [1,2,3,4,5,6]
Output: [120,1,1,1,1,1]
Explanation: For node 0 place 6 * 5 * 4 = 120 coins. All other nodes are leaves with subtree of size 1, place 1 coin on each of them.
Example 2:


Input: edges = [[0,1],[0,2],[1,3],[1,4],[1,5],[2,6],[2,7],[2,8]], cost = [1,4,2,3,5,7,8,-4,2]
Output: [280,140,32,1,1,1,1,1,1]
Explanation: The coins placed on each node are:
- Place 8 * 7 * 5 = 280 coins on node 0.
- Place 7 * 5 * 4 = 140 coins on node 1.
- Place 8 * 2 * 2 = 32 coins on node 2.
- All other nodes are leaves with subtree of size 1, place 1 coin on each of them.
Example 3:


Input: edges = [[0,1],[0,2]], cost = [1,2,-2]
Output: [0,1,1]
Explanation: Node 1 and 2 are leaves with subtree of size 1, place 1 coin on each of them.
For node 0 the only possible product of cost is 2 * 1 * -2 = -4. Hence place 0 coins on node 0.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CoinPlaceTree {
    public static int[] placedCoins(int[][] edges, int[] cost) {
        List<Integer> ans = new ArrayList<>(Collections.nCopies(cost.length, 0));
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < cost.length; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        dfs(adj, cost, 0, -1, ans);

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private static List<Integer> dfs(List<List<Integer>> adj, int[] cost, int root, int par, List<Integer> ans) {
        List<Integer> usefulCost = new ArrayList<>(Collections.singletonList(cost[root]));

        for (int n : adj.get(root)) {
            if (n == par) continue;
            // go deep into the leaf nodes first
            List<Integer> v = dfs(adj, cost, n, root, ans);
            // accumulate all the cost from all its child nodes at the root level
            usefulCost.addAll(v);
        }

        // After traversing all subtrees, sort the accumulated cost
        usefulCost.sort(Collections.reverseOrder());
        int sz = usefulCost.size();

        // check if the size of the subtree is less than 3, then set the cost to 1 and return
        if (usefulCost.size() < 3) {
            ans.set(root, 1);
            return usefulCost;
        }

        //check if the product of the 2nd largest and 3rd largest is greater than
        //the product of the smallest two numbers
        if (usefulCost.get(1) * usefulCost.get(2) > usefulCost.get(sz - 1) * usefulCost.get(sz - 2)) {
            ans.set(root, usefulCost.get(0) * usefulCost.get(1) * usefulCost.get(2));
        } else {
            ans.set(root, usefulCost.get(0) * usefulCost.get(sz - 1) * usefulCost.get(sz - 2));
        }

        if (ans.get(root) < 0) {
            ans.set(root, 0);
        }

        if (usefulCost.size() <= 5) {
            return usefulCost;
        }

        // return the largest 3 and the smallest two items; only those can be useful in later steps, discard others
        return Arrays.asList(usefulCost.get(0), usefulCost.get(1), usefulCost.get(2),
                usefulCost.get(sz - 2), usefulCost.get(sz - 1));
    }

    public static void main(String[] args) {
        // Example usage:
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 6}, {2, 7}, {2, 8}};
        int[] cost = {1, 4, 2, 3, 5, 7, 8, -4, 2};

        // Call the placedCoins function
        int[] result = CoinPlaceTree.placedCoins(edges, cost);

        // Print the results
        System.out.println(Arrays.toString(result));
    }
}
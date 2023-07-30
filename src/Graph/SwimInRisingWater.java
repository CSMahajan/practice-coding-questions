package Graph;

/*
Swim in Rising Water

You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
The rain starts to fall. At time t, the depth of the water everywhere is t.
You can swim from a square to another 4-directionally adjacent square if and only if
the elevation of both squares individually are at most t.
You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
Example 1:
Input: grid = [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
Example 2:
Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation: The final route is shown.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
*/
public class SwimInRisingWater {

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int low = 0;
        int high = n * n - 1;
        int end = high;
        while (low < high) {
            int middle = (low + high) / 2;
            DisjointSet ds = new DisjointSet(n * n);
            for (int i = 0; i < n; i++) {
                boolean noUnion = true;
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] > middle) continue;
                    int node = i * n + j;
                    // right
                    if (j < n - 1 && grid[i][j + 1] <= middle) {
                        noUnion = false;
                        int rightAdjacentNode = i * n + j + 1;
                        ds.unionByRank(node, rightAdjacentNode);
                    }
                    // down
                    if (i < n - 1 && grid[i + 1][j] <= middle) {
                        noUnion = false;
                        int downAdjacentNode = (i + 1) * n + j;
                        ds.unionByRank(node, downAdjacentNode);
                    }
                }
                if (noUnion) break;
            }
            if (ds.isConnected(0, end)) {
                high = middle;
            } else {
                low = middle + 1;
            }
        }
        return low;
    }

    static class DisjointSet {

        private int count;
        private int[] parents;
        private int[] rank;

        public DisjointSet(int size) {
            this.count = size;
            parents = new int[count];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
            }
            rank = new int[count];
        }

        public int findUltimateParent(int i) {
            while (parents[i] != i) {
                parents[i] = parents[parents[i]];
                i = parents[i];
            }
            return parents[i];
        }

        public boolean unionByRank(int p, int q) {
            int parentOf_P = findUltimateParent(p);
            int parentOf_Q = findUltimateParent(q);
            if (parentOf_P == parentOf_Q) return false;
            if (rank[parentOf_P] < rank[parentOf_Q]) {
                parents[parentOf_P] = parentOf_Q;
            } else {
                if (rank[parentOf_P] == rank[parentOf_Q]) {
                    rank[parentOf_P]++;
                }
                parents[parentOf_Q] = parentOf_P;
            }
            return true;
        }

        public boolean isConnected(int p, int q) {
            int pp = findUltimateParent(p);
            int pq = findUltimateParent(q);
            return pp == pq;
        }

        public int getCount() {
            return this.count;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5},
                {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}};
        SwimInRisingWater sirw = new SwimInRisingWater();
        System.out.println(sirw.swimInWater(grid));
    }
}

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
        //we will find the minimum possible time represented through mid to go from source(0,0) to destination(n-1,n-1)
        while (low < high) {
            int mid = (low + high) / 2;
            DisjointSet ds = new DisjointSet(n * n);
            for (int row = 0; row < n; row++) {
                boolean noUnion = true;
                for (int column = 0; column < n; column++) {
                    if (grid[row][column] > mid) {
                        //current grid value is more than current time, it means we need to wait till mid(currentTime) reaches grid value
                        continue;
                    }
                    int node = row * n + column;
                    // right
                    if (column < n - 1 && grid[row][column + 1] <= mid) {
                        //same row, next column
                        noUnion = false;
                        int rightAdjacentNode = row * n + column + 1;
                        ds.unionByRank(node, rightAdjacentNode);
                    }
                    // down
                    if (row < n - 1 && grid[row + 1][column] <= mid) {
                        //next row, same column
                        noUnion = false;
                        int downAdjacentNode = (row + 1) * n + column;
                        ds.unionByRank(node, downAdjacentNode);
                    }
                }
                if (noUnion) break;
            }
            if (ds.isConnected(0, end)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    static class DisjointSet {

        private int count;
        private int[] parent;
        private int[] rank;

        public DisjointSet(int size) {
            this.count = size;
            parent = new int[count];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
            rank = new int[count];
        }

        public int findUltimateParent(int i) {
            while (parent[i] != i) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return parent[i];
        }

        public void unionByRank(int u, int v) {
            int parentOf_U = findUltimateParent(u);
            int parentOf_V = findUltimateParent(v);
            if (parentOf_U == parentOf_V) {
                return;
            }
            if (rank[parentOf_U] < rank[parentOf_V]) {
                parent[parentOf_U] = parentOf_V;
            } else {
                if (rank[parentOf_U] == rank[parentOf_V]) {
                    rank[parentOf_U]++;
                }
                parent[parentOf_V] = parentOf_U;
            }
        }

        public boolean isConnected(int u, int v) {
            int parentOf_U = findUltimateParent(u);
            int parentOf_V = findUltimateParent(v);
            return parentOf_U == parentOf_V;
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

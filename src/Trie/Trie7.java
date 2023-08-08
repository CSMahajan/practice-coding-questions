package Trie;

import java.util.*;

/*
Maximum XOR With an Element From Array

You are given an array nums consisting of non-negative integers.
You are also given a queries array, where queries[i] = [xi, mi].
The answer to the ith query is the maximum bitwise XOR value of xi and any element of nums that does not exceed mi.
In other words, the answer is max(nums[j] XOR xi) for all j such that nums[j] <= mi.
If all elements in nums are larger than mi, then the answer is -1.
Return an integer array answer where answer.length == queries.length and answer[i] is the answer to the ith query.
Example 1:
Input: nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
Output: [3,3,7]
Explanation:
1) 0 and 1 are the only two integers not greater than 1. 0 XOR 3 = 3 and 1 XOR 3 = 2. The larger of the two is 3.
2) 1 XOR 2 = 3.
3) 5 XOR 2 = 7.
Example 2:
Input: nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
Output: [15,-1,5]
*/
public class Trie7 {

    static class Tuple {
        int xorNumber;
        int maxXorLimitNumber;
        int queryIndex;

        public Tuple(int xorNumber, int maxXorLimitNumber, int queryIndex) {
            this.xorNumber = xorNumber;
            this.maxXorLimitNumber = maxXorLimitNumber;
            this.queryIndex = queryIndex;
        }
    }

    static class Node {
        Node[] links = new Node[2];

        public boolean containsKey(int bit) {
            return (links[bit] != null);
        }

        public Node get(int bit) {
            return links[bit];
        }

        public void put(int bit, Node node) {
            links[bit] = node;
        }
    }

    private static Node root;

    Trie7() {
        root = new Node();
    }

    public void insert(int number) {
        Node node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (number >> i) & 1;
            if (!node.containsKey(bit)) {
                node.put(bit, new Node());
            }
            node = node.get(bit);
        }
    }

    public int getMaximumXOR(int number) {
        Node node = root;
        int maximumXOR = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (number >> i) & 1;
            if (node.containsKey(1 - bit)) {
                node = node.get(1 - bit);
                maximumXOR = (1 << i) | maximumXOR;
            } else {
                node = node.get(bit);
            }
        }
        return maximumXOR;
    }

    //Time Complexity:O(M) + O(MlogM) + O(M*32 + N*32)
    //Space Complexity:O(32*N)
    //Reason: O(M) for creating a Vector/ArrayList of OfflineQueries,
    //O(MlogM) for sorting the offlineQueries,O(M*32 + N*32) for inserting the elements into a trie and
    //calculating the maxXor value.//32 since we are storing the integer in 32 bit format.
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        int m = queries.length;
        List<Tuple> offlineQueries = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int xorNumber = queries[i][0];
            int maxXorLimitNumber = queries[i][1];
            offlineQueries.add(new Tuple(xorNumber, maxXorLimitNumber, i));
        }
        //sorting the given queries in ascending order of max limit allowed for their numbers
        offlineQueries.sort(Comparator.comparingInt(o -> o.maxXorLimitNumber));
        int index = 0;
        int[] xorQueryResult = new int[m];
        Arrays.fill(xorQueryResult, -1);
        Trie7 trie = new Trie7();
        //we are storing into trie if the number in an array is less than or equal to maximum xor limit for queries
        //these queries are sorted in ascending order of their maximum xor limit number
        //this changes the time complexity from O(Q*n) to O(Q+n)
        //as we are only increasingly adding number in trie, so it is easier to get maximum XOR till its limit
        for (int i = 0; i < m; i++) {
            int maxXorLimitNumber = offlineQueries.get(i).maxXorLimitNumber;
            int xorNumber = offlineQueries.get(i).xorNumber;
            int queryIndex = offlineQueries.get(i).queryIndex;
            while (index < n && nums[index] <= maxXorLimitNumber) {
                //inserting into trie smaller or equal numbers to the limit
                trie.insert(nums[index]);
                index++;
            }
            if (index != 0) {
                //index increased means there are few nodes(elements of array) into the trie,
                //getting maximum xor among all possible range for our query number
                xorQueryResult[queryIndex] = trie.getMaximumXOR(xorNumber);
            } else {
                //if any index is not increased it means all the numbers in the array were larger than maximum limit allowed for the query
                //so saving the answer as -1 for that query
                xorQueryResult[queryIndex] = -1;
            }
        }
        return xorQueryResult;
    }



    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4};
        int[][] queries = {{3, 1}, {1, 3}, {5, 6}};
        Trie7 trie = new Trie7();
        System.out.println(Arrays.toString(trie.maximizeXor(nums, queries)));
    }
}
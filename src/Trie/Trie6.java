package Trie;

/*
Maximum XOR of two numbers in an array

Given an array of non-negative integers of size N.
Find the maximum possible XOR between two numbers present in the array.
Example 1:
Input:
Arr = {25, 10, 2, 8, 5, 3}
Output: 28
Explanation:
The maximum result is 5 ^ 25 = 28.
Example 2:
Input :
Arr = {1, 2, 3, 4, 5, 6, 7}
Output : 7
Explanation :
The maximum result is 1 ^ 6 = 7.
*/
public class Trie6 {

    static class Node {
        Node[] links = new Node[2];
        //2 positions in node links array indicate 0 and 1 which are required in binary representation of 32-bit representing a number

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

    Trie6() {
        root = new Node();
    }

    public void insert(int number) {
        Node node = root;
        for (int i = 31; i >= 0; i--) {
            //we want to check if i-th bit is set or not
            int bit = (number >> i) & 1;
            if (!node.containsKey(bit)) {
                //if it not set in trie, then create a new node for that bit(either 0 or 1) in the trie
                node.put(bit, new Node());
            }
            node = node.get(bit);
        }
    }

    public int getMaximumXORNumber(int number) {
        Node node = root;
        int maximumNumber = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (number >> i) & 1;
            //in order to find xor of two number at one trie node, if for the give number i-th bit is 0 then ,bit should be 1(complementary)
            //this is done to maximize the XOR number
            if (node.containsKey(1 - bit)) {
                //as for that i-th bit to be 1 to maximize the result, we can perform bitwise OR operation with 1 at i-th bit
                maximumNumber = maximumNumber | (1 << i);
                //if complementary bit reference trie is present, go to that reference trie node
                node = node.get(1 - bit);
            } else {
                //otherwise we don't have other option than to choose that bit as it is
                node = node.get(bit);
            }
        }
        return maximumNumber;
    }

    //TC:O(N*32) + O(N*32), first 32*N for inserting into trie, second 32*N for getting the maximum xor number from trie
    public int findMaximumXOR(int[] arr, int n) {
        //code here
        Trie6 trie = new Trie6();
        //first insert all the number in the trie
        for (int i = 0; i < n; i++) {
            trie.insert(arr[i]);
        }
        int maximumXORNumber = 0;
        //get the maximum for each number of array in the trie
        for (int i = 0; i < n; i++) {
            maximumXORNumber = Math.max(maximumXORNumber, trie.getMaximumXORNumber(arr[i]));
        }
        return maximumXORNumber;
    }

    public static void main(String[] args) {
        int[] arr = {25, 10, 2, 8, 5, 3};
        int n = arr.length;
        Trie6 trie = new Trie6();
        System.out.println(trie.findMaximumXOR(arr, n));
    }
}
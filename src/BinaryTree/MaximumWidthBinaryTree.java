package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/*
Maximum Width of Binary Tree

Given the root of a binary tree, return the maximum width of the given tree.
The maximum width of a tree is the maximum width among all levels.
The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
where the null nodes between the end-nodes that would be present in a complete binary tree
extending down to that level are also counted into the length calculation.
It is guaranteed that the answer will in the range of a 32-bit signed integer.
Example 1:
Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
Example 2:
Input: root = [1,3,2,5,null,null,9,6,null,7]
Output: 7
Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
Example 3:
Input: root = [1,3,2,5]
Output: 2
Explanation: The maximum width exists in the second level with length 2 (3,2).
*/
public class MaximumWidthBinaryTree {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class Pair {
        TreeNode node;
        int index;

        public Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    //Time Complexity: O(N)
    //Reason: We are doing a simple level order traversal.
    //The inner loop simply traverses the nodes level-wise and do not add to the complexity.
    //Space Complexity: O(N)
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxWidth = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        //Perform level order bfs traversal on each level
        //while doing so, reduce the indexes to avoid integer overflow of indexes
        while (!queue.isEmpty()) {
            int size = queue.size();
            int minimumIndex = queue.peek().index;
            int first = 0;
            int last = 0;
            //in order to avoid integer overflow, we will reduce the indexes for each nodes using minimum index
            //in that we only require indexes of first and last index on each level
            //iterate on the current level
            for (int i = 0; i < size; i++) {
                Pair pair = queue.peek();
                //reducing value of currentIndex to avoid integer overflow of indexes while putting into queue
                int currentIndex = pair.index - minimumIndex;
                TreeNode node = pair.node;
                queue.poll();
                //while iterating on the current level, at the start is first index element
                if (i == 0) {
                    first = currentIndex;
                }
                //while iterating on the current level, at the end is last index element
                if (i == size - 1) {
                    last = currentIndex;
                }
                //0-based indexing
                if (node.left != null) {
                    //left child of 0-based indexing for root i is 2*i+1
                    queue.add(new Pair(node.left, currentIndex * 2 + 1));
                }
                if (node.right != null) {
                    //right child of 0-based indexing for root i is 2*i+2
                    queue.add(new Pair(node.right, currentIndex * 2 + 2));
                }
            }
            //taking the maximum possible width on any level
            maxWidth = Math.max(maxWidth, last - first + 1);
        }
        return maxWidth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        MaximumWidthBinaryTree mwbt = new MaximumWidthBinaryTree();
        System.out.println(mwbt.widthOfBinaryTree(root));
    }
}
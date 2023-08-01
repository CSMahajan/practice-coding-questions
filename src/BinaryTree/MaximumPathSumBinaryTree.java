package BinaryTree;

/*
Maximum Path Sum

A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
The path sum of a path is the sum of the node's values in the path.
Given the root of a binary tree, return the maximum path sum of any non-empty path.
Example 1:
Input:
           3
         /    \
       4       5
      /  \
    -10   4
Output: 16
Explanation:
Maximum Sum lies between special node 4 and 5. Maximum Possible Sum = 4 + 4 + 3 + 5 = 16.
Example 2:
Input:
            -15
         /      \
        5         6
      /  \       / \
    -8    1     3   9
   /  \              \
  2   -3              0
                     / \
                    4  -1
                       /
                     10
Output:  27
Explanation:
The maximum possible sum from one special node to another is (3 + 6 + 9 + 0 + -1 + 10 = 27)
*/
public class MaximumPathSumBinaryTree {

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

    public int maxPathSum(TreeNode root) {
        int[] maximumPathSum = new int[1];
        maximumPathSum[0] = Integer.MIN_VALUE;
        //We are using array as pass by reference alternative
        maxPathSumSubTree(root,maximumPathSum);
        return maximumPathSum[0];
    }

    private int maxPathSumSubTree(TreeNode node, int[] maximumPathSum) {
        if (node == null) {
            return 0;
        }
        //for left and right subtree sum we need to take max comparing to 0 as some nodes might have negative data(value)
        //which makes the overall sum as negative
        //so to ignore such paths, need to take maximum compared to 0
        int leftSum = Math.max(0, maxPathSumSubTree(node.left, maximumPathSum));
        int rightSum = Math.max(0, maxPathSumSubTree(node.right, maximumPathSum));
        maximumPathSum[0] = Math.max(maximumPathSum[0], node.data + leftSum + rightSum);
        return node.data + Math.max(leftSum, rightSum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left= new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        MaximumPathSumBinaryTree mpsbt = new MaximumPathSumBinaryTree();
        System.out.println(mpsbt.maxPathSum(root));
    }
}
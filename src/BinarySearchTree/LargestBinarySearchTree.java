package BinarySearchTree;

/*
Largest BST

Given a binary tree. Find the size of its largest subtree that is a Binary Search Tree.
Note: Here Size is equal to the number of nodes in the subtree.
Example 1:
Input:
        1
      /   \
     4     4
   /   \
  6     8
Output: 1
Explanation: There's no subtree with size greater than 1 which forms a BST.
All the leaf Nodes are the BSTs with size equal
to 1.
Example 2:
Input: 6 6 3 N 2 9 3 N 8 8 2
            6
        /       \
       6         3
        \      /   \
         2    9     3
          \  /  \
          8 8    2
Output: 2
Explanation: The following subtree is a BST of size 2:
       2
    /    \
   N      8
*/
public class LargestBinarySearchTree {

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

    static class NodeValue {
        public int maxNode;
        public int minNode;
        public int maxSize;

        public NodeValue(int maxNode, int minNode, int maxSize) {
            this.maxNode = maxNode;
            this.minNode = minNode;
            this.maxSize = maxSize;
        }
    }

    public static int largestBst(TreeNode root) {
        // Write your code here
        return largestBST(root).maxSize;
    }

    //TC:O(N)
    //SC:O(1){O(N) if recursive stack space considered}
    private static NodeValue largestBST(TreeNode root) {
        // An empty tree is a BST of size 0.
        if (root == null) {
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }
        // Get values from left and right subtree of current tree.
        NodeValue left = largestBST(root.left);
        NodeValue right = largestBST(root.right);
        // Current node is greater than max in left AND smaller than min in right, it is a BST.
        if (left.maxNode < root.data && root.data < right.minNode) {
            // It is a BST.
            return new NodeValue(Math.min(root.data, left.minNode), Math.max(root.data, right.maxNode), 1 + left.maxSize + right.maxSize);
        }
        // Otherwise, return [-inf, inf] so that parent can't be valid BST
        return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.maxSize, right.maxSize));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        System.out.println(largestBst(root));
    }
}
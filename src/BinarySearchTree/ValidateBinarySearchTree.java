package BinarySearchTree;

/*
Validate Binary Search Tree

Given the root of a binary tree. Check whether it is a BST or not.
Note: We are considering that BSTs can not contain duplicate Nodes.
A BST is defined as follows:
The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
Input:
   2
 /    \
1      3
Output: 1
Explanation:
The left subtree of root node contains node with key lesser than the root nodes key and
the right subtree of root node contains node with key greater than the root nodes key.
Hence, the tree is a BST.
Example 2:
Input:
  2
   \
    7
     \
      6
       \
        5
         \
          9
           \
            2
             \
              6
Output: 0
Explanation:
Since the node with value 7 has right subtree nodes with keys less than 7, this is not a BST.
*/
public class ValidateBinarySearchTree {

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

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    //TC:O(N)
    //SC:O(1)
    private boolean isValidBST(TreeNode root, long minValue, long maxValue) {
        if (root == null) {
            return true;
        }
        if (root.data >= maxValue || root.data <= minValue) {
            return false;
        }
        return isValidBST(root.left, minValue, root.data) &&
                isValidBST(root.right, root.data, maxValue);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        ValidateBinarySearchTree vbst = new ValidateBinarySearchTree();
        System.out.println(vbst.isValidBST(root));
    }
}
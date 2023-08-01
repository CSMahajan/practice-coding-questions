package BinaryTree;

/*
Balanced Binary Tree

Given a binary tree, determine if it is height-balanced.
Given a binary tree, find if it is height balanced or not.
A tree is height balanced if difference between heights of left and right subtrees is not more than one for all nodes of tree.
A height balanced tree
        1
     /     \
   10      39
  /
5
An unbalanced tree
        1
     /
   10
  /
5
Example 1:
Input:
      1
    /
   2
    \
     3
Output: 0
Explanation: The max difference in height
of left subtree and right subtree is 2,
which is greater than 1. Hence unbalanced
Example 2:
Input:
       10
     /   \
    20   30
  /   \
 40   60
Output: 1
Explanation: The max difference in height
of left subtree and right subtree is 1.
Hence, balanced.
*/
public class BalancedBinaryTree {

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

    //Time Complexity: O(N)
    //Space Complexity: O(1) Extra Space + O(H) Recursion Stack space (Where 'H'  is the height of binary tree)
    public boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != -1;
    }

    public int dfsHeight(TreeNode root) {
        if(root==null){
            return 0;
        }
        int leftSubTreeHeight = dfsHeight(root.left);
        if(leftSubTreeHeight==-1){
            return -1;
        }
        int rightSubTreeHeight = dfsHeight(root.right);
        if(rightSubTreeHeight==-1){
            return -1;
        }
        if(Math.abs(leftSubTreeHeight-rightSubTreeHeight)>1){
            return -1;
        }
        return 1 + Math.max(leftSubTreeHeight,rightSubTreeHeight);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left= new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        BalancedBinaryTree bbt = new BalancedBinaryTree();
        System.out.println(bbt.isBalanced(root));
    }
}
package BinaryTree;

/*
Maximum Depth(Height) of Binary Tree

Given the root of a binary tree, return its maximum depth.
A binary tree's maximum depth is the number of nodes along the longest path
from the root node down to the farthest leaf node.
Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 3
Example 2:
Input: root = [1,null,2]
Output: 2
*/
public class HeightBinaryTree {

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
    //Space Complexity: O(1) Extra Space + O(H) Recursion Stack space, where “H”  is the height of the binary tree.
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int leftSubTreeHeight = maxDepth(root.left);
        int rightSubTreeHeight = maxDepth(root.right);
        return 1 + Math.max(leftSubTreeHeight,rightSubTreeHeight);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left= new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        HeightBinaryTree mdbt = new HeightBinaryTree();
        System.out.println(mdbt.maxDepth(root));
    }
}
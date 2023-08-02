package BinaryTree;

/*
Lowest Common Ancestor in a Binary Tree

Given a Binary Tree with all unique values and two nodes value, n1 and n2.
The task is to find the lowest common ancestor of the given two nodes.
We may assume that either both n1 and n2 are present in the tree or none of them are present.
LCA: It is the first common ancestor of both the nodes n1 and n2 from bottom of tree.
Example 1:
Input:
n1 = 2 , n2 = 3
       1
      / \
     2   3
Output: 1
Explanation:
LCA of 2 and 3 is 1.
Example 2:
Input:
n1 = 3 , n2 = 4
           5
          /
         2
        / \
       3   4
Output: 2
Explanation:
LCA of 3 and 4 is 2.
*/
public class LowestCommonAncestorBinaryTree {

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

    //Time complexity: O(N) where n is the number of nodes.
    //Space complexity: O(N), auxiliary space.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        TreeNode p = root.left.right;
        TreeNode q = root.right.left;
        LowestCommonAncestorBinaryTree lcabt = new LowestCommonAncestorBinaryTree();
        System.out.println(lcabt.lowestCommonAncestor(root, p, q).data);
    }
}
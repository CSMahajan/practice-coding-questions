package BinaryTree;

/*
Symmetric Tree

Given a Binary Tree.
Check whether it is Symmetric or not, i.e. whether the binary tree is a Mirror image of itself or not.
Example 1:
Input:
         5
       /   \
      1     1
     /       \
    2         2
Output: True
Explanation: Tree is mirror image of
itself i.e. tree is symmetric
Example 2:
Input:
         5
       /   \
      10     10
     /  \     \
    20  20     30
Output: False
*/
public class SymmetricBinaryTree {

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
    //Reason: We are doing simple tree traversal and changing both root1 and root2 simultaneously.
    //Space Complexity: O(N)
    //Reason: In the worst case (skewed tree), space complexity can be O(N).
    public boolean isSymmetric(TreeNode root) {
        return (root == null) ||
                isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return node1 == node2;
        }
        return node1.data == node2.data &&
                isSymmetric(node1.left, node2.right)&&
                isSymmetric(node1.right, node2.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(4);
        SymmetricBinaryTree sbt = new SymmetricBinaryTree();
        System.out.println(sbt.isSymmetric(root));
    }
}
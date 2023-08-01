package BinaryTree;

/*
Diameter of a Binary Tree

The diameter of a tree (sometimes called the width) is the number of nodes on the longest path between two end nodes.
The diagram below shows two trees each with diameter nine, the leaves that form the ends of the longest path are shaded
(note that there is more than one path in each tree of length nine, but no path longer than nine nodes).
Example 1:
Input:
       1
     /  \
    2    3
Output: 3
Example 2:
Input:
         10
        /   \
      20    30
    /   \
   40   60
Output: 4
*/
public class DiameterBinaryTree {

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
    public int diameterOfBinaryTree(TreeNode root) {
        int[] diameter = new int[1];
        maxHeight(root, diameter);
        return diameter[0];
    }

    private int maxHeight(TreeNode root, int[] diameter) {
        if (root == null) {
            return 0;
        }
        int leftHeight = maxHeight(root.left, diameter);
        int rightHeight = maxHeight(root.right, diameter);
        diameter[0] = Math.max(diameter[0], leftHeight + rightHeight);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left= new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        DiameterBinaryTree dbt = new DiameterBinaryTree();
        System.out.println(dbt.diameterOfBinaryTree(root));
    }
}
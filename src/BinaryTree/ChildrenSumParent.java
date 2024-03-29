package BinaryTree;

/*
Children Sum Parent

Given a Binary Tree. Check whether all of its nodes have the value equal to the sum of their child nodes.
Example 1:
Input:
     10
    /
  10
Output: 1
Explanation: Here, every node is sum of its left and right child.
Example 2:
Input:
       1
     /   \
    4     3
   /  \
  5    N
Output: 0
Explanation: Here, 1 is the root node
and 4, 3 are its child nodes. 4 + 3 = 7 which is not equal to the value of root node.
Hence, this tree does not satisfy the given conditions.
*/
public class ChildrenSumParent {

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


    public static int isSumProperty(TreeNode root) {
        // add your code here
        if (root == null)
            return 1;
        if (root.left == null && root.right == null)
            return 1;
        int sum = 0;
        if (root.left != null) {
            sum += root.left.data;
        }
        if (root.right != null) {
            sum += root.right.data;
        }

        if (root.data == sum) {
            int leftProperty = isSumProperty(root.left);
            int rightProperty = isSumProperty(root.right);

            if (leftProperty == 1 && rightProperty == 1)
                return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(22);
        root.left = new TreeNode(9);
        root.right = new TreeNode(13);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(isSumProperty(root) == 1);
    }
}
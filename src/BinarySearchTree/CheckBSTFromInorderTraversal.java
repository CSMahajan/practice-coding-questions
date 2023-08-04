package BinarySearchTree;

public class CheckBSTFromInorderTraversal {

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

    public static boolean isValidBST(int[] order) {
        // Write your code here.
        for (int i = 0; i < order.length - 1; i++) {
            if (order[i] > order[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        int[] inorder = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(isValidBST(inorder));
    }
}
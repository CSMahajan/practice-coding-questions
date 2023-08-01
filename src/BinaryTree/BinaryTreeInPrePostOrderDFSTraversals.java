package BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInPrePostOrderDFSTraversals {

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

    public static List<List<Integer>> getTreeTraversal(TreeNode root) {
        // Write your code here.
        List<Integer> inOrder = new ArrayList<>();
        List<Integer> preOrder = new ArrayList<>();
        List<Integer> postOrder = new ArrayList<>();
        List<List<Integer>> dfsOrder = new ArrayList<>();
        inOrderTraversal(root, inOrder);
        preOrderTraversal(root, preOrder);
        postOrderTraversal(root, postOrder);
        dfsOrder.add(inOrder);
        dfsOrder.add(preOrder);
        dfsOrder.add(postOrder);
        return dfsOrder;
    }

    private static void inOrderTraversal(TreeNode root, List<Integer> inOrder) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, inOrder);
        inOrder.add(root.data);
        inOrderTraversal(root.right, inOrder);
    }

    private static void preOrderTraversal(TreeNode root, List<Integer> preOrder) {
        if (root == null) {
            return;
        }
        preOrder.add(root.data);
        preOrderTraversal(root.left, preOrder);
        preOrderTraversal(root.right, preOrder);
    }

    private static void postOrderTraversal(TreeNode root, List<Integer> postOrder) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left, postOrder);
        postOrderTraversal(root.right, postOrder);
        postOrder.add(root.data);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left= new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(getTreeTraversal(root));
    }
}
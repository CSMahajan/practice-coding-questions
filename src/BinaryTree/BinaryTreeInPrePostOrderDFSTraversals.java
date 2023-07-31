package BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInPrePostOrderDFSTraversals {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static List<List<Integer>> getTreeTraversal(Node root) {
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

    private static void inOrderTraversal(Node root, List<Integer> inOrder) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, inOrder);
        inOrder.add(root.data);
        inOrderTraversal(root.right, inOrder);
    }

    private static void preOrderTraversal(Node root, List<Integer> preOrder) {
        if (root == null) {
            return;
        }
        preOrder.add(root.data);
        preOrderTraversal(root.left, preOrder);
        preOrderTraversal(root.right, preOrder);
    }

    private static void postOrderTraversal(Node root, List<Integer> postOrder) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left, postOrder);
        postOrderTraversal(root.right, postOrder);
        postOrder.add(root.data);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left= new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        System.out.println(getTreeTraversal(root));
    }
}
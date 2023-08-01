package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativeRecursivePreOrderTraversal {

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

    //TC:O(N)
    //SC:O(N)
    public static List<Integer> getPreOrderTraversalIterative(TreeNode root) {
        // Write your code here.
        List<Integer> preOrder = new ArrayList<>();
        if(root==null){
            return preOrder;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            preOrder.add(node.data);
            //first we need to add right node because it is stack(LIFO) and
            //our preorder execution requires us to explore left subtree first
            if(node.right!=null){
                stack.add(node.right);
            }
            if(node.left!=null){
                stack.add(node.left);
            }
        }
        return preOrder;
    }

    public static List<Integer> getPreOrderTraversalRecursive(TreeNode root) {
        // Write your code here.
        List<Integer> preOrder = new ArrayList<>();
        preOrderTraversalRecursive(root, preOrder);
        return preOrder;
    }

    //TC:O(N)
    //SC:O(N)
    private static void preOrderTraversalRecursive(TreeNode root, List<Integer> preOrder) {
        if (root == null) {
            return;
        }
        preOrder.add(root.data);
        preOrderTraversalRecursive(root.left, preOrder);
        preOrderTraversalRecursive(root.right, preOrder);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left= new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println("Recursive Pre Order Traversal----->");
        System.out.println(getPreOrderTraversalRecursive(root));
        System.out.println("Iterative Pre Order Traversal----->");
        System.out.println(getPreOrderTraversalIterative(root));
    }
}
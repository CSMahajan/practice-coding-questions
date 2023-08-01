package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativeRecursiveInOrderTraversal {

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
    public static List<Integer> getInOrderTraversalIterative(TreeNode root) {
        // Write your code here.
        List<Integer> inOrder = new ArrayList<>();
        if(root==null){
            return inOrder;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(true){
            //logic is if node is not null, add it to stack and go to left
            //if node is null, check if stack is empty that means traversal is completed,
            //if stack still has some elements, then get the topmost element in stack and add it to inorder list and go to right
            if(node!=null) {
                stack.add(node);
                node = node.left;
            } else {
                if(stack.isEmpty()){
                    break;
                }
                node = stack.pop();
                inOrder.add(node.data);
                node = node.right;
            }
        }
        return inOrder;
    }

    public static List<Integer> getInOrderTraversalRecursive(TreeNode root) {
        // Write your code here.
        List<Integer> inOrder = new ArrayList<>();
        inOrderTraversalRecursive(root, inOrder);
        return inOrder;
    }

    //TC:O(N)
    //SC:O(N)
    private static void inOrderTraversalRecursive(TreeNode root, List<Integer> inOrder) {
        if (root == null) {
            return;
        }
        inOrderTraversalRecursive(root.left, inOrder);
        inOrder.add(root.data);
        inOrderTraversalRecursive(root.right, inOrder);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left= new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println("Recursive In Order Traversal----->");
        System.out.println(getInOrderTraversalRecursive(root));
        System.out.println("Iterative In Order Traversal----->");
        System.out.println(getInOrderTraversalIterative(root));
    }
}
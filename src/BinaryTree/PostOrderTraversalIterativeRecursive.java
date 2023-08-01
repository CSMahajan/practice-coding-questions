package BinaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class PostOrderTraversalIterativeRecursive {

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
    //SC:O(2N) {for 2 stacks}
    public static List<Integer> getPostOrderTraversalIterativeUsing2Stacks(TreeNode root) {
        // Write your code here.
        List<Integer> postOrder = new ArrayList<>();
        if (root == null) {
            return postOrder;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(root);
        //logic is get the top most element from stack1 as the node, and add that node in stack 2
        //if the node has left,add node's left to stack1
        //if the node has right,add node's right to stack1
        //stack2 has the elements in post order traversal which can be retrieved by popping elements from it
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.add(node);
            if (node.left != null) {
                stack1.add(node.left);
            }
            if (node.right != null) {
                stack1.add(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            TreeNode node = stack2.pop();
            postOrder.add(node.data);
        }
        return postOrder;
    }

    //TC:O(N)+O(N) {First O(N) for while loop and Second O(N) for arraylist reversal}
    //SC:O(N)
    public static List<Integer> getPostOrderTraversalIterativeUsing1StackListReversal(TreeNode root) {
        // Write your code here.
        List<Integer> postOrder = new ArrayList<>();
        if (root == null) {
            return postOrder;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        //get the top most element from stack into list
        //check for left, if exists, add it to stack
        //check for right, if exists, add it to stack
        //reverse the list
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            postOrder.add(node.data);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        Collections.reverse(postOrder);
        return postOrder;
    }

    //TC:O(2N) {first O(N) for outer while loop and second O(N) for inner while loop}
    //SC:O(N)
    public static List<Integer> getPostOrderTraversalIterativeUsing1Stack(TreeNode root) {
        // Write your code here.
        List<Integer> postOrder = new ArrayList<>();
        if (root == null) {
            return postOrder;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.add(current);
                current = current.left;
            } else {
                TreeNode temp = stack.peek().right;
                if (temp == null) {
                    temp = stack.pop();
                    postOrder.add(temp.data);
                    while (!stack.isEmpty() && temp == stack.peek().right) {
                        temp = stack.pop();
                        postOrder.add(temp.data);
                    }
                } else {
                    current = temp;
                }
            }
        }
        return postOrder;
    }

    public static List<Integer> getPostOrderTraversalRecursive(TreeNode root) {
        // Write your code here.
        List<Integer> postOrder = new ArrayList<>();
        postOrderTraversalRecursive(root, postOrder);
        return postOrder;
    }

    //TC:O(N)
    //SC:O(N)
    private static void postOrderTraversalRecursive(TreeNode root, List<Integer> postOrder) {
        if (root == null) {
            return;
        }
        postOrderTraversalRecursive(root.left, postOrder);
        postOrderTraversalRecursive(root.right, postOrder);
        postOrder.add(root.data);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println("Recursive Post Order Traversal----->");
        System.out.println(getPostOrderTraversalRecursive(root));
        System.out.println("Iterative Post Order Traversal using 2 Stacks----->");
        System.out.println(getPostOrderTraversalIterativeUsing2Stacks(root));
        System.out.println("Iterative Post Order Traversal using 1 Stack----->");
        System.out.println(getPostOrderTraversalIterativeUsing1Stack(root));
        System.out.println("Iterative Post Order Traversal using 1 Stack list reversal----->");
        System.out.println(getPostOrderTraversalIterativeUsing1StackListReversal(root));
    }
}
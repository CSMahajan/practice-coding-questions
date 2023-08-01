package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InPrePostOrderDFSTraversalsIterative {

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

    static class Pair {
        TreeNode node;
        int operationNumber;

        public Pair(TreeNode node, int operationNumber) {
            this.node = node;
            this.operationNumber = operationNumber;
        }
    }

    //Time Complexity: O(N)
    //Reason: We are visiting every node thrice therefore time complexity will be O(3*N),
    //which can be assumed as linear time complexity.
    //Space Complexity: O(N)
    //Reason: We are using three lists and a stack to store the nodes.
    //The time complexity will be about O(4*N), which can be assumed as linear time complexity.
    public static List<List<Integer>> getTreeTraversal(TreeNode root) {
        // Write your code here.
        List<Integer> inOrder = new ArrayList<>();
        List<Integer> preOrder = new ArrayList<>();
        List<Integer> postOrder = new ArrayList<>();
        List<List<Integer>> dfsOrder = new ArrayList<>();
        if (root == null) {
            return dfsOrder;
        }
        Stack<Pair> stack = new Stack<>();
        //operationNumber = 1 denotes preOrder
        //operationNumber = 2 denotes inOrder
        //operationNumber = 3 denotes postOrder
        stack.add(new Pair(root, 1));
        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            if (pair.operationNumber == 1) {
                //1.increment 1 to 2
                //2.add to preOrder
                //3.stack push current
                //4.stack push left node if present with operationNumber as 1
                preOrder.add(pair.node.data);
                pair.operationNumber++;
                stack.add(pair);
                if (pair.node.left != null) {
                    stack.add(new Pair(pair.node.left, 1));
                }
            } else if(pair.operationNumber==2){
                //1.increment 2 to 3
                //2.add to inOrder
                //3.stack push current
                //4.stack push right node if present with operationNumber as 1
                inOrder.add(pair.node.data);
                pair.operationNumber++;
                stack.add(pair);
                if (pair.node.right != null) {
                    stack.add(new Pair(pair.node.right, 1));
                }
            } else {
                postOrder.add(pair.node.data);
            }
        }
        dfsOrder.add(inOrder);
        dfsOrder.add(preOrder);
        dfsOrder.add(postOrder);
        return dfsOrder;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println("InOrder Traversal Iterative----->");
        System.out.println(getTreeTraversal(root).get(0));
        System.out.println("PreOrder Traversal Iterative----->");
        System.out.println(getTreeTraversal(root).get(1));
        System.out.println("PostOrder Traversal Iterative----->");
        System.out.println(getTreeTraversal(root).get(2));
    }
}
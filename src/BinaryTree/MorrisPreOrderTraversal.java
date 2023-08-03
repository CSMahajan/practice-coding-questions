package BinaryTree;

import java.util.ArrayList;
import java.util.List;

/*
Perform Morris preorder traversal on given binary tree
*/
public class MorrisPreOrderTraversal {

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
    //Space Complexity: O(1)
    //Reason: We are not using recursion.
    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> preOrder = new ArrayList<>();
        if (root == null) {
            return preOrder;
        }
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                preOrder.add(current.data);
                current = current.right;
            } else {
                TreeNode previous = current.left;
                while (previous.right != null && previous.right != current) {
                    //go to rightmost node of left subtree
                    previous = previous.right;
                }
                if (previous.right == null) {
                    //create the thread link
                    previous.right = current;
                    preOrder.add(current.data);
                    //current goes to left as predecessor is done for pointing and adding to list
                    current = current.left;
                } else {
                    //remove the thread link and current can now go to the left node
                    //i.e.we had already made the link
                    previous.right = null;
                    current = current.right;
                }
            }
        }
        return preOrder;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        MorrisPreOrderTraversal mpot = new MorrisPreOrderTraversal();
        System.out.println(mpot.preOrderTraversal(root));
    }
}
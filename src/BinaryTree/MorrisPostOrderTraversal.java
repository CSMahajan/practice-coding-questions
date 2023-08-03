package BinaryTree;

import java.util.ArrayList;
import java.util.List;

/*
Perform Morris postorder traversal on given binary tree
*/
public class MorrisPostOrderTraversal {

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
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> postOrder = new ArrayList<>();
        if (root == null) {
            return postOrder;
        }
        TreeNode current = root;
        while (current != null) {
            if (current.right == null) {
                //add at the start of list
                postOrder.add(0, current.data);
                current = current.left;
            } else {
                TreeNode previous = current.right;
                while (previous.left != null && previous.left != current) {
                    //go to leftmost node of right subtree
                    previous = previous.left;
                }
                if (previous.left == null) {
                    //create the thread link
                    previous.left = current;
                    //add at the start of list
                    postOrder.add(0, current.data);
                    //current goes to right as predecessor is done for pointing and adding to list
                    current = current.right;
                } else {
                    //remove the thread link and current can now go to the left node
                    //i.e.we had already made the link
                    previous.left = null;
                    current = current.left;
                }
            }
        }
        return postOrder;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        MorrisPostOrderTraversal mpot = new MorrisPostOrderTraversal();
        System.out.println(mpot.postOrderTraversal(root));
    }
}
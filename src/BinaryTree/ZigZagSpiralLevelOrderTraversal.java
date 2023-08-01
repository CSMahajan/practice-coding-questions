package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Zigzag(Spiral) Level Order Traversal

Given a Binary Tree. Find the Zig-Zag Level Order Traversal of the Binary Tree.
Example 1:
Input:
        1
      /   \
     2     3
    / \   /  \
   4   5 6    7
Output:
1 3 2 4 5 6 7
Example 2:
Input:
           7
        /     \
       9       7
     /  \     /
    8    8   6
   /  \
  10   9
Output:
7 7 9 8 8 6 9 10
*/
public class ZigZagSpiralLevelOrderTraversal {

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
    //Space Complexity: O(N)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> zigzagOrder = new ArrayList<>();
        if (root == null) {
            return zigzagOrder;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;
        //flag will decide the order in the current level to add left to right or right to left
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    if (flag) {
                        currentLevel.add(node.data);
                    } else {
                        currentLevel.add(0, node.data);
                    }
                }
            }
            flag = !flag;
            zigzagOrder.add(currentLevel);
        }
        return zigzagOrder;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        ZigZagSpiralLevelOrderTraversal lot = new ZigZagSpiralLevelOrderTraversal();
        System.out.println(lot.zigzagLevelOrder(root));
    }
}
package BinaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Transform to Sum Tree

Convert the given binary tree to children sum tree.
The value of any node equals sum of its left and right child in the binary tree.
*/
public class TransformParentToChildrenSumBinaryTree {

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

    public static void transformBinaryTree(TreeNode root) {
        changeBinaryTree(root);
    }

    private static void changeBinaryTree(TreeNode node) {
        if (node == null) {
            return;
        }
        int childSum = 0;
        if (node.left != null) {
            childSum += node.left.data;
        }
        if (node.right != null) {
            childSum += node.right.data;
        }
        if (childSum < node.data) {
            if (node.left != null) {
                node.left.data = node.data;
            }
            if (node.right != null) {
                node.right.data = node.data;
            }
        }
        changeBinaryTree(node.left);
        changeBinaryTree(node.right);
        int totalSum = 0;
        if (node.left != null) {
            totalSum += node.left.data;
        }
        if (node.right != null) {
            totalSum += node.right.data;
        }
        if (node.left != null || node.right != null) {
            node.data = totalSum;
        }
    }

    //Level order Method taken just for traversal of binary tree printing purpose
    public static List<List<Integer>> getLevelOrderTraversal(TreeNode root) {
        List<List<Integer>> levelOrder = new LinkedList<>();
        if (root == null) {
            return levelOrder;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode != null) {
                    if (treeNode.left != null) {
                        queue.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        queue.add(treeNode.right);
                    }
                    currentLevel.add(treeNode.data);
                }
            }
            levelOrder.add(currentLevel);
        }
        return levelOrder;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        transformBinaryTree(root);
        System.out.println(getLevelOrderTraversal(root));
    }
}
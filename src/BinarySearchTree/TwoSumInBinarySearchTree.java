package BinarySearchTree;

import java.util.Stack;

/*
Find a pair with given target in BST

Given a Binary Search Tree and a target sum.
Check whether there's a pair of Nodes in the BST with value summing up to the target sum.
Example 1:
Input:
        2
      /   \
     1     3
sum = 5
Output: 1
Explanation:
Nodes with value 2 and 3 sum up to 5.
Example 2:
Input:
           6
          /
         5
        /
       3
     /  \
    1    4
sum = 2
Output: 0
Explanation:
There's no pair that sums up to 2.
*/
public class TwoSumInBinarySearchTree {

    static class BSTIterator {
        private final Stack<TreeNode> stack = new Stack<>();

        private boolean reverse = true;

        public BSTIterator(TreeNode root, boolean isReverse) {
            reverse = isReverse;
            pushAll(root);
        }

        public int next() {
            TreeNode tmpNode = stack.pop();
            if (reverse) {
                pushAll(tmpNode.left);
            } else {
                pushAll(tmpNode.right);
            }
            return tmpNode.data;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        private void pushAll(TreeNode node) {
            while (node != null) {
                stack.push(node);
                if (reverse) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
        }
    }

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
    //SC:O(Height of BST)
    public boolean findTarget(TreeNode root, int k) {
        BSTIterator l = new BSTIterator(root, false);
        BSTIterator r = new BSTIterator(root, true);
        int i = l.next();
        int j = r.next();
        while (i < j) {
            if (i + j == k) {
                return true;
            } else if (i + j < k) {
                i = l.next();
            } else {
                j = r.next();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        TwoSumInBinarySearchTree tsbst = new TwoSumInBinarySearchTree();
        int target = 10;
        System.out.println(tsbst.findTarget(root, target));
    }
}
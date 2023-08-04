package BinarySearchTree;

/*
Search in a Binary Search Tree

LeetCode
You are given the root of a binary search tree (BST) and an integer val.
Find the node in the BST that the node's value equals val and
return the subtree rooted with that node. If such a node does not exist, return null.

GeeksForGeeks
Given a Binary Search Tree and a node value X, find if the node with value X is present in the BST or not.
Example 1:
Input:         2
                \
                 81
               /    \
             42      87
              \       \
               66      90
              /
            45
X = 87
Output: 1
Explanation: As 87 is present in the given nodes , so the output will be 1.
Example 2:
Input:      6
             \
              8
             / \
            7   9
X = 11
Output: 0
Explanation: As 11 is not present in the given nodes , so the output will be 0.
*/
public class SearchInBinarySearchTree {

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

    //TC:O(log2(N))
    //SC:O(1)
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null && root.data != val) {
            root = val < root.data ? root.left : root.right;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        SearchInBinarySearchTree sibst = new SearchInBinarySearchTree();
        int val = 7;
        TreeNode targetNode = sibst.searchBST(root,val);
        System.out.println(targetNode.data);
    }
}
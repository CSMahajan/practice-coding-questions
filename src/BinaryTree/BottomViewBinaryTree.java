package BinaryTree;

import java.util.*;

/*
Bottom View of Binary Tree

Given a binary tree, print the bottom view from left to right.
A node is included in bottom view if it can be seen when we look at the tree from bottom.

                      20
                    /    \
                  8       22
                /   \        \
              5      3       25
                    /   \
                  10    14

For the above tree, the bottom view is 5 10 3 14 25.
If there are multiple bottom-most nodes for a horizontal distance from root,
then print the later one in level traversal.
For example,
in the below diagram, 3 and 4 are both the bottommost nodes at horizontal distance 0, we need to print 4.

                      20
                    /    \
                  8       22
                /   \     /   \
              5      3 4     25
                     /    \
                 10       14

For the above tree the output should be 5 10 4 14 25.
Example 1:
Input:
       1
     /   \
    3     2
Output: 3 1 2
Explanation:
First case represents a tree with 3 nodes and 2 edges where root is 1,
left child of 1 is 3 and right child of 1 is 2.
Thus, nodes of the binary tree will be printed as such 3 1 2.
Example 2:
Input:
         10
       /    \
      20    30
     /  \
    40   60
Output: 40 20 60 30
*/
public class BottomViewBinaryTree {

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
        int verticalLineAsRow;

        public Pair(TreeNode node, int verticalLineAsRow) {
            this.node = node;
            this.verticalLineAsRow = verticalLineAsRow;
        }
    }

    //Time Complexity: O(N)
    //Space Complexity: O(N)
    public ArrayList<Integer> bottomView(TreeNode root) {
        // Code here
        ArrayList<Integer> bottomView = new ArrayList<>();
        if (root == null) {
            return bottomView;
        }
        //Queue is storing pair consisting of node and vertical line as row number
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        //TreeMap stores the vertical line number as the key and node's data(value) as the value
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        while (!queue.isEmpty()) {
            Pair pair = queue.remove();
            TreeNode node = pair.node;
            int verticalLineAsRow = pair.verticalLineAsRow;
            //we will put or replace values corresponding to vertical line as we traverse towards leaf nodes
            //this will ensure we always have latest(new) nodes which can be viewed from bottom
            treeMap.put(verticalLineAsRow, node.data);
            if (node.left != null) {
                queue.add(new Pair(node.left, verticalLineAsRow - 1));
            }
            if (node.right != null) {
                queue.add(new Pair(node.right, verticalLineAsRow + 1));
            }
        }
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            bottomView.add(entry.getValue());
        }
        return bottomView;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        BottomViewBinaryTree bvbt = new BottomViewBinaryTree();
        System.out.println(bvbt.bottomView(root));
    }
}
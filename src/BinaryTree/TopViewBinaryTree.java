package BinaryTree;

import java.util.*;

/*
Top View of Binary Tree

Given below is a binary tree. The task is to print the top view of binary tree.
Top view of a binary tree is the set of nodes visible when the tree is viewed from the top.
For the given below tree
       1
     /   \
   2       3
 /  \    /   \
4    5  6     7
Top view will be: 4 2 1 3 7
Note: Return nodes from leftmost node to rightmost node.
Also if 2 nodes are outside the shadow of the tree and
are at same position then consider the extreme ones only(i.e. leftmost and rightmost).
For ex - 1 2 3 N 4 5 N 6 N 7 N 8 N 9 N N N N N will give 8 2 1 3 as answer.
Here 8 and 9 are on the same position but 9 will get shadowed.
Example 1:
Input:
      1
   /    \
  2      3
Output: 2 1 3
Example 2:
Input:
       10
    /      \
  20        30
 /   \    /    \
40   60  90    100
Output: 40 20 10 30 100
*/
public class TopViewBinaryTree {

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
    public static ArrayList<Integer> topView(TreeNode root) {
        // add your code
        ArrayList<Integer> topViewOrder = new ArrayList<>();
        if (root == null) {
            return topViewOrder;
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
            if (!treeMap.containsKey(verticalLineAsRow)) {
                //if current vertical is occurring for first time then only add it to treeMap
                //this will ensure that we will have only topmost elements of binary tree into our map
                treeMap.put(verticalLineAsRow, node.data);
            }
            if (node.left != null) {
                queue.add(new Pair(node.left, verticalLineAsRow - 1));
            }
            if (node.right != null) {
                queue.add(new Pair(node.right, verticalLineAsRow + 1));
            }
        }
        //as entries in treeMap are in ascending order i.e.left to right vertical line number,
        //their corresponding values depict the top view of the binary tree
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            topViewOrder.add(entry.getValue());
        }
        return topViewOrder;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(topView(root));
    }
}
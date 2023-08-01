package BinaryTree;

import java.util.ArrayList;

/*
Boundary Traversal of binary tree

Given a Binary Tree, find its Boundary Traversal. The traversal should be in the following order:
Left boundary nodes: defined as the path from the root to the left-most node ie-
the leaf node you could reach when you always travel preferring the left subtree over the right subtree.
Leaf nodes: All the leaf nodes except for the ones that are part of left or right boundary.
Reverse right boundary nodes: defined as the path from the right-most node to the root.
The right-most node is the leaf node you could reach when you always travel preferring the right subtree over the left subtree.
Exclude the root from this as it was already included in the traversal of left boundary nodes.
Note: If the root doesn't have a left subtree or right subtree, then the root itself is the left or right boundary.
Example 1:
Input:
        1
      /   \
     2     3
    / \   / \
   4   5 6   7
      / \
     8   9

Output: 1 2 4 8 9 6 7 3
Explanation:
Example 2:
Input:
            1
           /
          2
        /  \
       4    9
     /  \    \
    6    5    3
             /  \
            7     8

Output: 1 2 4 6 5 7 8
Explanation:
As you can see we have not taken the right subtree.
*/
public class BoundaryOrderTraversal {

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

    //Time Complexity: O(N).
    //Reason: The time complexity will be O(H) + O(H) + O(N) which is ≈ O(N)
    //Space Complexity: O(N)
    //Reason: Space is needed for the recursion stack while adding leaves.
    //In the worst case (skewed tree), space complexity can be O(N).
    public ArrayList<Integer> boundary(TreeNode node) {
        ArrayList<Integer> boundaryOrder = new ArrayList<>();
        if (!isLeafNode(node)) {
            boundaryOrder.add(node.data);
        }
        //Step 1: add left boundary nodes without leaf nodes
        addLeftBoundaryNodes(node, boundaryOrder);
        //Step 2: add all the leaf nodes from left to right
        addLeafNodes(node, boundaryOrder);
        //Step 3: add right boundary nodes without leaf node in reverse order
        addRightBoundaryNodesInReverse(node, boundaryOrder);
        return boundaryOrder;
    }

    //Step 1
    private void addLeftBoundaryNodes(TreeNode root, ArrayList<Integer> boundaryOrder) {
        TreeNode current = root.left;
        while (current != null) {
            if (!isLeafNode(current)) {
                boundaryOrder.add(current.data);
            }
            if (current.left != null) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
    }

    //Step 2
    private void addLeafNodes(TreeNode node, ArrayList<Integer> boundaryOrder) {
        if (isLeafNode(node)) {
            boundaryOrder.add(node.data);
            return;
        }
        if (node.left != null) {
            addLeafNodes(node.left, boundaryOrder);
        }
        if (node.right != null) {
            addLeafNodes(node.right, boundaryOrder);
        }
    }

    //Step 3
    private void addRightBoundaryNodesInReverse(TreeNode root, ArrayList<Integer> boundaryOrder) {
        TreeNode current = root.right;
        ArrayList<Integer> rightBoundaryNodes = new ArrayList<>();
        while (current != null) {
            if (!isLeafNode(current)) {
                rightBoundaryNodes.add(current.data);
            }
            if (current.right != null) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        //adding the nodes in the right boundary nodes in reverse order
        for (int i = rightBoundaryNodes.size() - 1; i >= 0; i--) {
            boundaryOrder.add(rightBoundaryNodes.get(i));
        }
    }

    private boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        BoundaryOrderTraversal bot = new BoundaryOrderTraversal();
        System.out.println(bot.boundary(root));
    }
}
package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/*
Merge two BST 's

Given two BSTs, return elements of both BSTs in sorted form.
Example 1:
Input:
BST1:
       5
     /   \
    3     6
   / \
  2   4
BST2:
        2
      /   \
     1     3
            \
             7
            /
           6
Output: 1 2 2 3 3 4 5 6 6 7
Explanation:
After merging and sorting the two BST we get 1 2 2 3 3 4 5 6 6 7.
Example 2:
Input:
BST1:
       12
     /
    9
   / \
  6   11
BST2:
      8
    /  \
   5    10
  /
 2
Output: 2 5 6 8 9 10 11 12
Explanation:
After merging and sorting the two BST we get 2 5 6 8 9 10 11 12.
*/
public class MergeTwoBinarySearchTrees {

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

    //Time Complexity: O(m + n)
    //Auxiliary Space Complexity: O(1)
    public List<Integer> merge(TreeNode root1, TreeNode root2) {
        // Write your code here
        // We run this loop until both trees are completely
        // exhausted Even if one of the trees is still left, we
        // run this loop
        List<Integer> inorder = new ArrayList<>();
        while (root1 != null || root2 != null) {

            // Morris traversal of the first tree
            while (root1 != null) {

                // This check is to ensure that if
                // root1 is already exhausted we skip root1

                // If root has a left node, we go to the
                // rightmost child of the left node and assign
                // root to the right of the rightmost node
                if (root1.left != null) {
                    TreeNode left = root1.left;

                    // Moving to the rightmost node of left
                    while (left.right != null)
                        left = left.right;

                    // Assign root to right of rightmost node
                    left.right = root1;

                    // Make root's left to null and move root to left
                    left = root1.left;
                    root1.left = null;
                    root1 = left;
                } else break; // If root doesn't have a left node, that means
                // we're already on the left most (smallest) node
            }

            // Morris traversal of the second tree
            while (root2 != null) { // This check is to ensure that if
                // root2 is already exhausted we skip root2

                // If root has a left node, we go to the
                // rightmost child of the left node and assign
                // root to the right of the rightmost node
                if (root2.left != null) {
                    TreeNode left = root2.left;

                    // Moving to the rightmost node of left
                    while (left.right != null)
                        left = left.right;

                    // Assign root to right of rightmost node
                    left.right = root2;

                    // Make root's left to null and move root to left
                    left = root2.left;
                    root2.left = null;
                    root2 = left;
                } else break; // If root doesn't have a left node, that means
                // we're already on the left most (smallest) node
            }

            // Here root1 and root2 are smallest nodes in both trees
            if (root1 != null && root2 != null) {
                // Compare both nodes' data
                if (root1.data <= root2.data) {
                    inorder.add(root1.data);

                    root1 = root1.right; // Move smaller one to right
                } else {
                    inorder.add(root2.data);

                    root2 = root2.right; // Move smaller one to right
                }
            } else if (root1 != null) { // If root2 has exhausted and only root1 remains
                inorder.add(root1.data);

                root1 = root1.right; // Move it to right
            } else { // If root2 has exhausted and only root2 remains
                inorder.add(root2.data);

                root2 = root2.right; // Move it to right
            }
        }
        return inorder;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        root1.right.left = new TreeNode(5);
        root1.right.right = new TreeNode(7);
        TreeNode root2 = new TreeNode(14);
        root2.left = new TreeNode(10);
        root2.right = new TreeNode(17);
        root2.left.left = new TreeNode(9);
        root2.left.right = new TreeNode(12);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(23);
        MergeTwoBinarySearchTrees mtbst = new MergeTwoBinarySearchTrees();
        System.out.println(mtbst.merge(root1, root2));
    }
}
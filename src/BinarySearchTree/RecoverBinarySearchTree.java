package BinarySearchTree;

/*
Fixing Two nodes of a BST

You are given the root of a binary search tree(BST), where exactly two nodes were swapped by mistake.
Fix (or correct) the BST by swapping them back. Do not change the structure of the tree.
Note: It is guaranteed that the given input will form BST, except for 2 nodes that will be wrong.
All changes must be reflected in the original linked list.
Example 1:
Input:
       10
     /    \
    5      8
   / \
  2   20
Output: 1
Explanation:
Example 2:
Input:
         11
       /    \
      3      17
       \    /
        4  10
Output: 1
Explanation:
By swapping nodes 11 and 10, the BST can be fixed.
*/
public class RecoverBinarySearchTree {

    private TreeNode previous;
    private TreeNode first;
    private TreeNode middle;
    private TreeNode last;

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
    //SC:O(1)(if used recursive stack space: O(N), if used morris traversal: O(1))
    public void recoverTree(TreeNode root) {
        first = null;
        middle = null;
        last = null;
        previous = new TreeNode(Integer.MIN_VALUE);
        inorder(root);
        if (first != null && last != null) {
            //Case for non-adjacent nodes violating BST
            //found 2 violations, where pointers are at first and last
            int temp = first.data;
            first.data = last.data;
            last.data = temp;
        } else if (first != null && middle != null) {
            //Case for adjacent nodes violating BST
            //found only 1 violation, where pointers are at first and middle
            int temp = first.data;
            first.data = middle.data;
            middle.data = temp;
        }
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        //Perform inorder traversal recursively with slight modification that instead of storing the node values,
        //we will store only the nodes who do not follow inorder property in BST(i.e.inorder of BST is always ascending order)
        if (previous != null && root.data < previous.data) {
            if (first == null) {
                // If this is first violation, mark these two nodes as
                // 'first' and 'middle'
                first = previous;
                middle = root;
            } else {
                // If this is second violation, mark this node as last
                last = root;
            }
        }
        previous = root;
        inorder(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        RecoverBinarySearchTree rbst = new RecoverBinarySearchTree();
        rbst.recoverTree(root);
    }
}
package BinarySearchTree;

/*
Lowest Common Ancestor in a BST

Given a Binary Search Tree (with all values unique) and two node values n1 and n2 (n1!=n2).
Find the Lowest Common Ancestors of the two nodes in the BST.
Example 1:
Input:
              5
            /   \
          4      6
         /        \
        3          7
                    \
                     8
n1 = 7, n2 = 8
Output: 7
Example 2:
Input:
     2
   /   \
  1     3
n1 = 1, n2 = 3
Output: 2
*/
public class LowestCommonAncestorInBinarySearchTree {

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

    //TC:O(H)
    //SC:O(1)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        int currentNodeValue = root.data;
        //if both nodes lie on the left subtree from current root node, then go to left
        if (p.data < currentNodeValue && q.data < currentNodeValue) {
            return lowestCommonAncestor(root.left, p, q);
        }
        //if both nodes lie on the right subtree from current root node, then go to right
        if (p.data > currentNodeValue && q.data > currentNodeValue) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        TreeNode p = root.left;
        TreeNode q = root.right.left;
        LowestCommonAncestorInBinarySearchTree lcabst = new LowestCommonAncestorInBinarySearchTree();
        System.out.println(lcabst.lowestCommonAncestor(root, p, q).data);
    }
}

package BinarySearchTree;

/*
Inorder Successor in BST

Given a BST, and a reference to a Node x in the BST. Find the Inorder Successor of the given node in the BST.
Example 1:
Input:
      2
    /   \
   1     3
K(data of x) = 2
Output: 3
Explanation:
Inorder traversal : 1 2 3
Hence, inorder successor of 2 is 3.
Example 2:
Input:
             20
            /   \
           8     22
          / \
         4   12
            /  \
           10   14
K(data of x) = 8
Output: 10
Explanation:
Inorder traversal: 4 8 10 12 14 20 22
Hence, successor of 8 is 10.
*/
public class InorderSuccessorInBinarySearchTree {

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

    //TC:O(Height of BST)
    //SC:O(1)
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //add code here.
        TreeNode successor = null;
        while (root != null) {
            if (p.data >= root.data) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        InorderSuccessorInBinarySearchTree iosbst = new InorderSuccessorInBinarySearchTree();
        TreeNode node = root.left.right;
        System.out.println(iosbst.inorderSuccessor(root, node).data);
    }
}
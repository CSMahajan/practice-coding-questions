package BinarySearchTree;

/*
Insert a node in a BST

Given a BST and a key K. If K is not present in the BST, Insert a new Node with a value equal to K into the BST.
Note: If K is already present in the BST, don't modify the BST.
Example 1:
Input:
     2
   /   \
  1     3
K = 4
Output: 1 2 3 4
Explanation: After inserting the node 4, inorder traversal will be 1 2 3 4.
Example 2:
Input:
        2
      /   \
     1     3
             \
              6
K = 4
Output: 1 2 3 4 6
Explanation: After inserting the node 4, inorder traversal of the above tree will be 1 2 3 4 6.
*/
public class InsertNodeInBinarySearchTree {

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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode current = root;
        while (true) {
            if (val >= current.data) {
                if (current.right != null) {
                    current = current.right;
                } else {
                    current.right = new TreeNode(val);
                    break;
                }
            } else {
                if (current.left != null) {
                    current = current.left;
                } else {
                    current.left = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        InsertNodeInBinarySearchTree inbst = new InsertNodeInBinarySearchTree();
        int key = 5;
        System.out.println(inbst.insertIntoBST(root, key).data);
    }
}
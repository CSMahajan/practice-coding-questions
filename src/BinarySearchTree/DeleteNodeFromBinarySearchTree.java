package BinarySearchTree;

/*
Delete a node from BST

Given a Binary Search Tree and a node value X. Delete the node with the given value X from the BST.
If no node with value x exists, then do not make any change.
Example 1:
Input:
      2
    /   \
   1     3
X = 12
Output: 1 2 3
Explanation: In the given input there is no node with value 12 , so the tree will remain same.
Example 2:
Input:
            1
             \
              2
                \
                 8
               /    \
              5      11
            /  \    /  \
           4    7  9   12
X = 9
Output: 1 2 4 5 7 8 11 12
Explanation: In the given input tree after deleting 9 will be
             1
              \
               2
                 \
                  8
                 /   \
                5     11
               /  \     \
              4    7     12
*/
public class DeleteNodeFromBinarySearchTree {

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
    public TreeNode deleteFromBST(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.data == key) {
            //root node is to be deleted
            return deleteNode(root);
        }
        TreeNode dummyNode = root;
        while (root != null) {
            if (root.data > key) {
                //as our target(key) is smaller, the target key should be on the left subtree
                if (root.left != null && root.left.data == key) {
                    //if the left node of current root is to be deleted
                    root.left = deleteNode(root.left);
                } else {
                    root = root.left;
                }
            } else {
                //as our target(key) is greater, the target key should be on the right subtree
                if (root.right != null && root.right.data == key) {
                    //if the right node of current root is to be deleted
                    root.right = deleteNode(root.right);
                    break;
                } else {
                    root = root.right;
                }
            }
        }
        //as the root node is traversed and became the node to be deleted,
        //giving the original node which was saved in dummyNode before searching the node to be deleted
        return dummyNode;
    }

    private TreeNode deleteNode(TreeNode root) {
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        } else {
            TreeNode rightChild = root.right;
            //find the right most node from left subtree
            TreeNode lastRight = findLastRight(root.left);
            //assign the right link of last found rightmost node(in the left subtree) with the node to be deleted
            lastRight.right = rightChild;
            return root.left;
        }
    }

    private TreeNode findLastRight(TreeNode root) {
        if (root.right == null) {
            return root;
        }
        //this goes to the right most node from given root node
        return findLastRight(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        DeleteNodeFromBinarySearchTree inbst = new DeleteNodeFromBinarySearchTree();
        int key = 5;
        System.out.println(inbst.deleteFromBST(root, key).data);
    }
}
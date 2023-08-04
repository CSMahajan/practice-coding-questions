package BinarySearchTree;

/*
Ceil in BST

Given a BST and a number X, find Ceil of X.
Note: Ceil(X) is a number that is either equal to X or is immediately greater than X.
If Ceil could not be found, return -1.
Example 1:
Input:
      5
    /   \
   1     7
    \
     2
      \
       3
X = 3
Output: 3
Explanation: We find 3 in BST, so ceil of 3 is 3.
Example 2:
Input:
     10
    /  \
   5    11
  / \
 4   7
      \
       8
X = 6
Output: 7
Explanation: We find 7 in BST, so ceil of 6 is 7.
*/
public class CeilInBinarySearchTree {

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
    public int findCeil(TreeNode root, int key) {
        //Ceil is to find the smallest value larger than or equal to key
        int ceil = -1;
        if (root == null) {
            return ceil;
        }
        while (root != null) {
            if (key == root.data) {
                ceil = root.data;
                return ceil;
            }
            if (key > root.data) {
                root = root.right;
            } else {
                ceil = root.data;
                root = root.left;
            }
        }
        return ceil;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        CeilInBinarySearchTree cibst = new CeilInBinarySearchTree();
        int key = 5;
        System.out.println(cibst.findCeil(root, key));
    }
}
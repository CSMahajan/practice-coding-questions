package BinarySearchTree;

/*
Floor in BST

You are given a BST(Binary Search Tree) with n number of nodes and value x.
Your task is to find the greatest value node of the BST which is smaller than or equal to x.
Note: when x is smaller than the smallest node of BST then returns -1.
Example:
Input:
n = 7               2
                     \
                      81
                    /     \
                 42       87
                   \       \
                    66      90
                   /
                 45
x = 87
Output:
87
Explanation:
87 is present in tree so floor will be 87.
Example 2:
Input:
n = 4                     6
                           \
                            8
                          /   \
                        7       9
x = 11
Output: 9
*/
public class FloorInBinarySearchTree {

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
    public int findFloor(TreeNode root, int key) {
        //Floor is to find the greatest value smaller than or equal to key
        int floor = -1;
        if (root == null) {
            return floor;
        }
        while (root != null) {
            if (key == root.data) {
                floor = root.data;
                return floor;
            }
            if (key > root.data) {
                floor = root.data;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return floor;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        FloorInBinarySearchTree fibst = new FloorInBinarySearchTree();
        int key = 5;
        System.out.println(fibst.findFloor(root, key));
    }
}
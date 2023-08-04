package BinarySearchTree;

/*
ConstructBinarySearchTree from Preorder Traversal

Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree),
construct the tree and return its root.
It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.
A binary search tree is a binary tree where for every node,
any descendant of Node.left has a value strictly less than Node.val,
and any descendant of Node.right has a value strictly greater than Node.val.
A preorder traversal of a binary tree displays the value of the node first,
then traverses Node.left, then traverses Node.right.
Example 1:
Input: preorder = [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]
Example 2:
Input: preorder = [1,3]
Output: [1,null,3]
*/
public class ConstructBinarySearchTreeFromPreorderTraversal {

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

    public TreeNode bstFromPreorder(int[] preorder) {
        //if we want to keep something constant then we need to pass the variables by reference
        //since in java, pass by reference is not allowed, we will use the array
        return bstFromPreorder(preorder, Integer.MAX_VALUE, new int[]{0});
    }

    //TC:O(3N)=O(N)
    //SC:O(1), O(N) if recursion stack space considered
    private TreeNode bstFromPreorder(int[] preorder, int upperBound, int[] index) {
        //if we cross preorder array or element of preorder at our index(index[0]) is greater than upper bound,
        //then it is not possible to create node, so returning null
        if (index[0] == preorder.length || preorder[index[0]] > upperBound) {
            return null;
        }
        //creating the new node at index[0] and increasing its count
        TreeNode root = new TreeNode(preorder[index[0]++]);
        //for left subtree values, upper bound will be newly created roots data
        root.left = bstFromPreorder(preorder, root.data, index);
        //for right subtree values, upper bound will be previous upper bound of node
        root.right = bstFromPreorder(preorder, upperBound, index);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {8, 5, 1, 7, 10, 12};
        ConstructBinarySearchTreeFromPreorderTraversal cbstpt = new ConstructBinarySearchTreeFromPreorderTraversal();
        System.out.println(cbstpt.bstFromPreorder(preorder).data);
    }
}
package BinaryTree;

import java.util.HashMap;
import java.util.Map;

/*
Construct Binary Tree from Inorder and Postorder Traversal

Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and
postorder is the postorder traversal of the same tree, construct and return the binary tree.
Example 1:
Input:
N = 8
in[] = 4 8 2 5 1 6 3 7
post[] =8 4 5 2 6 7 3 1
Output: 1 2 4 8 5 3 6 7
Explanation: For the given postorder and inorder traversal of tree the  resultant binary tree will be
           1
       /      \
     2         3
   /  \      /  \
  4    5    6    7
   \
     8

Example 2:
Input:
N = 5
in[] = 9 5 2 3 4
post[] = 5 9 3 4 2
Output: 2 9 5 4 3
Explanation:
the  resultant binary tree will be
           2
        /     \
       9       4
        \     /
         5   3
*/
public class ConstructBinaryTreeFromInorderPostorder {

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

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inOrderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inOrderIndexMap.put(inorder[i], i);
        }
        return buildTree(postorder, 0, postorder.length - 1,
                inorder, 0, inorder.length - 1, inOrderIndexMap);
    }

    //Time Complexity: O(N)
    //Assumption: Hashmap returns the answer in constant time
    //Space Complexity: O(N)
    //Reason: We are using an external hashmap of size 'N'
    private TreeNode buildTree(int[] postorder, int postOrderStart, int postOrderEnd, int[] inorder, int inOrderStart, int inOrderEnd, Map<Integer, Integer> inOrderIndexMap) {
        if (postOrderStart > postOrderEnd || inOrderStart > inOrderEnd) {
            return null;
        }
        //last node in the postorder is always root node
        //so, we will prepare root node on that postorder index data
        TreeNode root = new TreeNode(postorder[postOrderEnd]);
        //corresponding root node's index inOrder can be found using the inOrderIndexMap
        //in case of duplicate node data(value) present,
        // write a function which iterates for loop from inOrderStart to inOrderEnd on inorder array and
        //gets the matching node to target node(new node's data) index and return such matching index
        int inOrderRootIndex = inOrderIndexMap.get(root.data);
        //Inside the inOrder traversal, number of elements on left of root index in the inOrder array are
        //the number(and exactly same) of nodes in left subtree of our binary tree and
        //similarly, elements on right side of root index in the inorder array are the number(and exactly same) of nodes right subtree of binary tree
        //so total number of nodes of left subtree can be found
        int noOfNodesOnLeftSubTree = inOrderRootIndex - inOrderStart;
        /*recursively following above process and create the new nodes with left and right child for their corresponding parent
        1.in postOrder traversal array, left subtree nodes are present
        from postOrderStart to(till) the postOrderStart+ number of nodes on left subtree
        right subtree nodes are present from next index of ending of left subtree in postorder(earlier mentioned)
        to(till) the end of number of nodes in postorder array traversal.
        2.in inOrder traversal array, left subtree nodes are present
        from inOrderStart to previous index of inOrderRootIndex
        right subtree nodes are present from next index of inOrderRootIndex to inOrderEnd index*/
        root.left = buildTree(postorder, postOrderStart, postOrderStart + noOfNodesOnLeftSubTree - 1,
                inorder, inOrderStart, inOrderRootIndex - 1, inOrderIndexMap);
        root.right = buildTree(postorder, postOrderStart + noOfNodesOnLeftSubTree, postOrderEnd - 1,
                inorder, inOrderRootIndex + 1, inOrderEnd, inOrderIndexMap);
        return root;
    }

    public static void main(String[] args) {
        int[] postorder = {10, 20, 40, 50, 30, 60};
        int[] inorder = {40, 20, 50, 10, 60, 30};
        ConstructBinaryTreeFromInorderPostorder cbtip = new ConstructBinaryTreeFromInorderPostorder();
        TreeNode root = cbtip.buildTree(inorder, postorder);
    }
}
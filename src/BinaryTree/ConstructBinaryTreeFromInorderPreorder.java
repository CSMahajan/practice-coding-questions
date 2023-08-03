package BinaryTree;

import java.util.HashMap;
import java.util.Map;

/*
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and
inorder is the inorder traversal of the same tree, construct and return the binary tree.
Example 1:
Input:
N = 4
inorder[] = {1 6 8 7}
preorder[] = {1 6 7 8}
Output: 8 7 6 1
Example 2:
Input:
N = 6
inorder[] = {3 1 4 0 5 2}
preorder[] = {0 1 3 4 2 5}
Output: 3 4 1 5 2 0
Explanation: The tree will look like
       0
    /     \
   1       2
 /   \    /
3    4   5
*/
public class ConstructBinaryTreeFromInorderPreorder {

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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inOrderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inOrderIndexMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1, inOrderIndexMap);
    }

    //Time Complexity: O(N)
    //Assumption: Hashmap returns the answer in constant time
    //Space Complexity: O(N)
    //Reason: We are using an external hashmap of size 'N'
    private TreeNode buildTree(int[] preorder, int preOrderStart, int preOrderEnd, int[] inorder, int inOrderStart, int inOrderEnd, Map<Integer, Integer> inOrderIndexMap) {
        if (preOrderStart > preOrderEnd || inOrderStart > inOrderEnd) {
            return null;
        }
        //first node in the preorder is always root node
        //so, we will prepare root node on that preorder index data
        TreeNode root = new TreeNode(preorder[preOrderStart]);
        //corresponding root node's index in order can be found using the inOrderIndexMap
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
        1.in preOrder traversal array, left subtree nodes are present
        from index next of current root nodes index(which is at preOrderStart)
        to(till) the number of nodes on left subtree
        right subtree nodes are present from next index of ending of left subtree in preorder(earlier mentioned)
        to(till) the end of number of nodes in preorder array traversal.
        2.in inOrder traversal array, left subtree nodes are present
        from inOrderStart to previous index of inOrderRootIndex
        right subtree nodes are present from next index of inOrderRootIndex to inOrderEnd index*/
        root.left = buildTree(preorder, preOrderStart + 1, preOrderStart + noOfNodesOnLeftSubTree,
                inorder, inOrderStart, inOrderRootIndex - 1, inOrderIndexMap);
        root.right = buildTree(preorder, preOrderStart + noOfNodesOnLeftSubTree + 1, preOrderEnd,
                inorder, inOrderRootIndex + 1, inOrderEnd, inOrderIndexMap);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {10, 20, 40, 50, 30, 60};
        int[] inorder = {40, 20, 50, 10, 60, 30};
        ConstructBinaryTreeFromInorderPreorder cbtip = new ConstructBinaryTreeFromInorderPreorder();
        TreeNode root = cbtip.buildTree(preorder, inorder);
    }
}
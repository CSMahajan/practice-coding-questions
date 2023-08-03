package BinaryTree;

/*
Flatten binary tree to linked list

Given the root of a binary tree, flatten the tree into a "linked list":
The "linked list" should use the same Node class 
where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.
Example 1:
Input : 
          1
        /   \
       2     5
      / \     \
     3   4     6
Output :
1 2 3 4 5 6 
Explanation: 
After flattening, the tree looks 
like this
    1
     \
      2
       \
        3
         \
          4
           \
            5
             \
              6 
Here, left of each node points to NULL and right contains the next node in preorder.
The inorder traversal of this flattened tree is 1 2 3 4 5 6.
Example 2:
Input :
        1
       / \
      3   4
         /
        2
         \
          5 
Output : 
1 3 4 2 5  
Explanation : 
After flattening, the tree looks 
like this 
     1
      \
       3
        \
         4
          \
           2
            \ 
             5 
Here, left of each node points to NULL and right contains the next node in preorder.
The inorder traversal of this flattened tree is 1 3 4 2 5.
*/
public class FlattenBinaryTreeToLinkedList {

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

    //Time Complexity: O(N)
    //Reason: Time complexity will be the same as that of a morris traversal
    //Space Complexity: O(1)
    //Reason: In Morris Traversal, we are not using any extra space.
    public void flatten(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            if (current.left != null) {
                TreeNode previous = current.left;
                while (previous.right != null) {
                    previous = previous.right;
                }
                previous.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        FlattenBinaryTreeToLinkedList fbtll = new FlattenBinaryTreeToLinkedList();
        fbtll.flatten(root);
    }
}
package BinarySearchTree;

/*
Kth Largest element in BST

Given a Binary search tree.
Your task is to complete the function which will return
the Kth largest element without doing any modification in Binary Search Tree.
Example 1:
Input:
      4
    /   \
   2     9
k = 2
Output: 4
Example 2:
Input:
       9
        \
          10
K = 1
Output: 10
*/
public class KthLargestElementInBinarySearchTree {

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
    //Space Complexity: O(1)
    public int kthLargest(TreeNode root, int k) {
        int count = 0;
        int kthNode = -1;
        TreeNode current = root;
        //Perform Morris Traversal of inorder reverse
        //Instead of adding it to the list for inorder, increase the count, as we only want kth the largest element
        //we can not break the loop after finding the k-th largest element because while traversing we are creating the links
        //between left most node of right subtree pointing left link to the "current" node
        while (current != null) {
            if (current.right == null) {
                // inorder.add(current.val);
                //instead of adding into inorder list just increase counter to find k-th element
                count++;
                if (count == k) {
                    kthNode = current.data;
                }
                current = current.left;
            } else {
                TreeNode successor = current.right;
                //go to left most node of right subtree
                while (successor.left != null && successor.left != current) {
                    successor = successor.left;
                }

                if (successor.left == null) {
                    //create the link if not present
                    successor.left = current;
                    //as link is now created, current can now go to left
                    current = current.right;
                } else {
                    //remove the link if present
                    successor.left = null;
                    // inorder.add(current.val);
                    //instead of adding into inorder list just increase counter to find k-th element
                    count++;
                    if (count == k) {
                        kthNode = current.data;
                    }
                    current = current.left;
                }
            }
        }
        return kthNode;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        KthLargestElementInBinarySearchTree ksebst = new KthLargestElementInBinarySearchTree();
        int k = 3;
        System.out.println(ksebst.kthLargest(root, k));
    }
}
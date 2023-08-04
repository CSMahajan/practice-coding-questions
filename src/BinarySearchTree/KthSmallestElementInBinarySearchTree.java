package BinarySearchTree;

/*
Kth Smallest Element in a BST

Given the root of a binary search tree, and an integer k,
return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
Example 1:
Input: root = [3,1,4,null,2], k = 1
Output: 1
Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3
*/
public class KthSmallestElementInBinarySearchTree {

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
    public int kthSmallest(TreeNode root, int k) {
        int count = 0;
        int kthNode = -1;
        TreeNode current = root;
        //Perform Morris Traversal of inorder
        //Instead of adding it to the list for inorder, increase the count, as we only want kth the smallest element
        //we can not break the loop after finding the k-th smallest element because while traversing we are creating the links
        //between right most node of left subtree pointing right link to the "current" node
        while (current != null) {
            if (current.left == null) {
                // inorder.add(current.val);
                //instead of adding into inorder list just increase counter to find k-th element
                count++;
                if (count == k) {
                    kthNode = current.data;
                }
                current = current.right;
            } else {
                TreeNode previous = current.left;
                //go to right most node of left subtree
                while (previous.right != null && previous.right != current) {
                    previous = previous.right;
                }

                if (previous.right == null) {
                    //create the link if not present
                    previous.right = current;
                    //as link is now created, current can now go to left
                    current = current.left;
                } else {
                    //remove the link if present
                    previous.right = null;
                    // inorder.add(current.val);
                    //instead of adding into inorder list just increase counter to find k-th element
                    count++;
                    if (count == k) {
                        kthNode = current.data;
                    }
                    current = current.right;
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
        KthSmallestElementInBinarySearchTree ksebst = new KthSmallestElementInBinarySearchTree();
        int k = 5;
        System.out.println(ksebst.kthSmallest(root, k));
    }
}
package BinaryTree;

/*
Count Number of Nodes in a Binary Tree

You are given the root of a complete binary tree. Your task is to find the count of nodes.
A complete binary tree is a binary tree whose, all levels except the last one are completely filled,
the last level may or may not be completely filled and Nodes in the last level are as left as possible.
Design an algorithm that runs better than O(n).
Example:
Input:
root = [1,2,3,4,5,6]
Output:
6
Explanation:
There are a total of 6 nodes in the given tree.
*/
public class CountNodesBinaryTree {

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

    //Time Complexity: O(log(N)*log(N))
    //Reason: To find the leftHeight and right Height we need only logN time and
    //in the worst case we will encounter the second case(leftHeight!=rightHeight) for at max logN times,
    //so total time complexity will be O(log N * logN)
    //Space Complexity: O(logN)
    //Reason: Space is needed for the recursion stack when calculating height.
    //As it is a complete tree, the height will always be logN.
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = findLeftHeight(root);
        int rightHeight = findRightHeight(root);
        if (leftHeight == rightHeight) {
            //As the given binary tree is a complete binary tree,
            //if at any point left and right subtree has same height means total number of nodes will be 2^(height)-1
            //Note:as left shift or right operator has less priority than operators such as (+,-,*,%),
            //so compulsoriyly give the bracket to calculate correctly
            return (1 << (leftHeight)) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int findLeftHeight(TreeNode node) {
        int leftHeight = 0;
        while (node != null) {
            leftHeight++;
            node = node.left;
        }
        return leftHeight;
    }

    private int findRightHeight(TreeNode node) {
        int rightHeight = 0;
        while (node != null) {
            rightHeight++;
            node = node.right;
        }
        return rightHeight;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        //root.right.right = new TreeNode(7);
        CountNodesBinaryTree cnbt = new CountNodesBinaryTree();
        System.out.println(cnbt.countNodes(root));
    }
}
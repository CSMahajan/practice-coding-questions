package BinarySearchTree;

public class CheckBSTFromInorderTraversal {

    public static boolean isValidBST(int[] order) {
        // Write your code here.
        for (int i = 0; i < order.length - 1; i++) {
            if (order[i] > order[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] inorder = {1, 3, 4, 5, 7, 8, 9};
        System.out.println(isValidBST(inorder));
    }
}
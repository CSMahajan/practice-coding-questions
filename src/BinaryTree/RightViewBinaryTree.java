package BinaryTree;

import java.util.ArrayList;
import java.util.List;

/*
Right View of Binary Tree

Given a Binary Tree, find Right view of it.
Right view of a Binary Tree is set of nodes visible when tree is viewed from right side.
Right view of following tree is 1 3 7 8.

          1
       /     \
     2        3
   /   \      /    \
  4     5   6    7
    \
     8

Example 1:
Input:
       1
    /    \
   3      2
Output: 1 2
Example 2:
Input:
     10
    /   \
  20     30
 /   \
40  60
Output: 10 30 60
*/
public class RightViewBinaryTree {

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
    //Space Complexity: O(H), where (H -> Height of the Tree)
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightView = new ArrayList<>();
        if(root==null){
            return rightView;
        }
        //root is at level 0
        rightSideView(root,0, rightView);
        return rightView;
    }

    private void rightSideView(TreeNode node, int level, List<Integer> rightView) {
        if(node==null){
            return;
        }
        //if the level number equals our data structure's size that means that level has been reached for the first time
        //so then only we will add
        //so whenever any recursive call with different level comes
        //if any other node has already been considered for that level then we will not add
        //this is because first recursive call for right view, we will keep for right side node and then left side node
        //this will ensure right side nodes are added to traversal data structure
        if(level == rightView.size()){
            rightView.add(node.data);
        }
        rightSideView(node.right,level+1,rightView);
        rightSideView(node.left,level+1,rightView);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        RightViewBinaryTree rvbt = new RightViewBinaryTree();
        System.out.println(rvbt.rightSideView(root));
    }
}
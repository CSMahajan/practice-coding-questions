package BinaryTree;

import java.util.ArrayList;
import java.util.List;

/*
Left View of Binary Tree

Given a Binary Tree, return Left view of it.
Left view of a Binary Tree is set of nodes visible when tree is visited from Left side.
The task is to complete the function leftView(), which accepts root of the tree as argument.
Left view of following tree is 1 2 4 8.

          1
       /    \
     2        3
   /   \     /  \
  4     5   6    7
   \
    8

Example 1:
Input:
   1
 /  \
3    2
Output: 1 3
Example 2:
Input:
      10
     /  \
    20   30
   /  \
 40   60
Output: 10 20 40
*/
public class LeftViewBinaryTree {

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
    public List<Integer> leftSideView(TreeNode root) {
        List<Integer> leftView = new ArrayList<>();
        if(root==null){
            return leftView;
        }
        //root is at level 0
        leftSideView(root,0, leftView);
        return leftView;
    }

    private void leftSideView(TreeNode node, int level, List<Integer> leftView) {
        if(node==null){
            return;
        }
        //if the level number equals our data structure's size that means that level has been reached for the first time
        //so then only we will add
        //so whenever any recursive call with different level comes
        //if any other node has already been considered for that level then we will not add
        //this is because first recursive call for left view, we will keep for left side node and then right side node
        //this will ensure left side nodes are added to traversal data structure
        if(level == leftView.size()){
            leftView.add(node.data);
        }
        leftSideView(node.left,level+1,leftView);
        leftSideView(node.right,level+1,leftView);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        LeftViewBinaryTree lvbt = new LeftViewBinaryTree();
        System.out.println(lvbt.leftSideView(root));
    }
}
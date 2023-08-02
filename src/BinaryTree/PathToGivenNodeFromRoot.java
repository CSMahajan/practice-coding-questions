package BinaryTree;

import java.util.ArrayList;
import java.util.List;

/*
Print the path from root node to the given node
*/
public class PathToGivenNodeFromRoot {

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

    public List<Integer> getPathToGivenNode(TreeNode root, int target){
        List<Integer> path = new ArrayList<>();
        if(root==null){
            return path;
        }
        getPathToGivenNode(root,target,path);
        return path;
    }

    private boolean getPathToGivenNode(TreeNode node, int target, List<Integer> path) {
        if(node==null){
            return false;
        }
        path.add(node.data);
        if(node.data==target){
            return true;
        }
        //if on either left or right, we find the target, then returning true
        if(getPathToGivenNode(node.left,target,path)||getPathToGivenNode(node.right,target,path)){
            return true;
        }
        //removing the node as through that node we did not find the target
        path.remove(path.size()-1);
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        PathToGivenNodeFromRoot ptgnfr = new PathToGivenNodeFromRoot();
        int target = 5;
        System.out.println(ptgnfr.getPathToGivenNode(root,target));
    }
}
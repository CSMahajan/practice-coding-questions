package BinaryTree;

import java.util.*;

/*
Burning Tree

Given a binary tree and a node data called target.
Find the minimum time required to burn the complete binary tree if the target is set on fire.
It is known that in 1 second all nodes connected to a given node get burned.
That is its left child, right child, and parent.
Note: The tree contains unique values.
Example 1:
Input:
          1
        /   \
      2      3
    /  \      \
   4    5      6
       / \      \
      7   8      9
                   \
                   10
Target Node = 8
Output: 7
Explanation: If leaf with the value
8 is set on fire.
After 1 sec: 5 is set on fire.
After 2 sec: 2, 7 are set to fire.
After 3 sec: 4, 1 are set to fire.
After 4 sec: 3 is set to fire.
After 5 sec: 6 is set to fire.
After 6 sec: 9 is set to fire.
After 7 sec: 10 is set to fire.
It takes 7s to burn the complete tree.
Example 2:
Input:
          1
        /   \
      2      3
    /  \      \
   4    5      7
  /    /
 8    10
Target Node = 10
Output: 5
*/
public class MinimumTimeBurningTree {

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

    //TC:O(N)+O(N)
    //First O(N) for marking the parents bfs traversal
    //Second O(N) for traversing till K distance in queue bfs traversal, on worst K can be N so, O(N)
    //SC:O(N)+O(N)+O(N)
    //First O(N) for parent, Second O(N) for visited, Third O(N) for queue
    public static int minBurningTime(TreeNode root, int target) {
        // Your code goes here
        //Step 1: Marking the parents
        //parentMap stores the parent of the current node where current node as the key and parent node as the value
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        TreeNode targetNode = markParentsForAllNodes(root, parentMap, target);
        //Step 2: Traversing till reaches end of binary tree(towards parent,left child,right child)
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(targetNode);
        //visited map stores if the node is visited or not
        Map<TreeNode, Boolean> visitedBurntNodesMap = new HashMap<>();
        visitedBurntNodesMap.put(targetNode, true);
        int currentTime = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isAnyNodeBurnt = false;
            for (int i = 0; i < size; i++) {
                //for every node in our current level, we need to traverse towards left child, right child, parent node
                TreeNode current = queue.poll();
                if (current != null) {
                    //Traverse towards left child node
                    if (current.left != null && !visitedBurntNodesMap.containsKey(current.left)) {
                        isAnyNodeBurnt = true;
                        queue.add(current.left);
                        visitedBurntNodesMap.put(current.left, true);
                    }
                    //Traverse towards right child node
                    if (current.right != null && !visitedBurntNodesMap.containsKey(current.right)) {
                        isAnyNodeBurnt = true;
                        queue.add(current.right);
                        visitedBurntNodesMap.put(current.right, true);
                    }
                    //Traverse towards parent node
                    TreeNode parentNode = parentMap.get(current);
                    if (parentNode != null && !visitedBurntNodesMap.containsKey(parentNode)) {
                        isAnyNodeBurnt = true;
                        queue.add(parentNode);
                        visitedBurntNodesMap.put(parentNode, true);
                    }
                }
            }
            if(isAnyNodeBurnt) {
                currentTime++;
            }
        }
        return currentTime;
    }

    private static TreeNode markParentsForAllNodes(TreeNode root, Map<TreeNode, TreeNode> parentMap, int target) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode targetNode = null;
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if(current.data==target){
                targetNode = current;
            }
            if (current.left != null) {
                parentMap.put(current.left, current);
                queue.add(current.left);
            }
            if (current.right != null) {
                parentMap.put(current.right, current);
                queue.add(current.right);
            }
        }
        return targetNode;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        int target = 5;
        System.out.println(minBurningTime(root,target));
    }
}
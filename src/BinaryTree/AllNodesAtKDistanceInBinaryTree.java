package BinaryTree;

import java.util.*;

/*
All Nodes Distance K in Binary Tree

Given the root of a binary tree, the value of a target node target, and an integer k,
return an array of the values of all nodes that have a distance k from the target node.
You can return the answer in any order.
Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
Example 2:
Input: root = [1], target = 1, k = 3
Output: []
Example 1:
Input:
          20
        /    \
      8       22
    /   \
   4    12
       /   \
      10    14
Target Node = 8
K = 2
Output: 10 14 22
Explanation: The three nodes at distance 2
from node 8 are 10, 14, 22.
Example 2:
Input:
         20
       /    \
      7      24
    /   \
   4     3
        /
       1
Target Node = 7
K = 2
Output: 1 24
*/
public class AllNodesAtKDistanceInBinaryTree {

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
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        //Step 1: Marking the parents
        List<Integer> allNodesAtDistanceK = new ArrayList<>();
        //parentMap stores the parent of the current node where current node as the key and parent node as the value
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        markParentsForAllNodes(root, parentMap);
        //Step 2: Traversing till K distance(level) from target node
        //as K levels signify K distance in binary tree(towards parent,left child,right child)
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        //visited map stores if the node is visited or not
        Map<TreeNode, Boolean> visitedNodes = new HashMap<>();
        visitedNodes.put(target, true);
        int currentLevel = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (currentLevel == k) {
                break;
            }
            currentLevel++;
            for (int i = 0; i < size; i++) {
                //for every node in our current level, we need to traverse towards left child, right child, parent node
                TreeNode current = queue.poll();
                if (current != null) {
                    //Traverse towards left child node
                    if (current.left != null && !visitedNodes.containsKey(current.left)) {
                        queue.add(current.left);
                        visitedNodes.put(current.left, true);
                    }
                    //Traverse towards right child node
                    if (current.right != null && !visitedNodes.containsKey(current.right)) {
                        queue.add(current.right);
                        visitedNodes.put(current.right, true);
                    }
                    //Traverse towards parent node
                    TreeNode parentNode = parentMap.get(current);
                    if (parentNode != null && !visitedNodes.containsKey(parentNode)) {
                        queue.add(parentNode);
                        visitedNodes.put(parentNode, true);
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            allNodesAtDistanceK.add(queue.poll().data);
        }
        return allNodesAtDistanceK;
    }

    private void markParentsForAllNodes(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current.left != null) {
                parentMap.put(current.left, current);
                queue.add(current.left);
            }
            if (current.right != null) {
                parentMap.put(current.right, current);
                queue.add(current.right);
            }
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
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(11);
        root.right.left.left = new TreeNode(12);
        root.right.left.right = new TreeNode(13);
        root.right.right.left = new TreeNode(14);
        root.right.right.right = new TreeNode(15);
        TreeNode target = root.left.right;
        int k = 5;
        AllNodesAtKDistanceInBinaryTree ankbt = new AllNodesAtKDistanceInBinaryTree();
        System.out.println(ankbt.distanceK(root, target, k));
    }
}
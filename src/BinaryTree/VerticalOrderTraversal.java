package BinaryTree;

import java.util.*;

/*
Vertical Traversal of Binary Tree

GeeksForGeeks
Given a Binary Tree, find the vertical traversal of it starting from the leftmost level to the rightmost level.
If there are multiple nodes passing through a vertical line,
then they should be printed as they appear in level order traversal of the tree.

Vertical Order Traversal of a Binary Tree

LeetCode
Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
For each node at position (row, col), its left and right children will be
at positions (row + 1, col - 1) and (row + 1, col + 1) respectively.
The root of the tree is at (0, 0).
The vertical order traversal of a binary tree is a list of top-to-bottom orderings
for each column index starting from the leftmost column and ending on the rightmost column.
There may be multiple nodes in the same row and same column.
In such a case, sort these nodes by their values.
Return the vertical order traversal of the binary tree.
*/
public class VerticalOrderTraversal {

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

    static class Tuple {
        TreeNode node;
        int verticalOfNodeAsRow;
        int levelOfNodeAsColumn;

        public Tuple(TreeNode node, int verticalOfNodeAsRow, int levelOfNodeAsColumn) {
            this.node = node;
            this.verticalOfNodeAsRow = verticalOfNodeAsRow;
            this.levelOfNodeAsColumn = levelOfNodeAsColumn;
        }
    }

    //Time Complexity: O(N*logN*logN*logN)
    //Space Complexity: O(N)
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        //We need a custom data structure which stores the vertical corresponding to the level
        //which has all the nodes in sorted order(if nodes at same vertical and level)
        //TreeMap can maintain entries in sorted order(or if any comparator of custom order given)
        //So our outer treemap has vertical row number as the key where 0 is the vertical row number for root node
        //left side nodes have -1,-2,-3... and right side nodes have 1,2,3... so on as the vertical number
        //inner TreeMap has level number of level order as the key and its value, the priority queue stores the nodes in ascending order
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(root, 0, 0));
        //Step 1: Operate on queue like level order traversal
        while (!queue.isEmpty()) {
            Tuple tuple = queue.poll();
            TreeNode node = tuple.node;
            int verticalOfNodeAsRow = tuple.verticalOfNodeAsRow;
            int levelOfNodeAsColumn = tuple.levelOfNodeAsColumn;
            //Creating the vertical if it is not created earlier
            if (!map.containsKey(verticalOfNodeAsRow)) {
                map.put(verticalOfNodeAsRow, new TreeMap<>());
            }
            //Creating the level for that vertical if it is not created earlier
            if (!map.get(verticalOfNodeAsRow).containsKey(levelOfNodeAsColumn)) {
                map.get(verticalOfNodeAsRow).put(levelOfNodeAsColumn, new PriorityQueue<>());
            }
            //Adding the data(value) of that node to its priority queue(priority queue used as we need to sort elements having same vertical and level)
            map.get(verticalOfNodeAsRow).get(levelOfNodeAsColumn).offer(node.data);
            //check for left side node, if present, add it to the queue of tuple
            if (node.left != null) {
                queue.offer(new Tuple(node.left, verticalOfNodeAsRow - 1, levelOfNodeAsColumn + 1));
            }
            //check for right side node, if present, add it to the queue of tuple
            if (node.right != null) {
                queue.offer(new Tuple(node.right, verticalOfNodeAsRow + 1, levelOfNodeAsColumn + 1));
            }
        }
        //Step 2:Get the content from treemap into our desired result format(list of list)
        List<List<Integer>> verticalOrder = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> verticalRowEntries : map.values()) {
            //Creating the empty new arraylist for each vertical
            verticalOrder.add(new ArrayList<>());
            for (PriorityQueue<Integer> sortedNodesPriorityQueue : verticalRowEntries.values()) {
                while (!sortedNodesPriorityQueue.isEmpty()) {
                    //Getting the nodes present at the levels into the verticalOrder list
                    verticalOrder.get(verticalOrder.size() - 1).add(sortedNodesPriorityQueue.poll());
                }
            }
        }
        return verticalOrder;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        VerticalOrderTraversal vot = new VerticalOrderTraversal();
        System.out.println(vot.verticalTraversal(root));
    }
}
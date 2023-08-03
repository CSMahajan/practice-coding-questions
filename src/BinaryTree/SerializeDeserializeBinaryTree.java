package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/*
Serialize and Deserialize a Binary Tree

LeetCode
Serialization is the process of converting a data structure or object into
a sequence of bits so that it can be stored in a file or memory buffer,
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
Design an algorithm to serialize and deserialize a binary tree.
There is no restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that a binary tree can be serialized to a string and
this string can be deserialized to the original tree structure.
Clarification: The input/output format is the same as how LeetCode serializes a binary tree.
You do not necessarily need to follow this format,
so please be creative and come up with different approaches yourself.

GeekForGeeks
Serialization is to store a tree in an array so that it can be later restored and
Deserialization is reading tree back from the array. Now your task is to complete the function serialize which
stores the tree into an array A[ ] and deSerialize which deserializes the array to the tree and returns it.
Note: The structure of the tree must be maintained. Multiple nodes can have the same data.
*/
public class SerializeDeserializeBinaryTree {

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
    //Space Complexity: O(N)
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        //To serialize is to convert binary tree into string
        //We will use level order traversal
        //if we encounter a null in the node then we will change it to #
        //the distinguishing character between two nodes while representing in string is space( )
        //if encounter a node,we will append it as it is and its left and right child also(even if children are null)
        StringBuilder serializedStringBuilder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                //found null node, append # to the serialized string with space
                serializedStringBuilder.append("# ");
                continue;
            }
            //found non-null node, append to the serialized string with space
            serializedStringBuilder.append(node.data).append(" ");
            //add left and right child into the queue without checking for null
            queue.add(node.left);
            queue.add(node.right);
        }
        return serializedStringBuilder.toString();
    }

    //Time Complexity: O(N)
    //Space Complexity: O(N)
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        //To deserialize is to convert serialized string back to its original binary tree
        //As we had used the space character as distinguishing letter in the string while serializing
        //we will get the string array where every element will be node(non-null nodes as well as null nodes) based on space
        String[] serializedNodeValues = data.split(" ");
        Queue<TreeNode> queue = new LinkedList<>();
        //first letter is root node of the binary tree
        TreeNode root = new TreeNode(Integer.parseInt(serializedNodeValues[0]));
        queue.add(root);
        for (int i = 1; i < serializedNodeValues.length; i++) {
            //As we had used level order traversal while serializing, we will also use same here
            TreeNode parentNode = queue.poll();
            //if we encounter any integer value(not as #), then we make that as the left child of our current parent node
            if(!serializedNodeValues[i].equals("#")){
                TreeNode leftChild = new TreeNode(Integer.parseInt(serializedNodeValues[i]));
                parentNode.left = leftChild;
                queue.add(leftChild);
            }
            //next character in string array if not null, then assigned as right child of our current parent node
            //Note:here we pre incremented i because we need to check for next letter(node) in the same iteration
            //this is done as in every iteration, firstly we are polling the node from queue
            //otherwise we would miss to assign the right child-parent relationship
            //if post increment operator would have used caused problem as it increases value after evaluation of expression
            //which is not desirable
            if(!serializedNodeValues[++i].equals("#")){
                TreeNode rightChild = new TreeNode(Integer.parseInt(serializedNodeValues[i]));
                parentNode.right = rightChild;
                queue.add(rightChild);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        SerializeDeserializeBinaryTree sdbt = new SerializeDeserializeBinaryTree();
        String serializedString = sdbt.serialize(root);
        System.out.println(serializedString);
        TreeNode deserializedRoot = sdbt.deserialize(serializedString);
    }
}
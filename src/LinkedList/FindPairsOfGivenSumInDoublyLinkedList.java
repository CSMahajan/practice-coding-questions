package LinkedList;

import java.util.*;

/*
Find pairs with given sum in doubly linked list

A doubly-linked list is a data structure that consists of sequentially linked nodes,
and the nodes have reference to both the previous and the next nodes in the sequence of nodes.
You are given a sorted doubly linked list of size ‘n’, consisting of distinct positive integers, and a number ‘k’
Find out all the pairs in the doubly linked list with sum equal to 'k’.
Example :
Input: Linked List: 1 <-> 2.<-> 3 <-> 4.<-> 9nd’
Output: (1, 4) and (2, 3)
Explanation: There are 2 pairs in the linked list having sum ‘k'
Sample input 1:
5
1 2 3 4 9
5
Sample output 1
2
1 4
2 3
Explanation for sample input 1 :
There are 2 pairs in the linked list having sum equal to 'k' (= 5).
*/
public class FindPairsOfGivenSumInDoublyLinkedList {

    static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
        }
    }

    public boolean isPairWithGivenSumExists(Node head, int k) {
        // Write your code here.
        Node temp = head;
        Map<Integer, Integer> map = new HashMap<>();
        while (temp != null) {
            if (map.containsKey(k - temp.data)) {
                return true;
            }
            map.put(temp.data, 1);
            temp = temp.next;
        }
        return false;
    }

    public List<List<Integer>> findPairsWithGivenSum(Node head, int k) {
        // Write your code here.
        Node left = head;
        Node right = findTail(head);
        List<List<Integer>> pairsList = new ArrayList<>();
        if (head == null) {
            return pairsList;
        }
        while (left.data < right.data) {
            if (left.data + right.data == k) {
                List<Integer> pair = new ArrayList<>();
                pair.add(left.data);
                pair.add(right.data);
                pairsList.add(pair);
                left = left.next;
                right = right.prev;
            } else if (left.data + right.data < k) {
                left = left.next;
            } else {
                right = right.prev;
            }
        }
        return pairsList;
    }

    private Node findTail(Node head) {
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        return tail;
    }


    public void displayLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        FindPairsOfGivenSumInDoublyLinkedList fpgsdll = new FindPairsOfGivenSumInDoublyLinkedList();
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.prev = head;
        head.next.next = new Node(3);
        head.next.next.prev = head.next;
        head.next.next.next = new Node(4);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.prev = head.next.next.next;
        int sum = 5;
        System.out.println("Finding the sum as " + sum + " from given linked list");
        fpgsdll.displayLinkedList(head);
        boolean isSumPresent = fpgsdll.isPairWithGivenSumExists(head, sum);
        System.out.println();
        System.out.println("Is sum present with the sum as " + sum + " ?");
        System.out.println(isSumPresent);
        System.out.println("All pairse with sum as " + sum + " are as follows...");
        System.out.println(fpgsdll.findPairsWithGivenSum(head, sum));
    }
}
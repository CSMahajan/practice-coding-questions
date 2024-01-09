package LinkedList;

import java.util.PriorityQueue;

/*
Merge k Sorted Lists

You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.
Example 1:
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:
Input: lists = []
Output: []
Example 3:
Input: lists = [[]]
Output: []
*/
public class MergeKSortedLinkedList {

    static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }

    }

    static class Pair {
        int nodeValue;
        ListNode node;

        public Pair(int nodeValue, ListNode node) {
            this.nodeValue = nodeValue;
            this.node = node;
        }
    }

    //TC:O(K*logK + N*K*logK)
    //Reason: for loop has K lists are present and to add each element in priority queue takes logK time
    //while loop has for at most N elements per linked list with 2 operations(add,poll) of logK time
    //SC:O(K)
    //Reason: we need to store at a time K elements(1 element from each list into priority queue
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((p1, p2) -> p1.nodeValue - p2.nodeValue);
        for (ListNode node : lists) {
            if (node != null) {
                priorityQueue.offer(new Pair(node.data, node));
            }
        }
        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;
        while (!priorityQueue.isEmpty()) {
            Pair pair = priorityQueue.poll();
            if (pair.node.next != null) {
                priorityQueue.add(new Pair(pair.node.next.data, pair.node.next));
            }
            temp.next = pair.node;
            temp = temp.next;
        }
        return dummyNode.next;
    }

    public void displayLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        MergeKSortedLinkedList mksll = new MergeKSortedLinkedList();
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);
        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);
        ListNode[] lists = {head1, head2, head3};
        System.out.print("Given linked list: ");
        System.out.println();
        for (ListNode node : lists) {
            mksll.displayLinkedList(node);
            System.out.println();
        }
        ListNode mergedKListHead = mksll.mergeKLists(lists);
        System.out.print("Merged linked list: ");
        mksll.displayLinkedList(mergedKListHead);
    }
}
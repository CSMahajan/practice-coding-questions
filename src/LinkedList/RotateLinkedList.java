package LinkedList;

/*
Rotate a linked list

Given a singly linked list of size N. The task is to right-shift the linked list by k nodes,
where k is a given positive integer smaller than or equal to length of the linked list.
Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
Example 2:
Input: head = [0,1,2], k = 4
Output: [2,0,1]
*/

public class RotateLinkedList {

    static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }

    }

    //Time Complexity: O(length of list) + O(length of list – (length of list%k))
    //Reason: O(length of the list) for calculating the length of the list.
    //O(length of the list – (length of list%k)) for breaking link.
    //Space Complexity: O(1)
    //Reason: No extra data structure is used for computation.
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int length = 1;
        ListNode current = head;
        while (current.next != null) {
            length++;
            current = current.next;
        }
        //last node pointing to first node
        current.next = head;
        //if k greater than length then modulo will give remainder as same level/number of rotations we require
        k = k % length;
        //in case of left shift below step should not be done
        k = length - k;
        //so traversing length - k and mark last node as null but before that make sure store next of last node as new head
        while (k-- > 0) {
            current = current.next;
        }
        head = current.next;
        current.next = null;
        return head;
    }

    //Time Complexity: O(length of list) + O(length of list – (length of list%k))
    //Reason: O(length of the list) for calculating the length of the list.
    //O(length of the list – (length of list%k)) for breaking link.
    //Space Complexity: O(1)
    //Reason: No extra data structure is used for computation.
    public ListNode rotateRightKTimes(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        if (k % length == 0) {
            return head;
        }
        //assigning next of tail as head
        tail.next = head;
        //finding (length - k)th node to mark it as last node;
        ListNode nthNode = getNthNode(head, length - k);
        //before marking n-th node as the last node, updating head
        head = nthNode.next;
        nthNode.next = null;
        return head;
    }

    //Time Complexity: O(2 * length of list)
    //Reason: O(length of the list) for calculating the length of the list.
    //O(length of the list for breaking link at k = 1 at worst.
    //Space Complexity: O(1)
    //Reason: No extra data structure is used for computation.
    public ListNode rotateLeftKTimes(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        if (k % length == 0) {
            return head;
        }
        k = k % length;
        //performing below step only for left rotation,
        //in case of right it is not required to be performed i.e.(k = length - k)
        k = length - k;
        //assigning next of tail as head
        tail.next = head;
        //finding (length - k)th node to mark it as last node;
        ListNode nthNode = getNthNode(head, length - k);
        //before marking n-th node as the last node, updating head
        head = nthNode.next;
        nthNode.next = null;
        return head;
    }

    private ListNode getNthNode(ListNode temp, int k) {
        int count = 1;
        while (temp != null) {
            if (count == k) {
                return temp;
            }
            count++;
            temp = temp.next;
        }
        return temp;
    }

    public void displayLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        RotateLinkedList rll = new RotateLinkedList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        int k = 3;
        System.out.println("Before right rotating a linked list by " + k);
        rll.displayLinkedList(head);
        ListNode rotatedHead = rll.rotateRightKTimes(head, k);
        System.out.println();
        System.out.println("After right rotating a linked list by " + k);
        rll.displayLinkedList(rotatedHead);
    }
}
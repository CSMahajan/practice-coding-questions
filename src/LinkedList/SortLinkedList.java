package LinkedList;

/*
Sort a given linked list

Given the head of a linked list, return the list after sorting it in ascending order.
Example 1:
Input: head = [4,2,1,3]
Output: [1,2,3,4]
Example 2:
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
Example 3:
Input: head = []
Output: []
*/
public class SortLinkedList {

    static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }

    }

    //TC: O(logN *(N+N/2))
    //SC: O(logN)
    public ListNode sortLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //applying merge sort on linked list
        //similar to arrays only difference is finding middle element/node using tortoise and hare algorithm
        ListNode middleNode = findMiddleNode(head);
        ListNode leftHead = head;
        ListNode rightHead = middleNode.next;
        //cutting the link after middle node so as we have 2 separate linked list
        middleNode.next = null;
        leftHead = sortLinkedList(leftHead);
        rightHead = sortLinkedList(rightHead);
        return mergeSortedLinkedLists(leftHead, rightHead);
    }

    private ListNode mergeSortedLinkedLists(ListNode head1, ListNode head2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;
        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                temp.next = head1;
                temp = temp.next;
                head1 = head1.next;
            } else {
                temp.next = head2;
                temp = temp.next;
                head2 = head2.next;
            }
        }
        if (head1 != null) {
            temp.next = head1;
        } else {
            temp.next = head2;
        }
        return dummyNode.next;
    }

    private ListNode findMiddleNode(ListNode head) {
        ListNode slow = head;
        //we are starting fast 1 place ahead than normal
        //tortoise and hare(slow and fast pointer approach) to find middle node of linked list
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void displayLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        SortLinkedList sll = new SortLinkedList();
        ListNode head = new ListNode(2);
        head.next = new ListNode(1);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next = new ListNode(0);
        head.next.next.next.next.next.next = new ListNode(8);
        System.out.println("Linked List before sorting");
        sll.displayLinkedList(head);
        ListNode sortHead = sll.sortLinkedList(head);
        System.out.println();
        System.out.println("Linked List after sorting");
        sll.displayLinkedList(sortHead);
    }
}
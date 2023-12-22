/*
Reverse a linked list

Given a linked list of N nodes. The task is to reverse this list.
Example 1:
Input:
LinkedList: 1->2->3->4->5->6
Output: 6 5 4 3 2 1
Explanation: After reversing the list,
elements are 6->5->4->3->2->1.
Example 2:
Input:
LinkedList: 2->7->8->9->10
Output: 10 9 8 7 2
Explanation: After reversing the list,
elements are 10->9->8->7->2.
*/
package LinkedList;

public class ReverseLinkedListRecursive {

    static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reverseHead = reverseList(head.next);
        ListNode front = head.next;
        front.next = head;
        head.next = null;
        return reverseHead;
    }

    public void displayLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        ReverseLinkedListRecursive rllr = new ReverseLinkedListRecursive();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println("Before reversing a linked list recursive");
        rllr.displayLinkedList(head);
        ListNode reverseHead = rllr.reverseList(head);
        System.out.println();
        System.out.println("After reversing a linked list recursive");
        rllr.displayLinkedList(reverseHead);
    }
}
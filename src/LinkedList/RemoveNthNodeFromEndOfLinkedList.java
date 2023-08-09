package LinkedList;

/*
Remove Nth Node From End of List

Given the head of a linked list, remove the nth node from the end of the list and return its head.
Example 1:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:
Input: head = [1], n = 1
Output: []
Example 3:
Input: head = [1,2], n = 1
Output: [1]
*/
public class RemoveNthNodeFromEndOfLinkedList {

    static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }

        public ListNode() {
        }
    }

    //Time Complexity: O(N)
    //Space Complexity: O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode();
        start.next = head;
        ListNode fast = start;
        ListNode slow = start;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        //need to return start.next as head node can also be removed which might create pointing problems
        return start.next;
    }

    //Time Complexity: O(N)
    //Space Complexity: O(1)
    public ListNode removeNthFromEndOptimised(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        while (fast.next != null) {
            fast = fast.next;
            if (n-- <= 0) {
                slow = slow.next;
            }
        }
        if (slow.next != null) {
            slow.next = slow.next.next;
        }
        return dummy.next;
    }

    public void displayLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEndOfLinkedList rnfell = new RemoveNthNodeFromEndOfLinkedList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println("Before removing nth node from end of linked list");
        rnfell.displayLinkedList(head);
        int n = 2;
        ListNode reverseHead = rnfell.removeNthFromEnd(head, n);
        System.out.println();
        System.out.println("After removing nth node from end of linked list");
        rnfell.displayLinkedList(reverseHead);
    }
}
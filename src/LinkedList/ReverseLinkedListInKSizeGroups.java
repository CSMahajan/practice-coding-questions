package LinkedList;

/*
Reverse a Linked List in groups of given size

Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
k is a positive integer and is less than or equal to the length of the linked list.
If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
You may not alter the values in the list's nodes, only nodes themselves may be changed.
Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]
Example 2:
Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]
Example 3:
Input:
LinkedList: 1->2->2->4->5->6->7->8, k = 4
Output: 4 2 2 1 8 7 6 5
Explanation:
The first 4 elements 1,2,2,4 are reversed first and then the next 4 elements 5,6,7,8.
Hence, the resultant linked list is 4->2->2->1->8->7->6->5.
*/

public class ReverseLinkedListInKSizeGroups {

    static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }

    }

    //Time Complexity: O(N)
    //Reason: Nested iteration with O((N/k)*k) which makes it equal to O(N).
    //Space Complexity: O(1)
    //Reason: No extra data structures are used for computation.
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int length = getLengthOfLinkedList(head);
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode previous = dummy;
        ListNode current;
        ListNode nextNode;
        while (length >= k) {
            current = previous.next;
            nextNode = current.next;
            for (int i = 1; i < k; i++) {
                current.next = nextNode.next;
                nextNode.next = previous.next;
                previous.next = nextNode;
                nextNode = current.next;
            }
            previous = current;
            length -= k;
        }
        return dummy.next;
    }

    private int getLengthOfLinkedList(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    public void displayLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        ReverseLinkedListInKSizeGroups rllksg = new ReverseLinkedListInKSizeGroups();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        System.out.println("Before reversing a linked list in k size groups");
        rllksg.displayLinkedList(head);
        int k = 3;
        ListNode reverseHead = rllksg.reverseKGroup(head, k);
        System.out.println();
        System.out.println("After reversing a linked list in k size groups");
        rllksg.displayLinkedList(reverseHead);
    }
}
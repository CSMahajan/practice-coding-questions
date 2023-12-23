package LinkedList;

/*
Delete Middle of Linked List

Given a singly linked list, delete middle of the linked list.
For example, if given linked list is 1->2->3->4->5 then linked list should be modified to 1->2->4->5.
If there are even nodes, then there would be two middle nodes, we need to delete the second middle element.
For example, if given linked list is 1->2->3->4->5->6 then it should be modified to 1->2->3->5->6.
If the input linked list is NULL or has 1 node, then it should return NULL
Example 1:
Input:
LinkedList: 1->2->3->4->5
Output: 1 2 4 5
Example 2:
Input:
LinkedList: 2->4->6->7->5->1
Output: 2 4 6 5 1
*/
public class DeleteMiddleNodeLinkedList {

    static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }
    }

    public ListNode deleteMiddleNode(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode previous = null;
        while(fast != null && fast.next != null){
            previous = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if(slow != head && previous.next != null){
            previous.next = previous.next.next;
        } else head = null;
        return head;
    }

    public ListNode deleteMiddleNodeWithoutPreviousNodeVariable(ListNode head) {
            if(head == null || head.next == null){
                return null;
            }
            ListNode slow = head;
            ListNode fast = head;
            fast = fast.next.next;
            while(fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode middle = slow.next;
            slow.next = slow.next.next;
            return head;
    }

    public void displayLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        DeleteMiddleNodeLinkedList mll = new DeleteMiddleNodeLinkedList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println("Before deleting middle node of a linked list");
        mll.displayLinkedList(head);
        ListNode deletedMiddleNodeHead = mll.deleteMiddleNodeWithoutPreviousNodeVariable(head);
        System.out.println();
        System.out.println("After deleting middle node of a linked list");
        mll.displayLinkedList(deletedMiddleNodeHead);
    }
}

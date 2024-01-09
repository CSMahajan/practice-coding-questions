package LinkedList;

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

    public ListNode mergeKLists(ListNode[] lists) {
        return null;
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
        for(ListNode node: lists) {
            mksll.displayLinkedList(node);
            System.out.println();
        }
        ListNode mergedKListHead = mksll.mergeKLists(lists);
        System.out.print("Merged linked list: ");
        mksll.displayLinkedList(mergedKListHead);
    }
}

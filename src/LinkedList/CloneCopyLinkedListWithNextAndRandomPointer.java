package LinkedList;

/*
Clone a linked list with next and random pointer

You are given a special linked list with N nodes where each node has a next pointer pointing to its next node.
You are also given M random pointers, where you will be given M number of pairs denoting two nodes a and b
i.e. a->arb = b (arb is pointer to random node).
Construct a copy of the given list. The copy should consist of exactly N new nodes,
where each new node has its value set to the value of its corresponding original node.
Both the next and random pointer of the new nodes should point to new nodes in the copied list such that
the pointers in the original list and copied list represent the same list state.
None of the pointers in the new list should point to nodes in the original list.
For example, if there are two nodes X and Y in the original list, where X.arb --> Y,
then for the corresponding two nodes x and y in the copied list, x.arb --> y.
Return the head of the copied linked list.
Note :- The diagram isn't part of any example, it just depicts an example of how the linked list may look like.
*/
public class CloneCopyLinkedListWithNextAndRandomPointer {

    static class ListNode {
        int data;
        ListNode next;
        ListNode random;

        public ListNode(int data) {
            this.data = data;
        }

    }

    //Time Complexity: O(N)+O(N)+O(N)
    //Reason: Each step takes O(N) of time complexity.
    //Space Complexity: O(1)
    //Reason: No extra data structure was used for computation.
    public ListNode copyRandomList(ListNode head) {
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
        CloneCopyLinkedListWithNextAndRandomPointer ccllnrp = new CloneCopyLinkedListWithNextAndRandomPointer();
        ListNode head1 = new ListNode(7);
        ListNode head2 = new ListNode(13);
        ListNode head3 = new ListNode(11);
        ListNode head4 = new ListNode(10);
        ListNode head5 = new ListNode(1);
        head1.random = null;
        head2.random = head1;
        head3.random = head5;
        head4.random = head3;
        head5.random = head2;
        System.out.println("Before cloning a linked list");
        ccllnrp.displayLinkedList(head1);
        ListNode flattenedHead = ccllnrp.copyRandomList(head1);
        System.out.println();
        System.out.println("After cloning a linked list");
        ccllnrp.displayLinkedList(flattenedHead);
    }
}
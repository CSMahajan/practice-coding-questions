package LinkedList;

/*
Intersection of Two Linked Lists

Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
If the two linked lists have no intersection at all, return null.
For example, the following two linked lists begin to intersect at node c1:
The test cases are generated such that there are no cycles anywhere in the entire linked structure.
Note that the linked lists must retain their original structure after the function returns.
Example 1:
Input:
LinkedList1: 9->6->4->2->3->8
LinkedList2: 1->2->8->6
Output: 6 2 8
Example 1:
Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
- Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are different node references. In other words, they point to two different locations in memory, while the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.
Example 2:
Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Intersected at '2'
Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
Example 3:
Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: No intersection
Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.
*/
public class IntersectionOfTwoLinkedLists {

    static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }

        public ListNode() {
        }
    }

    //Time Complexity: O(2*max(length of list1,length of list2))
    //Reason: Uses the same concept of the difference of lengths of two lists.
    //Space Complexity: O(1)
    //Reason: No extra data structure is used
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode first = headA;
        ListNode second = headB;
        while (first != second) {
            first = (first == null) ? headB : first.next;
            second = (second == null) ? headA : second.next;
        }
        return first;
    }

    public void displayLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        IntersectionOfTwoLinkedLists itll = new IntersectionOfTwoLinkedLists();
        ListNode head1 = new ListNode(4);
        head1.next = new ListNode(1);
        head1.next.next = new ListNode(8);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        System.out.print("First linked list: ");
        itll.displayLinkedList(head1);
        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(1);
        head2.next.next.next = head1.next.next;
        head2.next.next.next.next = head1.next.next.next;
        head2.next.next.next.next.next = head1.next.next.next.next;
        System.out.println();
        System.out.print("Second linked list: ");
        itll.displayLinkedList(head2);
        ListNode intersectionHead = itll.getIntersectionNode(head1, head2);
        System.out.println();
        System.out.print("Intersection point onwards linked list: ");
        itll.displayLinkedList(intersectionHead);
    }
}
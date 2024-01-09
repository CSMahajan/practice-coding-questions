package LinkedList;

/*
Flattening a Linked List

Given a Linked List of size N, where every node represents a sub-linked-list and contains two pointers:
(i) a next pointer to the next node,
(ii) a bottom pointer to a linked list where this node is head.
Each of the sub-linked-list is in sorted order.
Flatten the Link List such that all the nodes appear in a single level while maintaining the sorted order.
Note: The flattened list will be printed using the bottom pointer instead of next pointer.
Example 1:
Input:
5 -> 10 -> 19 -> 28
|     |     |     |
7     20    22   35
|           |     |
8          50    40
|                 |
30               45
Output:  5-> 7-> 8- > 10 -> 19-> 20-> 22-> 28-> 30-> 35-> 40-> 45-> 50.
Explanation:
The resultant linked lists has every
node in a single level.
(Note: | represents the bottom pointer.)
Example 2:
Input:
5 -> 10 -> 19 -> 28
|          |
7          22
|          |
8          50
|
30
Output: 5->7->8->10->19->22->28->30->50
Explanation:
The resultant linked lists has every
node in a single level.
(Note: | represents the bottom pointer.)
*/
public class FlatteningLinkedList {

    static class ListNode {
        int data;
        ListNode next;
        ListNode bottom;

        public ListNode(int data) {
            this.data = data;
        }

    }

    //Time Complexity: O(N), where N is the total number of nodes present
    //Reason: We are visiting all the nodes present in the given list.
    //Space Complexity: O(1)
    //Reason: We are not creating new nodes or using any other data structure.
    public ListNode flatten(ListNode root) {
        if (root == null || root.next == null) {
            return root;
        }
        ListNode mergeHead = flatten(root.next);
        return mergeTwoLinkedLists(root, mergeHead);
    }

    private ListNode mergeTwoLinkedLists(ListNode first, ListNode second) {
        ListNode temp = new ListNode(0);
        ListNode mergedNode = temp;
        while (first != null && second != null) {
            if (first.data < second.data) {
                temp.bottom = first;
                first = first.bottom;
                temp = temp.bottom;
            } else {
                temp.bottom = second;
                second = second.bottom;
                temp = temp.bottom;
            }
        }
        if (first != null) {
            temp.bottom = first;
        } else {
            temp.bottom = second;
        }
        return mergedNode.bottom;
    }

    public void displayLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.bottom;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        FlatteningLinkedList fll = new FlatteningLinkedList();
        ListNode head1 = new ListNode(5);
        head1.bottom = new ListNode(7);
        head1.bottom.bottom = new ListNode(8);
        head1.bottom.bottom.bottom = new ListNode(30);
        ListNode head2 = new ListNode(10);
        head2.bottom = new ListNode(20);
        ListNode head3 = new ListNode(19);
        head3.bottom = new ListNode(22);
        head3.bottom.bottom = new ListNode(50);
        ListNode head4 = new ListNode(28);
        head4.bottom = new ListNode(32);
        head4.bottom.bottom = new ListNode(40);
        head4.bottom.bottom.bottom = new ListNode(45);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        System.out.println("Before flattening a linked list");
        fll.displayLinkedList(head1);
        ListNode flattenedHead = fll.flatten(head1);
        System.out.println();
        System.out.println("After flattening a linked list");
        fll.displayLinkedList(flattenedHead);
    }
}
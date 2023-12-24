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

    //In below recursive approach,we are also reversing last group which of size less than k
    public static ListNode reverseInKSizeGroup(ListNode node, int k) {
        //Your code here
        ListNode previous = null, nextNode = null;
        ListNode current = node;
        int count = 0;
        while (current != null && count < k) {
            nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
            count++;
        }
        if (nextNode != null) {
            node.next = reverseInKSizeGroup(nextNode, k);
        }
        return previous;
    }

    //Time Complexity: O(N)
    //Reason: Nested iteration with O((N/k)*k) which makes it equal to O(N).
    //Space Complexity: O(1)
    //Reason: No extra data structures are used for computation.
    public ListNode reverseKSizeNodes(ListNode head, int k) {
        ListNode temp = head;
        //previousLastNode denotes the last node of the previous k size group
        ListNode previousLastNode = null;
        while (temp != null) {
            //Step 1: Get k th node of every section of linked list
            ListNode kthNode = getKthNode(temp, k);
            if (kthNode == null) {
                //if current section is of size less than k
                if (previousLastNode != null) {
                    //updating the previous sections last nodes next to be temp because we had cut that link below
                    previousLastNode.next = temp;
                }
                break;
            }
            //Step 2: preserving nextNode after kth node
            ListNode nextNode = kthNode.next;
            //Step 3: cutting the link for every k nodes to reverse them
            kthNode.next = null;
            //Step 4: reversing the given linked list in place
            reverseKSizeLinkedList(temp);
            //Step 5: establish the broken link between two groups
            //as for every section kthNode is going to be the head, so establishing the broken link between these two k groups
            if (temp == head) {
                //as for every section k-th Node is going to be the head,
                //this is going to occur for first k size group because we need to return the head of new linked list
                head = kthNode;
            } else {
                //establishing link for previous k groups last node to kth node(which is current k groups head)
                previousLastNode.next = kthNode;
            }
            //Step 6: Updating previous last node
            previousLastNode = temp;
            //Step 7: Moving to next k-th group start point
            temp = nextNode;
        }
        return head;
    }

    private ListNode getKthNode(ListNode temp, int k) {
        //reducing k by 1 as we will stop at temp=null(possible k-th node)
        k--;
        while(temp != null && k > 0) {
            k--;
            temp = temp.next;
        }
        return temp;
    }

    private ListNode reverseKSizeLinkedList(ListNode temp){
        ListNode previous = null;
        while(temp != null) {
            ListNode nextNode = temp.next;
            temp.next = previous;
            previous = temp;
            temp = nextNode;
        }
        return previous;
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
        int k = 3;
        System.out.println("Before reversing a linked list in groups of size " + k);
        rllksg.displayLinkedList(head);
        ListNode reverseHead = rllksg.reverseKSizeNodes(head, k);
        System.out.println();
        System.out.println("After reversing a linked list in groups of size " + k);
        rllksg.displayLinkedList(reverseHead);
    }
}
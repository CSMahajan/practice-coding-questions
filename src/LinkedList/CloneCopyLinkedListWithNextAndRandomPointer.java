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
            next = null;
            random = null;
        }

    }

    //Time Complexity: O(N)+O(N)+O(N)
    //Reason: Each step takes O(N) of time complexity.
    //Space Complexity: O(1)
    //Reason: No extra data structure was used for computation.
    public ListNode copyRandomList(ListNode head) {
        ListNode temp = head;
        //Step 1: Create a copy nodes linked list
        //where new nodes are inserted in between original linked list with pointing of next pointers
        while (temp != null) {
            ListNode newNode = new ListNode(temp.data);
            newNode.next = temp.next;
            temp.next = newNode;
            temp = temp.next.next;
        }
        //Step 2: Create random pointer pointing in deep copy similar to original linked list random pointer pointing
        ListNode iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }
        //Step 3: Make original linked list and deep copy linked list pointing correct by removing pointing between original and deep copy
        ListNode dummy = new ListNode(0);
        //here iter will move on to original list
        //temp will move on to deep copy linked list
        //fast will always be next node of original linked list
        //(in order to establish a link between current to next nodes in original linked list) and
        //(current to next nodes in copy linked list)
        iter = head;
        temp = dummy;
        ListNode fast;
        while (iter != null) {
            fast = iter.next.next;
            temp.next = iter.next;
            iter.next = fast;
            temp = temp.next;
            iter = fast;
        }
        return dummy.next;
    }

    public void displayLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.data + ":");
            if (head.next != null) {
                System.out.print(head.next.data);
            } else {
                System.out.print("NULL");
            }
            if (head.random != null) {
                System.out.print("," + head.random.data);
            } else {
                System.out.print(",NULL");
            }
            System.out.println();
            head = head.next;
        }
    }

    public static void main(String[] args) {
        CloneCopyLinkedListWithNextAndRandomPointer ccllnrp = new CloneCopyLinkedListWithNextAndRandomPointer();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode head;
        head = node1;
        head.next = node2;
        head.next.next = node3;
        head.next.next.next = node4;
        head.random = node4;
        head.next.random = node1;
        head.next.next.random = null;
        head.next.next.next.random = node2;
        System.out.println("Before cloning a linked list");
        System.out.println("Original list:(current node:node pointed by next pointer, " +
                "node pointed by random pointer)");
        ccllnrp.displayLinkedList(head);
        ListNode cloneCopyHead = ccllnrp.copyRandomList(head);
        System.out.println();
        System.out.println("After cloning a linked list");
        System.out.println("Copy list:(current node:node pointed by next pointer,node " +
                "pointed by random pointer)");
        ccllnrp.displayLinkedList(cloneCopyHead);
    }
}
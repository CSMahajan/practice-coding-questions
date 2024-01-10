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
        //Step 1: insert copy nodes in between original list
        insertCopyInBetweenOriginalLinkedList(head);
        //Step 2: connect random pointers
        connectRandomPointersInDeepCopyList(head);
        //Step 3: get deep copy linked list
        return getDeepCopyLinkedList(head);
    }

    private void insertCopyInBetweenOriginalLinkedList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            ListNode copyNode = new ListNode(temp.data);
            ListNode nextNode = temp.next;
            temp.next = copyNode;
            copyNode.next = nextNode;
            temp = nextNode;
        }
    }

    private void connectRandomPointersInDeepCopyList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            ListNode copyNode = temp.next;
            if (temp.random != null) {
                copyNode.random = temp.random.next;
            } else {
                copyNode.random = null;
            }
            temp = temp.next.next;
        }
    }

    private ListNode getDeepCopyLinkedList(ListNode head) {
        ListNode temp = head;
        ListNode dummyNode = new ListNode(-1);
        ListNode result = dummyNode;
        while (temp != null) {
            result.next = temp.next;
            result = result.next;
            //disconnecting the newly created links and going back to original next links
            temp.next = temp.next.next;
            temp = temp.next;
        }
        return dummyNode.next;
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
package LinkedList;

/*
Remove duplicates from a sorted doubly linked list

Given a doubly linked list of n nodes sorted by values,
the task is to remove duplicate nodes present in the linked list.
Example 1:
Input:
n = 6
1<->1<->1<->2<->3<->4
Output:
1<->2<->3<->4
Explanation:
Only the first occurrence of node with value 1 is retained, rest nodes with value = 1 are deleted.
Example 2:
Input:
n = 7
1<->2<->2<->3<->3<->4<->4
Output:
1<->2<->3<->4
Explanation:
Only the first occurrence of nodes with values 2,3 and 4 are retained, rest repeating nodes are deleted.
*/
public class RemoveDuplicatesFromSortedDoublyLinkedList {

    static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
        }
    }

    //TC:O(N)
    //Reason: Even though there is a while loop inside a while loop,
    //inner loop traverses only through duplicates(nextNode iteration)
    //outer loop traverses only through unique elements(temp iteration)
    //so combined they traverse through entire linked once
    //SC:O(1)
    //Reason: Not using any extra space
    public Node removeDuplicates(Node head) {
        // Code Here.
        Node temp = head;
        while (temp != null && temp.next != null) {
            Node nextNode = temp.next;
            while (nextNode != null && temp.data == nextNode.data) {
                //traversing through duplicate elements till nextNode and temp have different data
                nextNode = nextNode.next;
            }
            //now nextNode is reached at a place whose data is greater than temp data
            //so need to make the link between temp and nextNode
            temp.next = nextNode;
            //need to have below if condition before marking for previous link of nextNode to avoid null pointer exception
            if (nextNode != null) {
                nextNode.prev = temp;
            }
            temp = temp.next;
        }
        return head;
    }

    public void displayLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedDoublyLinkedList rdfsdll = new RemoveDuplicatesFromSortedDoublyLinkedList();
        Node head = new Node(1);
        head.next = new Node(1);
        head.next.prev = head;
        head.next.next = new Node(2);
        head.next.next.prev = head.next;
        head.next.next.next = new Node(2);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.prev = head.next.next.next;
        System.out.println("Before removing duplicates from given linked list");
        rdfsdll.displayLinkedList(head);
        Node distinctElementsHead = rdfsdll.removeDuplicates(head);
        System.out.println();
        System.out.println("After removing duplicates from given linked list");
        rdfsdll.displayLinkedList(distinctElementsHead);
    }
}
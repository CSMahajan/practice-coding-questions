package LinkedList;

/*
Delete all occurrences of a given key in a doubly linked list

You are given the head of a doubly Linked List and a Key.
Your task is to delete all occurrences of the given key and return the new DLL.
Example:
Input:
2<->2<->10<->8<->4<->2<->5<->2
2
Output:
10<->8<->4<->5
Explanation:
All Occurrences of 2 have been deleted.
*/
public class DeleteAllOccurrencesOfGivenKeyInDoublyLinkedList {

    static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
        }
    }

    //Time Complexity: O(N)
    //Space Complexity: O(1)
    public Node deleteAllOccurrencesOfKey(Node head, int key) {
        // Write your code here
        Node temp = head;
        while (temp != null) {
            if (temp.data == key) {
                if (temp == head) {
                    head = head.next;
                }
                Node nextNode = temp.next;
                Node previousNode = temp.prev;
                //to avoid null pointer exceptions, add below if condition before modifying link
                if (nextNode != null) {
                    nextNode.prev = previousNode;
                }
                //to avoid null pointer exceptions, add below if condition before modifying link
                if (previousNode != null) {
                    previousNode.next = nextNode;
                }
                temp = nextNode;
            } else {
                temp = temp.next;
            }
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
        DeleteAllOccurrencesOfGivenKeyInDoublyLinkedList daoll = new DeleteAllOccurrencesOfGivenKeyInDoublyLinkedList();
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.prev = head;
        head.next.next = new Node(3);
        head.next.next.prev = head.next;
        head.next.next.next = new Node(4);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.prev = head.next.next.next;
        int key = 4;
        System.out.println("Before removing " + key + " from given linked list");
        daoll.displayLinkedList(head);
        Node reverseHead = daoll.deleteAllOccurrencesOfKey(head, key);
        System.out.println();
        System.out.println("After removing " + key + " from given linked list");
        daoll.displayLinkedList(reverseHead);
    }
}
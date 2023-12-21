package LinkedList;

public class ReverseDoublyLinkedList {

    static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        public Node(int data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    public Node reverseDoubleLinkedList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node temp = head;
        Node previous = null;
        while (temp != null) {
            previous = temp.prev;
            temp.prev = temp.next;
            temp.next = previous;
            temp = temp.prev;
        }
        return previous.prev;
    }

    public void displayLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        ReverseDoublyLinkedList dll = new ReverseDoublyLinkedList();
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.prev = head;
        head.next.next = new Node(3);
        head.next.next.prev = head.next;
        head.next.next.next = new Node(4);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.prev = head.next.next.next;
        System.out.println("Linked List before reversing");
        dll.displayLinkedList(head);
        Node reverseHead = dll.reverseDoubleLinkedList(head);
        System.out.println();
        System.out.println("Linked List after reversing");
        dll.displayLinkedList(reverseHead);
    }
}
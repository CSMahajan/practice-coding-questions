package LinkedList;

public class DoublyLinkedList {

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

    public Node convertArrayToDLL(int[] arr){
        int n = arr.length;
        if(n == 0){
            return null;
        }
        Node head = new Node(arr[0]);
        Node previous = head;
        for(int i = 1; i < n; i++){
            Node temp = new Node(arr[i], null, previous);
            previous.next = temp;
            previous = previous.next;
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
        DoublyLinkedList dll = new DoublyLinkedList();
        int[] arr = {1,2,3,4,5};
        Node head = dll.convertArrayToDLL(arr);
        dll.displayLinkedList(head);
    }
}
package LinkedList;

/*
Sort linked list of 0s 1s 2s

Given a linked list of N nodes where nodes can contain values 0s, 1s, and 2s only.
The task is to segregate 0s, 1s, and 2s linked list such that all zeros segregate to head side,
2s at the end of the linked list, and 1s in the mid of 0s and 2s.
Example 1:
Input:
N = 8
value[] = {1,2,2,1,2,0,2,2}
Output: 0 1 1 2 2 2 2 2
Explanation: All the 0s are segregated to the left end of the linked list,
2s to the right end of the list, and 1s in between.
Example 2:
Input:
N = 4
value[] = {2,2,0,1}
Output: 0 1 2 2
Explanation: After arranging all the 0s,1s and 2s in the given format, the output will be 0 1 2 2.
*/
public class Sort012LinkedList {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    //Function to sort a linked list of 0s, 1s and 2s.
    public Node sort012LinkedList(Node head) {
        // add your code here
        if (head == null || head.next == null) {
            return head;
        }
        Node zeroHead = new Node(-1);
        Node oneHead = new Node(-1);
        Node twoHead = new Node(-1);
        Node zero = zeroHead;
        Node one = oneHead;
        Node two = twoHead;
        Node temp = head;
        while (temp != null) {
            if (temp.data == 0) {
                zero.next = temp;
                zero = temp;
            } else if (temp.data == 1) {
                one.next = temp;
                one = temp;
            } else {
                two.next = temp;
                two = temp;
            }
            temp = temp.next;
        }
        zero.next = oneHead.next;
        one.next = twoHead.next;
        two.next = null;
        return zeroHead.next;
    }

    public void displayLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        Sort012LinkedList sll = new Sort012LinkedList();
        Node head = new Node(2);
        head.next = new Node(1);
        head.next.next = new Node(0);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(2);
        head.next.next.next.next.next = new Node(1);
        head.next.next.next.next.next.next = new Node(0);
        System.out.println("Linked List before 0 1 2 sorting");
        sll.displayLinkedList(head);
        Node sort012Head = sll.sort012LinkedList(head);
        System.out.println();
        System.out.println("Linked List after 0 1 2 sorting");
        sll.displayLinkedList(sort012Head);
    }
}
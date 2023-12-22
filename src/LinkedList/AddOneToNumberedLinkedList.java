package LinkedList;

/*
You're given a positive integer represented in the form of a singly linked-list of
digits. The length of the number is ‘n’

Add 1 to the number, ie., increment the given number by one.

The digits are stored such that the most significant digit is at the head of the
linked list and the least significant digit is at the tail of the linked list.

Example 1:
Input: Initial Linked List: 1 -> 5 -> 2
Output: Modified Linked List: 1 -> 5 -> 3
Explanation: After adding 1 to 152, sum becomes 153, so the modified linked list is 1->5>3
Example 2:
Input: Initial Linked List: 9 -> 9
Output: Modified Linked List: 1 -> 0 -> 0
Explanation: After adding 1 to 99, sum becomes 100, so the modified linked list is 1->0->0
*/
public class AddOneToNumberedLinkedList {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    //TC:O(N)
    //SC:O(N)
    public static Node addNode(Node head) {
        // Write your code here.
        //recursive function is used to calculating carry without reversing the linked list
        int carry = addOneHelper(head);
        //at last, carry remained as 1, so we need to create new node
        //e.g. (9->9) + 1 = (1->0->0)
        if (carry == 1) {
            Node newNode = new Node(carry);
            newNode.next = head;
            return newNode;
        }
        return head;
    }

    private static int addOneHelper(Node temp) {
        if (temp == null) {
            return 1;
        }
        //recursive call giving us carry for each node without even actually reversing the linked list
        int carry = addOneHelper(temp.next);
        //adding the carry to current node
        temp.data = temp.data + carry;
        if (temp.data < 10) {
            //even after adding carry, current node is from 1 to 9, it means carry can be returned as 0
            return 0;
        }
        //as now temp.data is 10, so assigning 0 to current node and returning carry as 1
        temp.data = 0;
        return 1;
    }

    public void displayLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        AddOneToNumberedLinkedList aonll = new AddOneToNumberedLinkedList();
        Node head = new Node(9);
        head.next = new Node(9);
        head.next.next = new Node(9);
        head.next.next.next = new Node(9);
        head.next.next.next.next = new Node(9);
        System.out.println("Before adding 1 to given linked list");
        aonll.displayLinkedList(head);
        Node reverseHead = aonll.addNode(head);
        System.out.println();
        System.out.println("After adding 1 to given linked list");
        aonll.displayLinkedList(reverseHead);
    }
}

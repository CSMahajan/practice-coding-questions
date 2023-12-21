package LinkedList;

/*
Odd Even Linked List

Given the head of a singly linked list,
group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
The first node is considered odd, and the second node is even, and so on.
Note that the relative order inside both the even and odd groups should remain as it was in the input.
You must solve the problem in O(1) extra space complexity and O(n) time complexity.
Example 1:
Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]
Example 2:
Input: head = [2,1,3,5,6,4,7]
Output: [2,3,6,7,1,5,4]
*/
public class OddEvenNodesLinkedList {

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

    //TC:O(N)
    //SC:O(1)
    public Node oddEvenList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node odd = head;
        Node even = head.next;
        //we will also require third variable evenHead
        //which is used for preserving the link from end of odd to even elements series in linked list
        Node evenHead = head.next;
        //below while condition will also work for either even or odd number of nodes,
        //because after every iteration even node is going to be ahead of odd node
        while (even != null && even.next != null) {
            //modify the links for next of odd and even indexes by 2 places
            odd.next = odd.next.next;
            even.next = even.next.next;
            //move the odd and even index nodes(basically by places which is done above)
            odd = odd.next;
            even = even.next;
        }
        //now odd has reached its possible end in linked list,
        //so to make further chain, pointing to even series preserved by evenHead
        odd.next = evenHead;
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
        OddEvenNodesLinkedList mll = new OddEvenNodesLinkedList();
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        System.out.println("Linked List before odd even segregating");
        mll.displayLinkedList(head);
        Node oddEvenNodeHead = mll.oddEvenList(head);
        System.out.println();
        System.out.println("Linked List after odd even segregating");
        mll.displayLinkedList(oddEvenNodeHead);
    }
}
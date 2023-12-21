package LinkedList;

/*
Segregate even and odd nodes in a Link List

Given a link list of size N,
modify the list such that all the even numbers appear before all the odd numbers in the modified list.
The order of appearance of numbers within each segregation should be same as that in the original list.
NOTE: Don't create a new linked list, instead rearrange the provided one.
Example 1:
Input:
N = 7
Link List:
17 -> 15 -> 8 -> 9 -> 2 -> 4 -> 6 -> NULL
Output: 8 2 4 6 17 15 9
Explanation: 8,2,4,6 are the even numbers, so they appear first and 17,15,9 are odd numbers that appear later.
Example 2:
Input:
N = 4
Link List:
1 -> 3 -> 5 -> 7
Output: 1 3 5 7
Explanation: There is no even number. So no need for modification.
*/
public class OddEvenNumberNodesLinkedList {

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
    public Node oddEvenNumberList(Node head) {
        //This problem is similar to sort 0 1 2 linked list
        if (head == null || head.next == null) {
            return head;
        }
        Node odd = new Node(-1);
        Node even = new Node(-1);
        Node e = even;
        Node o = odd;
        Node temp = head;
        while (temp != null) {
            if (temp.data % 2 == 0) {
                even.next = temp;
                even = temp;
            } else {
                odd.next = temp;
                odd = temp;
            }
            temp = temp.next;
        }
        even.next = o.next;
        odd.next = null;
        return e.next;
    }

    public void displayLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }


    public static void main(String[] args) {
        OddEvenNumberNodesLinkedList oennll = new OddEvenNumberNodesLinkedList();
        Node head = new Node(2);
        head.next = new Node(3);
        head.next.next = new Node(9);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(1);
        head.next.next.next.next.next = new Node(8);
        System.out.println("Linked List before odd even number segregating");
        oennll.displayLinkedList(head);
        Node oddEvenNodeHead = oennll.oddEvenNumberList(head);
        System.out.println();
        System.out.println("Linked List after odd even number segregating");
        oennll.displayLinkedList(oddEvenNodeHead);
    }
}
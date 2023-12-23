package LinkedList;

/*
Find length of Loop

Given a linked list of size N.
The task is to complete the function countNodesinLoop() that checks whether
a given Linked List contains a loop or not and
if the loop is present then return the count of nodes in a loop or else return 0.
C is the position of the node to which the last node is connected. If it is 0 then no loop.
Example 1:
Input:
N = 10
value[]={25,14,19,33,10,21,39,90,58,45}
C = 4
Output: 7
Explanation: The loop is 45->33. So length of loop is 33->10->21->39->90->58->45 = 7.
The number 33 is connected to the last node to form the loop because according to the input,
the 4th node from the beginning(1 based index) will be connected to the last node for the loop.
Example 2:
Input:
N = 2
value[] = {1,0}
C = 1
Output: 2
Explanation: The length of the loop is 2.
*/
public class LengthOfLoopLinkedList {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public int countTotalNodesInLoop(Node head) {
        //Add your code here.
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return findLengthOfLoop(slow, fast);
            }
        }
        return 0;
    }

    public void displayLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    private int findLengthOfLoop(Node slow, Node fast) {
        int count = 1;
        //need to start fast from next of collision point of slow and fast to calculate length of loop
        fast = fast.next;
        while (slow != fast) {
            fast = fast.next;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        LengthOfLoopLinkedList cldll = new LengthOfLoopLinkedList();
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = head.next.next;
        //cldll.displayLinkedList(head);
        System.out.println();
        System.out.println("Length of loop in given linked list : "+cldll.countTotalNodesInLoop(head));
    }
}
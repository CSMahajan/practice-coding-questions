package LinkedList;

/*
Add Two Numbers

You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order, and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.
Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:
Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
*/

public class AddTwoNumbersInLinkedList {

    static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }

        public ListNode() {
        }
    }

    //Time Complexity: O(max(m,n)).
    //Assume that m and n represent the length of l1 and l2 respectively, the algorithm above iterates at most max(m,n) times.
    //Space Complexity: O(max(m,n)). The length of the new list is at most max(m,n)+1.
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode temp = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.data;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.data;
                l2 = l2.next;
            }
            sum += carry;
            carry = sum / 10;
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
        }
        if(carry > 0) {
            ListNode newNode = new ListNode(carry);
            temp.next = newNode;
        }
        return dummy.next;
    }

    public void displayLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        AddTwoNumbersInLinkedList atnll = new AddTwoNumbersInLinkedList();
        ListNode head1 = new ListNode(2);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(9);
        System.out.print("First linked list: ");
        atnll.displayLinkedList(head1);
        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(4);
        System.out.println();
        System.out.print("Second linked list: ");
        atnll.displayLinkedList(head2);
        ListNode sumHead = atnll.addTwoNumbers(head1, head2);
        System.out.println();
        System.out.print("Sum linked list: ");
        atnll.displayLinkedList(sumHead);
    }
}
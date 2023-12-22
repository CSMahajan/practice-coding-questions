package LinkedList;

/*
Check if Linked List is Palindrome

Given a singly linked list of size N of integers. The task is to check if the given linked list is palindrome or not.
Example 1:
Input:
N = 3
value[] = {1,2,1}
Output: 1
Explanation: The given linked list is 1 2 1 , which is a palindrome and Hence, the output is 1.
Example 2:
Input:
N = 4
value[] = {1,2,3,4}
Output: 0
Explanation: The given linked list is 1 2 3 4 , which is not a palindrome and Hence, the output is 0.
*/
public class CheckPalindromeLinkedList {

    static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }
    }

    //Time Complexity: O(N/2)+O(N/2)+O(N/2)
    //Reason: O(N/2) for finding the middle element,
    //reversing the list from the middle element, and traversing again to find palindrome respectively.
    //Space Complexity: O(1)
    //Reason: No extra data structures are used.
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        //Step 1: Find middle node
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //Step 2. Reverse second half from next node of middle
        slow.next = reverseLinkedList(slow.next);
        //slow will move next location from which we will compare two halves of linked lists
        slow = slow.next;
        ListNode dummy = head;
        //Step 3: Compare first half before middle(pointed by dummy) and second half after middle(which is reversed and now pointed by slow)
        while (slow != null) {
            if (slow.data != dummy.data) {
                return false;
            }
            slow = slow.next;
            dummy = dummy.next;
        }
        return true;
    }

    private ListNode reverseLinkedList(ListNode head) {
        ListNode previous = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = previous;
            previous = head;
            head = next;
        }
        return previous;
    }

    //TC:O(2N)
    //SC:O(1)
    public boolean checkPalindromeLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        //Step 1: Reach to middle of linked list using slow and fast pointer approach
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode reverseHead = reverseSecondHalfLinkedList(slow.next);
        //here first and second points to first half and second half(reversed) linked list respectively
        ListNode first = head;
        ListNode second = reverseHead;
        //Step 2: Compare first half and second half in below while loop condition
        //check for only second half head because it might have one node less than first half
        //if total number of nodes are odd number
        while(second!=null){
            if(first.data != second.data){
                //need to make original linked list by reversing it again to avoid modifying existing linked list
                reverseSecondHalfLinkedList(reverseHead);
                return false;
            }
            first = first.next;
            second = second.next;
        }
        //need to make original linked list by reversing it again to avoid modifying existing linked list
        reverseSecondHalfLinkedList(reverseHead);
        return true;
    }

    private ListNode reverseSecondHalfLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode previousNode = null;
        ListNode temp = head;
        while(temp != null){
            ListNode nextNode = temp.next;
            temp.next = previousNode;
            previousNode = temp;
            temp = nextNode;
        }
        return previousNode;
    }

    public static void main(String[] args) {
        CheckPalindromeLinkedList cpll = new CheckPalindromeLinkedList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        System.out.println(cpll.checkPalindromeLinkedList(head));
    }
}
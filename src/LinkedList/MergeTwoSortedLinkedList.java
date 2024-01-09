package LinkedList;

public class MergeTwoSortedLinkedList {

    static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;
        while(list1 != null && list2 != null){
            if(list1.data < list2.data){
                temp.next = list1;
                temp = temp.next;
                list1 = list1.next;
            } else {
                temp.next = list2;
                temp = temp.next;
                list2 = list2.next;
            }
        }
        if(list1 != null){
            temp.next = list1;
        } else {
            temp.next = list2;
        }
        return dummyNode.next;
    }

    public void displayLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        MergeTwoSortedLinkedList mtsll = new MergeTwoSortedLinkedList();
        ListNode head1 = new ListNode(2);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(9);
        System.out.print("First linked list: ");
        mtsll.displayLinkedList(head1);
        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(7);
        System.out.println();
        System.out.print("Second linked list: ");
        mtsll.displayLinkedList(head2);
        ListNode sumHead = mtsll.mergeTwoLists(head1, head2);
        System.out.println();
        System.out.print("Merged linked list: ");
        mtsll.displayLinkedList(sumHead);
    }
}
package Stack;

/*
Implement stack using linked list
*/
public class Stack3 {

    private static class StackNode {
        int data;
        StackNode next;

        StackNode(int d) {
            data = d;
            next = null;
        }
    }

    StackNode top;
    int size;

    Stack3() {
        this.top = null;
        this.size = 0;
    }

    public void stackPush(int x) {
        StackNode element = new StackNode(x);
        element.next = top;
        top = element;
        System.out.println("Element pushed");
        size++;
    }

    public int stackPop() {
        if (top == null) return -1;
        int topData = top.data;
        top = top.next;
        return topData;
    }

    public int stackSize() {
        return size;
    }

    public boolean isStackEmpty() {
        return top == null;
    }

    public void printStack() {
        StackNode current = top;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Stack3 s = new Stack3();
        s.stackPush(10);
        s.stackPush(20);
        s.printStack();
        System.out.println("Element popped " + s.stackPop());
        System.out.println("Stack size: " + s.stackSize());
        System.out.println("Stack is empty or not: " + s.isStackEmpty());
    }
}
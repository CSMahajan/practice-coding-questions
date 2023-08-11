package Stack;

/*
    Implement Stack using Array
*/
public class Stack1 {

    private final int size;
    int[] arr;
    int top = -1;

    public Stack1(int capacity) {
        // Write your code here.
        size = capacity;
        arr = new int[size];
    }

    public void push(int x) {
        top++;
        arr[top] = x;
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        int x = arr[top];
        top--;
        return x;
    }

    public int top() {
        return isEmpty() ? -1 : arr[top];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        // Write your code here.
        return top == -1;
    }

    public boolean isFull() {
        // Write your code here.
        return size == top;
    }

    public static void main(String[] args) {
        Stack1 s = new Stack1(10);
        s.push(6);
        s.push(3);
        s.push(7);
        System.out.println("Top of the stack before deleting any element " + s.top());
        System.out.println("Size of the stack before deleting any element " + s.size());
        System.out.println("The element deleted is " + s.pop());
        System.out.println("Size of the stack after deleting an element " + s.size());
        System.out.println("Top of the stack after deleting an element " + s.top());
        System.out.println("Is stack full: " + s.isFull());
        System.out.println("Is stack empty: " + s.isEmpty());
    }
}
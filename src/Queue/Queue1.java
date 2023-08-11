package Queue;

/*
Implement Queue using Array
*/

public class Queue1 {

    private final int[] arr;
    private int start;
    private int end;
    private int currentSize;
    private final int maxSize;

    public Queue1(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        start = -1;
        end = -1;
        currentSize = 0;
    }

    public void push(int newElement) {
        if (currentSize == maxSize) {
            System.out.println("Queue is full\nExiting...");
            System.exit(1);
        }
        if (end == -1) {
            start = 0;
            end = 0;
        } else
            end = (end + 1) % maxSize;
        arr[end] = newElement;
        System.out.println("The element pushed is " + newElement);
        currentSize++;
    }

    public int pop() {
        if (start == -1) {
            System.out.println("Queue Empty\nExiting...");
            System.exit(1);
        }
        int popped = arr[start];
        if (currentSize == 1) {
            start = -1;
            end = -1;
        } else
            start = (start + 1) % maxSize;
        currentSize--;
        return popped;
    }

    public int top() {
        if (start == -1) {
            System.out.println("Queue is Empty");
            System.exit(1);
        }
        return arr[start];
    }

    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        return start == -1;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public static void main(String[] args) {
        Queue1 q = new Queue1(6);
        q.push(4);
        q.push(14);
        q.push(24);
        q.push(34);
        System.out.println("The peek of the queue before deleting any element " + q.top());
        System.out.println("The size of the queue before deletion " + q.size());
        System.out.println("The first element to be deleted " + q.pop());
        System.out.println("The peek of the queue after deleting an element " + q.top());
        System.out.println("The size of the queue after deleting an element " + q.size());
        System.out.println("Is queue full: " + q.isFull());
        System.out.println("Is queue empty: " + q.isEmpty());
    }
}
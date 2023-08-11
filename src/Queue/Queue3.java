package Queue;

/*
Implement Queue using Linked List
*/
public class Queue3 {

    static class QueueNode {
        int val;
        QueueNode next;

        QueueNode(int data) {
            val = data;
            next = null;
        }
    }

    QueueNode front = null, rear = null;
    int size = 0;

    public boolean isEmpty() {
        return front == null;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
            return -1;
        } else
            return front.val;
    }

    public void enqueue(int value) {
        QueueNode Temp;
        Temp = new QueueNode(value);
        if (front == null) {
            front = Temp;
            rear = Temp;
        } else {
            rear.next = Temp;
            rear = Temp;
        }
        System.out.println(value + " Inserted into Queue ");
        size++;
    }

    public void dequeue() {
        if (front == null)
            System.out.println("Queue is Empty");
        else {
            System.out.println(front.val + " Removed From Queue");
            front = front.next;
            size--;
        }
    }

    public static void main(String[] args) {
        Queue3 Q = new Queue3();
        Q.enqueue(10);
        Q.enqueue(20);
        Q.enqueue(30);
        Q.enqueue(40);
        Q.enqueue(50);
        Q.dequeue();
        System.out.println("The size of the Queue is " + Q.size);
        System.out.println("The Peek element of the Queue is " + Q.peek());
    }
}
/*
Reverse First K elements of Queue

Array = {1 2 3 4 5}
K = 3
Output = { 3 2 1 4 5}

*/

import java.util.*;

public class ReverseFirstKElementsQueue {
    public static Queue<Integer> reverseElements(Queue<Integer> q, int k) {
        // Write your code here.
        int i = 0;
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        while (i < k) {
            stack.push(q.poll());
            i++;
        }
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        while (!q.isEmpty()) {
            queue.add(q.poll());
        }
        return queue;
    }

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= 5; i++)
            q.add(i);
        System.out.println(reverseElements(q, 3));
    }
}

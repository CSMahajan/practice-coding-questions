package Stack;

import java.util.Stack;

/*
Get minimum element from stack

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
Implement the MinStack class:
MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.
Example 1:
Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]
Output
[null,null,null,null,-3,null,0,-2]
Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2
*/
public class MinStack {

    Stack<Long> stack = new Stack<>();
    Long minimum;

    public MinStack() {
        minimum = Long.MAX_VALUE;
    }

    public void push(int value) {
        Long val = (long) value;
        if (stack.isEmpty()) {
            minimum = val;
            stack.push(val);
        } else {
            if (val < minimum) {
                stack.push(2 * val - minimum);
                minimum = val;
            } else {
                stack.push(val);
            }
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        Long val = stack.pop();
        if (val < minimum) {
            minimum = 2 * minimum - val;
        }
    }

    public int top() {
        Long val = stack.peek();
        if (val < minimum) {
            return minimum.intValue();
        }
        return val.intValue();
    }

    //Time Complexity: O(1)
    //Space Complexity: O(N)
    public int getMin() {
        return minimum.intValue();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-1);
        minStack.push(0);
        minStack.push(-2);
        minStack.push(-4);
        minStack.push(2);
        minStack.pop();
        int top = minStack.top();
        minStack.pop();
        int minimumElement = minStack.getMin();
        System.out.println("top "+top);
        System.out.println("minimumElement "+minimumElement);
        minStack.pop();
        System.out.println("top "+minStack.top());
        System.out.println("minimumElement "+minimumElement);
    }
}
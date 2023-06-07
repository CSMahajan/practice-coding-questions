import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class ParenthesisChecker {

    /*
    Parenthesis Checker

    Given an expression string x. Examine whether the pairs and the orders of {,},(,),[,] are correct in exp.
    For example, the function should return 'true' for exp = [()]{}{[()()]()} and 'false' for exp = [(]).

    Note: The drive code prints "balanced" if function return true, otherwise it prints "not balanced".

    Example 1:

    Input:
    {([])}
    Output:
    true
    Explanation:
    { ( [ ] ) }. Same colored brackets can form balanced pairs, with 0 number of unbalanced bracket.*/

    //Function to check if brackets are balanced or not.
    public static boolean ispar(String x) {
        // add your code here
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < x.length(); i++) {
            char c = x.charAt(i);
            if (st.isEmpty())
                st.push(c);
            else if (c == ']' && st.peek() == '[' || c == '}' && st.peek() == '{' || c == ')' && st.peek() == '(') {
                st.pop();
            } else
                st.push(c);

        }
        if (st.isEmpty())
            return true;
        return false;
    }


    /*--------------Other solutions----------------------*/
    public static boolean ispar1(String x) {
        int i = -1;
        char[] stack = new char[x.length()];
        for (char ch : x.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[')
                stack[++i] = ch;
            else {
                if (i >= 0
                        && ((stack[i] == '(' && ch == ')')
                        || (stack[i] == '{' && ch == '}')
                        || (stack[i] == '[' && ch == ']')))
                    i--;
                else
                    return false;
            }
        }
        return i == -1;
    }

    public static boolean ispar2(String expr) {
    // Using ArrayDeque is faster than using Stack class
    Deque<Character> stack
            = new ArrayDeque<Character>();

    // Traversing the Expression
        for(
    int i = 0; i<expr.length();i++)

    {
        char x = expr.charAt(i);

        if (x == '(' || x == '[' || x == '{') {
            // Push the element in the stack
            stack.push(x);
            continue;
        }

        // If current character is not opening
        // bracket, then it must be closing. So stack
        // cannot be empty at this point.
        if (stack.isEmpty())
            return false;
        char check;
        switch (x) {
            case ')':
                check = stack.pop();
                if (check == '{' || check == '[')
                    return false;
                break;

            case '}':
                check = stack.pop();
                if (check == '(' || check == '[')
                    return false;
                break;

            case ']':
                check = stack.pop();
                if (check == '(' || check == '{')
                    return false;
                break;
        }
    }

    // Check Empty Stack
        return(stack.isEmpty());
}
}

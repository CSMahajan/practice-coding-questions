package Stack;

import java.util.Stack;

/*
Valid Parentheses

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
Example 1:
Input: s = "()"
Output: true
Example 2:
Input: s = "()[]{}"
Output: true
Example 3:
Input: s = "(]"
Output: false
*/
public class ValidParenthesesChecker {

    //Time Complexity: O(N)
    //Space Complexity: O(N)
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '[' || ch == '{' || ch == '(') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char stackChar = stack.pop();
                if ((ch == ')' && stackChar == '(') ||
                        (ch == '}' && stackChar == '{') ||
                        (ch == ']' && stackChar == '[')) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParenthesesChecker vpc = new ValidParenthesesChecker();
        String s = "[()]{}{[()()]()}";
        System.out.println(vpc.isValid(s));
    }
}
package com.alg;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 determine if the input string is valid.

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

 Example 4:

 Input: s = "([])"

 Output: true



 Constraints:

 1 <= s.length <= 104
 s consists of parentheses only '()[]{}'.
 */
public class Sol20_ValidParentheses {
    public static boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++){
            char curr = s.charAt(i);

            if (map.containsKey(curr)) {
                stack.push(curr);
            } else if (map.containsValue(curr)) { //or map.containsValue(curr)
                if (!stack.empty() && map.get(stack.peek()) == curr) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.empty();
    }

    public static void main(String[] args) {
        String s = "afdaf{a()}{[b{}]())}";
        String t = "{}(abc+)[]";
        System.out.println(isValid(t));
        System.out.println(isValid(s));
    }
    // method 2
    public boolean isValidP(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //碰到左括号，就把相应的右括号入栈
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
                // 第三种情况：遍历字符串匹配的过程中，栈已经为空了，没有匹配的字符了，说明右括号没有找到对应的左括号 return false
                // 第二种情况：遍历字符串匹配的过程中，发现栈里没有我们要匹配的字符。所以return false
            } else if (stack.isEmpty() || stack.pop() != c) {  //如果是右括号判断是否和栈顶元素匹配
                return false;
            }
        }
        // 第一种情况：此时我们已经遍历完了字符串，但是栈不为空，说明有相应的左括号没有右括号来匹配，所以return false，否则就return true

        return stack.isEmpty();
    }

    // brute force 1
    public boolean isValidPa(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c: s.toCharArray() ) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char temp = stack.pop();
                if (temp == '(' && c != ')') return false;
                if (temp == '[' && c != ']') return false;
                if (temp == '{' && c != '}') return false;
            }
        }
        return stack.isEmpty();
    }
}

package com.alg;

import java.util.Stack;

/*Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

*/
public class Sol32_LongestValidParentheses {
    public static int longestValidParentheses(String s){
        // using stack
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // used to calculate the length
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }else{
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String ss = ")(()(())";
        System.out.println(longestValidParentheses(ss));
    }
}

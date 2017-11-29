package com.alg;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by HAU on 6/10/2017.
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

            if (map.keySet().contains(curr)) {
                stack.push(curr);
            } else if (map.values().contains(curr)) {
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
    public static boolean isValidPt(String s){
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()){
            if (c == '('){
                stack.push(')');
            }else if(c == '['){
                stack.push(']');
            }else if(c =='{'){
                stack.push('}');
            }else if (stack.isEmpty() || stack.pop()!= c){
                return false;
            }
        }
        return stack.isEmpty();
    }
}

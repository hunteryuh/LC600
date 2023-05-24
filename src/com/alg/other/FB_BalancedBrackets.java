package com.alg.other;

import java.util.Stack;

/*
A bracket is considered to be any one of the following characters: (, ), {, }, [, or ].
We consider two brackets to be matching if the first element is an open-bracket, e.g., (, {, or [, and the second bracket is a close-bracket of the same type, e.g., ( and ), [ and ], and { and } are the only pairs of matching brackets.
Furthermore, a sequence of brackets is said to be balanced if the following conditions are met:
The sequence is empty, or
The sequence is composed of two, non-empty, sequences both of which are balanced, or
The first and last brackets of the sequence are matching, and the portion of the sequence without the first and last elements is balanced.
You are given a string of brackets. Your task is to determine whether each sequence of brackets is balanced. If a string is balanced, return true, otherwise, return false
Signature
bool isBalanced(String s)
Input
String s with length between 1 and 1000
Output
A boolean representing if the string is balanced or not
Example 1
s = {[()]}
output: true
Example 2
s = {}()
output: true
Example 3
s = {(})
output: false
Example 4
s = )
output: false

 */
public class FB_BalancedBrackets {
    public boolean isBalanced(String s) {
        if (s.isEmpty()) return true;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && isMatching(stack.peek(), c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();

    }

    private boolean isMatching(Character c1, Character c2) {
        if (c1 == '(' && c2 == ')') return true;
        if (c1 == '{' && c2 == '}') return true;
        if (c1 == '[' && c2 == ']') return true;
        return false;
    }
}

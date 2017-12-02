package com.alg;

import java.util.Stack;

/**
 * Created by HAU on 12/2/2017.
 */
/*Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.

'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
Example 1:
Input: "()"
Output: True
Example 2:
Input: "(*)"
Output: True
Example 3:
Input: "(*))"
Output: True*/
public class Sol678_ValidParenthesisString {
    public static boolean checkValidString(String s) {
        // * could change the balance by -1 , or 0, or 1
        int lo = 0;
        int hi = 0;
        for (char c : s.toCharArray()){
            lo += c == '('? 1 : -1;
            hi += c != ')'? 1 : -1;
            if (hi < 0) break;
            if ( lo < 0){
                lo = 0;
            }
        }
        return lo == 0;
    }

    public static void main(String[] args) {
        String s = "(**(";
        String a = "(**()";
        String t = ")*(";
        System.out.println(checkValidString(s));
        System.out.println(checkValid2(s));
        System.out.println(checkValidString(a));
        System.out.println(checkValid2(a));
        System.out.println(checkValidString(t));
        System.out.println(checkValid2(t));

    }
    public static boolean checkValid2(String s){
        Stack<Integer> left = new Stack<>();
        Stack<Integer> star = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if ( c == '('){
                left.push(i);
            }else if( c == '*'){
                star.push(i);
            }else{
                if(left.isEmpty() && star.isEmpty()) return false; // only 3 types, if so only ) char, false;
                if(!left.isEmpty()){
                    left.pop();  // when a ) comes, pop left bracket stack first if it is not empty.
                }else{  // star not empty
                    star.pop();
                }
            }
        }
        // do not care star stack
        while(!left.isEmpty() && !star.isEmpty()){
            if(left.pop() > star.pop()) return false;  // a ( comes after a *, false
        }
        return left.isEmpty();
    }
}

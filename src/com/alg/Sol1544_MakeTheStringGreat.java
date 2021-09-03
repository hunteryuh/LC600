package com.alg;

import java.util.Stack;

/*
Given a string s of lower and upper case English letters.

A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:

0 <= i <= s.length - 2
s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
To make the string good, you can choose two adjacent characters that make the string bad and remove them. You can keep doing this until the string becomes good.

Return the string after making it good. The answer is guaranteed to be unique under the given constraints.

Notice that an empty string is also good.



Example 1:

Input: s = "leEeetcode"
Output: "leetcode"
Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".
Example 2:

Input: s = "abBAcC"
Output: ""
Explanation: We have many possible scenarios, and all lead to the same answer. For example:
"abBAcC" --> "aAcC" --> "cC" --> ""
"abBAcC" --> "abBA" --> "aA" --> ""
Example 3:

Input: s = "s"
Output: "s"
 */
public class Sol1544_MakeTheStringGreat {
    public static String makeGood(String s) {
        boolean ch = true;
        while (ch) {
            ch = false;
            String t = s;
            for (int i = 0; i < s.length() - 1; i++) {
                if (Math.abs(s.charAt(i) - s.charAt(i+1)) == 32) {
                    t = s.substring(0, i) + s.substring(i+2);
                    ch = true;
                    break;
                }
            }
            s = t;
        }
        return s;
    }

    public static String makeGood2(String s) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(cur);
            } else {
                if (Math.abs(stack.peek() - cur) == 32) {
                    stack.pop();
                } else {
                    stack.push(cur);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        // stack.forEach(sb::append);
//        for (Character character : stack) {
//            sb.append(character);
//            System.out.println(character);  // this will give the bottom-up order...
//        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String s = "leEeetcode";
        String s2 = "abBAcC";
        String s3 = "s";
        System.out.println(makeGood(s));
        System.out.println(makeGood2(s));
        System.out.println(makeGood(s2));
        System.out.println(makeGood(s3));
    }
}

package com.alg;

import java.util.Stack;

/*Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

*/
public class Sol32_LongestValidParentheses {
    public static int longestValidParentheses(String s){
        // using stack, store the index of starting point of a valid string
        // 出现右括号的时候先pop出左括号，再计算长度
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // used to calculate the length
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
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

    // https://leetcode.com/problems/longest-valid-parentheses/editorial/
    // 2 pass space O(1)
    public int longestValidParen(String s) {
        int left = 0, right = 0;
        int res = 0;
        for (char c: s.toCharArray()) {
            if (c == '(') {
                left++;
            } else right++;
            if (left == right) {
                res = Math.max(res, left * 2);
            } else if (right > left) {
                left = right = 0;
            }
        }
        // only left scan will not find the longest (4)  for this example: () ( (())
        // as in the end the right ) is still less than the left (
        left = right  =0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '(') left++;
            else right++;
            if (left == right) {
                res = Math.max(res, left * 2);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return res;
    }

    // https://leetcode.com/problems/longest-valid-parentheses/solutions/14278/two-java-solutions-with-explanation-stack-dp-short-easy-to-understand/
    // dp
    public int longestValidParentheses_dp(String s) {
        int[] dp = new int[s.length()];
        int result = 0;
        int leftCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftCount++;
            } else if (leftCount > 0){
                dp[i] = dp[i - 1] + 2;
                /* i - dp[i]
                 it's the index in the dp array of the value right before the current parentheses sequence that is being counted
simple example: if you have ()(), when you check the last character in the string, your dp values will be [0][2][0][ ], and at first since you have a ), you'll add 2, but you want to add the value in index 1 (i - dp[i], or 3 - 2, gives you index 1) because that represents the count of an adjacent valid sequence that you want to add to your total count
index 1 contains 2, so you'll add 2 to your current 2 and get the correct answer of 4
                 */
                dp[i] += (i - dp[i]) >= 0 ? dp[i - dp[i]] : 0;
                result = Math.max(result, dp[i]);
                leftCount--;
            }
        }
        return result;
    }

    // dp 2
    public int longestValidParentheses3(String s) {
        int[] dp = new int[s.length()];
        int res = 0;
        int leftCount = 0, rightCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftCount++;
            } else if (rightCount < leftCount) {  // inside valid substring, update res
                rightCount++;
                dp[i] = dp[i - 1] + 2;
                dp[i] += (i - dp[i]) >= 0 ? dp[i - dp[i]] : 0;
                res = Math.max(res, dp[i]);
            } else {   // valid substring breaks
                leftCount = 0;
                rightCount = 0;
                dp[i] = 0;
            }
        }
        return res;
    }
}

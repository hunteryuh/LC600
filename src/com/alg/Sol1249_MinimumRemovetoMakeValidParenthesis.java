package com.alg;

import java.util.*;

/*
Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.


Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
Example 4:

Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"

medium

interviewed by facebook this one at 7/8/2021
 */
public class Sol1249_MinimumRemovetoMakeValidParenthesis {
    // https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/discuss/419702/Java-O(n)-solution-without-Stack
    // two pass
    public String minRemoveToMakeValid(String s) {
        boolean[] remove = new boolean[s.length()];
        int open = 0;
        for (int i = 0; i< s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                open++;
            } else if (c == ')') {
                if (open > 0) {
                    open--;
                } else {
                    remove[i] = true;
                }
            }
        }
        int close = 0;
        for (int i = s.length() - 1; i >=0; i--) {
            char c = s.charAt(i);
            if (c == ')') {
                close++;
            } else if (c == '(') {
                if (close > 0) {
                    close--;
                } else {
                    remove[i] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < remove.length; i++) {
            if (!remove[i]) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "(a(b(c)d)";
        Sol1249_MinimumRemovetoMakeValidParenthesis s = new Sol1249_MinimumRemovetoMakeValidParenthesis();
        System.out.println(s.minRemoveToMakeValid(input));
    }


    // https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/discuss/419443/Java-Clean-StringBuilder-solution
    public String minRemoveToMakeValid2(String s) {
        StringBuilder sb = new StringBuilder();
        int open = 0;
        for (char c : s.toCharArray()) {
            if (c == '(')
                open++;
            else if (c == ')') {
                if (open == 0) continue;
                open--;
            }
            sb.append(c);
        }
            // ))((     ((
        StringBuilder result = new StringBuilder();
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == '(' && open-- > 0) continue;
            result.append(sb.charAt(i));
        }

        return result.reverse().toString();
    }

    // with Stack
    // https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/discuss/419402/JavaC%2B%2B-Stack
    public String minRemoveToMakeValid3(String s) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < sb.length(); ++i) {
            if (sb.charAt(i) == '(') st.add(i);
            if (sb.charAt(i) == ')') {
                if (!st.empty()) st.pop();
                else sb.setCharAt(i, '*');
            }
        }
        while (!st.empty())
            sb.setCharAt(st.pop(), '*');
        return sb.toString().replaceAll("\\*", "");
    }

    // rolling state, use stack to store index
    // time O(n), n is the length of the input string
    public String minRemoveToMakeValid4(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> indicesToRemove = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') stack.push(i);
            else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indicesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        while (!stack.empty()) {
            indicesToRemove.add(stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indicesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    //Generate all distinct balanced parentheses strings
    //after removing the minimum number of parentheses
    //edit
    // input s = "()())"
    // output ["(())", "(())"]
    // use backtracking

    public class BalancedParentheses {
        public List<String> removeInvalidParentheses(String s) {
            Set<String> result = new HashSet<>();
            int leftToRemove = 0, rightToRemove = 0;

            // First, we find out the number of misplaced left and right parentheses
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    leftToRemove++;
                } else if (c == ')') {
                    if (leftToRemove == 0) {
                        rightToRemove++;
                    } else {
                        leftToRemove--;
                    }
                }
            }

            // Use backtracking to generate all possibilities
            backtrack(s, 0, 0, 0, leftToRemove, rightToRemove, new StringBuilder(), result);

            return new ArrayList<>(result);
        }

        private void backtrack(String s, int index, int leftCount, int rightCount, int leftToRemove, int rightToRemove, StringBuilder expression, Set<String> result) {
            // If we've reached the end of the string, add the current expression to the result if it's valid
            if (index == s.length()) {
                if (leftToRemove == 0 && rightToRemove == 0) {
                    result.add(expression.toString());
                }
                return;
            }

            char currentChar = s.charAt(index);
            int length = expression.length();

            // If the current character is a left parenthesis and we can remove it, do so
            if (currentChar == '(' && leftToRemove > 0) {
                backtrack(s, index + 1, leftCount, rightCount, leftToRemove - 1, rightToRemove, expression, result);
            }

            // If the current character is a right parenthesis and we can remove it, do so
            if (currentChar == ')' && rightToRemove > 0) {
                backtrack(s, index + 1, leftCount, rightCount, leftToRemove, rightToRemove - 1, expression, result);
            }

            // Append the current character and move to the next one
            expression.append(currentChar);

            // If it's not a parenthesis, just continue
            if (currentChar != '(' && currentChar != ')') {
                backtrack(s, index + 1, leftCount, rightCount, leftToRemove, rightToRemove, expression, result);
            } else if (currentChar == '(') {
                // If it's an open parenthesis, increment the count and continue
                backtrack(s, index + 1, leftCount + 1, rightCount, leftToRemove, rightToRemove, expression, result);
            } else if (rightCount < leftCount) {
                // If it's a close parenthesis and we have more opens, increment the count and continue
                backtrack(s, index + 1, leftCount, rightCount + 1, leftToRemove, rightToRemove, expression, result);
            }

            // Backtrack and remove the last character
            expression.deleteCharAt(length);
        }


        // method 2
        public List<String> removeInvalidParentheses2(String s) {
            Set<String> result = new HashSet<>();
            int maxRemoved = countUnbalanced(s); // To track maximum number of parentheses to remove
            // First pass to find the max number of parentheses to remove
            ;
            backtrack2(s, 0, 0, 0, maxRemoved, new StringBuilder(), result);
            return new ArrayList<>(result);
        }

        private int countUnbalanced(String s) {
            int left = 0, right = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    left++;
                } else if (c == ')') {
                    if (left > 0) {
                        left--;
                    } else {
                        right++;
                    }
                }
            }
            // Total parentheses to remove is the unmatched left and right
            return left + right;
        }
        private void backtrack2(String s, int index, int leftCount, int rightCount, int maxRemove, StringBuilder current, Set<String> result) {
            if (index == s.length()) {
                if (leftCount == rightCount) {
                    result.add(current.toString());
                }
                return;
            }

            char c = s.charAt(index);
            if (c == '(') {
                // Option to remove this '('
                if (maxRemove > 0) {
                    backtrack2(s, index + 1, leftCount, rightCount, maxRemove - 1, current, result);
                }
                // Option to keep this '('
                current.append(c);
                backtrack2(s, index + 1, leftCount + 1, rightCount, maxRemove, current, result);
                current.deleteCharAt(current.length() - 1);
            } else if (c == ')') {
                // Option to remove this ')'
                if (maxRemove > 0) {
                    backtrack2(s, index + 1, leftCount, rightCount, maxRemove - 1, current, result);
                }
                // Option to keep this ')'
                if (leftCount > rightCount) {
                    current.append(c);
                    backtrack2(s, index + 1, leftCount, rightCount + 1, maxRemove, current, result);
                    current.deleteCharAt(current.length() - 1);
                }
            } else {
                // Just add the character if it's not parentheses
                current.append(c);
                backtrack2(s, index + 1, leftCount, rightCount, maxRemove, current, result);
                current.deleteCharAt(current.length() - 1);
            }
        }
    }

}

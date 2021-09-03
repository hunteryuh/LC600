package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.



Example 1:

Input: expression = "2-1-1"
Output: [0,2]
Explanation:
((2-1)-1) = 0
(2-(1-1)) = 2
Example 2:

Input: expression = "2*3-4*5"
Output: [-34,-14,-10,-10,10]
Explanation:
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10

 https://leetcode.com/problems/different-ways-to-add-parentheses/
 */
public class Sol241_DifferentWaysToAddParentheses {
    public List<Integer> differentWaysToAddParentheses(String input) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                String leftPart = input.substring(0, i);
                String rightPart = input.substring(i+1);
                System.out.println("i = " + i);
//                System.out.println(leftPart);
//                System.out.println(rightPart);
                // recursion (divide & conquer)
                List<Integer> leftRes = differentWaysToAddParentheses(leftPart);
                List<Integer> rightRes = differentWaysToAddParentheses(rightPart);
                for (int l: leftRes) {
                    for (int r: rightRes) {
                        int temp = 0;
                        switch (input.charAt(i)) {
                            case '+' : temp = l + r; break;
                            case '-' : temp = l - r; break;
                            case '*' : temp = l * r; break;
                        }
                        res.add(temp);
//                        System.out.println(res);
                    }
                }

            }
        }
        if (res.isEmpty()) {
            res.add(Integer.valueOf(input));
        }

        return res;
    }

    public static void main(String[] args) {
        String input0 = "2-1-0";
        String input = "2*3-4*5";
        Sol241_DifferentWaysToAddParentheses s = new Sol241_DifferentWaysToAddParentheses();
        System.out.println(s.differentWaysToAddParentheses(input));
    }

    // add map memoization
    //cache for memorization  https://www.jiuzhang.com/problem/different-ways-to-add-parentheses/
    // https://www.cnblogs.com/grandyang/p/4682458.html
    Map<String, List<Integer>> map = new HashMap<>();
    public List<Integer> differentWaysToAddParentheses2(String input) {
        if (map.containsKey(input)) {
            return map.get(input);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                String leftPart = input.substring(0, i);
                String rightPart = input.substring(i+1);
                System.out.println("i = " + i);
                List<Integer> leftRes = differentWaysToAddParentheses(leftPart);
                List<Integer> rightRes = differentWaysToAddParentheses(rightPart);
                for (int l: leftRes) {
                    for (int r: rightRes) {
                        int temp = 0;
                        switch (input.charAt(i)) {
                            case '+' : temp = l + r; break;
                            case '-' : temp = l - r; break;
                            case '*' : temp = l * r; break;
                        }
                        res.add(temp);
                    }
                }

            }
        }
        if (res.isEmpty()) {   // at the end of dfs, there is only digits of the input, add it for upper level to use
            res.add(Integer.valueOf(input));
        }
        map.put(input, res);
        System.out.println(map);
        //{2=[2], 3=[3], 4=[4], 5=[5], 3-4*5=[-17, -5], 2*3-4=[-2, 2], 3-4=[-1], 2*3-4*5=[-34, -10, -14, -10, 10], 2*3=[6], 4*5=[20]}
        return res;
    }
}

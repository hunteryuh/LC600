package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAU on 1/27/2018.
 */
/*Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]*/
public class Sol22_GenerateParentheses {
    // back tracking
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res,"", 0, 0, n);
        return res;
    }

    private static void backtrack(List<String> res, String cur, int open, int close, int max) {
        if(cur.length() == max * 2){
            res.add(cur);
            return;
        }
        if( open < max){
            backtrack(res, cur + "(", open + 1, close,max);
        }
        if( close < open){
            backtrack(res, cur + ")", open, close + 1, max);
        }
    }

    public static void main(String[] args) {
        int k = 2;
        System.out.println(generateParenthesis(k));
    }
}

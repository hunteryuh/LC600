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
        if (open < max){
            backtrack(res, cur + "(", open + 1, close,max);
        }
        if (close < open){
            backtrack(res, cur + ")", open, close + 1, max);
        }
    }

    public static void main(String[] args) {
        int k = 3;
//        System.out.println(generateParenthesis(k));
        System.out.println(generateBrackets(k));
    }

    // https://www.jiuzhang.com/problem/generate-parentheses/
    // https://houbb.github.io/2020/06/08/algorithm-07-generate-parentheses
    public static List<String> generateBrackets(int n) {
        List<String> res = new ArrayList<String>();
        dfs(res, new StringBuilder(), 0 , 0, n);
        return res;
    }
    // 其实这里才算是一个标准的回溯算法。
    //
    //核心区别就是我们使用 StringBuilder 替代了 String，注意这里是需要重新设置长度的，这样才能保证回溯正确。
    private static void dfs(List<String> res, StringBuilder sb, int leftb, int rightb, int n) {
        if(sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }
        if (leftb < rightb) return;
        if (leftb > n) return;
        if (leftb < n) {
            sb.append("(");
            dfs(res, sb, leftb +1, rightb, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (rightb < leftb) {
            sb.append(")");
            dfs(res, sb, leftb, rightb + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }

    }
}

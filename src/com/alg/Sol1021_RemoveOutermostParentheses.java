package com.alg;

import java.util.ArrayList;
import java.util.List;

public class Sol1021_RemoveOutermostParentheses {
    public String removeOuterParentheses(String s) {
//        List<String> subs = new ArrayList<>();
        int open = 0;
        StringBuilder sb = new StringBuilder();
        int cur = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                open++;
            }
            if (s.charAt(i) == ')') {
                open--;
            }
            if (open == 0) {
//                subs.add(s.substring(cur, i + 1));
                sb.append(s.substring(cur + 1, i));
                cur = i + 1;
            }
        }
//        for (String sub: subs) {
//            String sol = sub.substring(1, sub.length() - 1);
//            sb.append(sol);
//        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "(()())(())(()(()))";
        Sol1021_RemoveOutermostParentheses ss = new Sol1021_RemoveOutermostParentheses();
        System.out.println(ss.removeOuterParentheses(s));  // ()()()()(())
    }
}

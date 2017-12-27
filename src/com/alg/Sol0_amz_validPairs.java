package com.alg;

import java.util.Stack;

/**
 * Created by HAU on 12/26/2017.
 */
/*给你一个str,里面只有 '('和‘)’,让你数valid pairs一共有多少,如果不是valid就返回-1.*/
/*(判断是不是valid的parenthesis string，不是的话返回-1，是的话返回valid pair个数，即String.length() / 2 )*/
public class Sol0_amz_validPairs {

    public static int validPairs(String s){
        Stack<Character> stack = new Stack<>();
        int res = 0;
        for ( int i = 0; i < s.length();i++){

            if (s.charAt(i) == '('){
                stack.push(')');
            }else if(!stack.isEmpty() && s.charAt(i) == stack.pop()){
                res++;
            }
        }
        return stack.isEmpty()? res: -1;
    }

    public static void main(String[] args) {
        String s = "(()()";
        System.out.println(validPairs(s));
    }
}

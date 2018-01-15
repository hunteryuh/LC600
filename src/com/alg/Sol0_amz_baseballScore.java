package com.alg;

import java.util.Stack;

/**
 * Created by HAU on 1/5/2018.
 */
/*丢棒球，输入一个字符串，其中包括整数，Z，X，或者+。整数代表此轮得分，X：当前成绩是double前面一个分数，+：当前成绩是前两个的和，Z：移除前一个成绩，求最后的总成绩和. 鍥磋鎴戜滑@1point 3 acres

一颗栗子： 输入["5", "-2", "4", "Z","X", 9, "+", "+"]
output: 27
5 : sum = 5
-2 : sum = 5 - 2 = 3
4 : sum = 3 + 4 = 7
Z : sum = 7 - 4 = 3
X : sum = 3 + -2 * 2 = -1 (4被移除了，前一个成绩是-2)
9 : sum = -1 + 9 = 8.
+ : sum = 8 + 9 - 4 = 13 (前两个成绩是9和-4)
+ : sum = 13 + 9 + 5 = 27 (前两个成绩是5 和 9)
用一个stack*/
public class Sol0_amz_baseballScore {
    public static int finalScore(String[] arr) {
        Stack<Integer> stack = new Stack<>();
        int score = 0;
        for(String c: arr){
/*            if (c >= '0' && c <= '9'){
                score += c-'0';
                stack.push(c);
            }*/
            if ( c.equals("Z")){
                score -= stack.pop();
            } else if ( c.equals("X")){
                int s = stack.peek()*2;
                //char s = (char)((stack.peek() - '0')*2 +'0');
                stack.push(s);
                score += s ;
            }else if ( c.equals("+")){
                int top = stack.pop();
                int s = top + stack.peek();
                stack.push(top);
                stack.push(s);
                score += s ;
            }else{
                int s = Integer.parseInt(c);
                score += s;
                stack.push(s);
            }
        }
        return score;

    }
    public static void main(String[] args) {
        String[] arr = {"5", "-2", "4", "Z","X", "9", "+", "+"};
        System.out.println(finalScore(arr));
    }
}

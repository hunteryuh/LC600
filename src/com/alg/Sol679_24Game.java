package com.alg;

import java.util.ArrayList;
import java.util.List;

/*
You are given an integer array cards of length 4. You have four cards, each containing a number in the range [1, 9]. You should arrange the numbers on these cards in a mathematical expression using the operators ['+', '-', '*', '/'] and the parentheses '(' and ')' to get the value 24.

You are restricted with the following rules:

    The division operator '/' represents real division, not integer division.
        For example, 4 / (1 - 2 / 3) = 4 / (1 / 3) = 12.
    Every operation done is between two numbers. In particular, we cannot use '-' as a unary operator.
        For example, if cards = [1, 1, 1, 1], the expression "-1 - 1 - 1 - 1" is not allowed.
    You cannot concatenate numbers together
        For example, if cards = [1, 2, 1, 2], the expression "12 + 12" is not valid.

Return true if you can get such expression that evaluates to 24, and false otherwise.



Example 1:

Input: cards = [4,1,8,7]
Output: true
Explanation: (8-4) * (7-1) = 24

Example 2:

Input: cards = [1,2,1,2]
Output: false



Constraints:

    cards.length == 4
    1 <= cards[i] <= 9

Accepted
58,443
Submissions
120,832
 */
public class Sol679_24Game {
    // https://leetcode-cn.com/problems/24-game/solution/java-hui-su-jing-dian-mian-shi-ti-by-air-pj1k/

    // 不要使用魔法数字 24, 1e-6 等, 需要使用有意义的变量代替
    //double 类型不能使用 "==", 需要用做差和一个较小的值比较判断
    //将函数拆分成几个小的函数分别求解, 可以先提出思路和写一个空函数
    //从 2 个数字开始逐步扩展
    //注意不能产生除 0 错误
    //一旦回溯有一条路能产生 true 需要立即返回
    //
    //作者：airmelt
    //链接：https://leetcode-cn.com/problems/24-game/solution/java-hui-su-jing-dian-mian-shi-ti-by-air-pj1k/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    final double TARGET = 24;
    final double EPSILON = 1e-6;
    public boolean judgePoint24(int[] cards) {
        return helper(new double[]{cards[0], cards[1], cards[2], cards[3]});
    }

    private boolean helper(double[] nums) {
        if (nums.length == 1) {
            return Math.abs(nums[0] - TARGET) < EPSILON;
        }
        // choose 2 different numbers to start back track
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // get all results of these two numbers, add it to a new array with the rest of numbers is nums
                double[] next = new double[nums.length - 1];
                for (int k = 0, pos = 0; k < nums.length; k++) {
                    if (k != i && k!= j) {
                        next[pos] = nums[k];
                        pos++;
                    }
                }
                for (double num: calculate(nums[i], nums[j])) {
                    next[next.length - 1] = num;
                    if (helper(next)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private List<Double> calculate(double a, double b) {
        List<Double> list = new ArrayList<>();
        list.add(a+b);
        list.add(a-b);
        list.add(a*b);
        list.add(b-a);
        if ( Math.abs(b - EPSILON) > 0) {
            list.add(a/b);
        }
        if (Math.abs(a - EPSILON) > 0) {
            list.add(b/a);
        }
        return list;
    }


}

package com.alg;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by HAU on 2/11/2018.
 */
/*
Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
*/
public class Sol739_DailyTemperatures {

    // Time O(n^2)
    public static int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            for (int j = i + 1; j < T.length; j++) {
                if (T[j] > T[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }
        return result;
    }

    public static int[] dailyTemp2(int[] Temp) {
        int[] ans = new int[Temp.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < Temp.length; i++) {
            while (!stack.empty()) {
                int top = stack.peek();
                if (Temp[i] <= Temp[top]) {  // opposite of "warmer" is less or equal to
                    break;
                }
                ans[top] = i - top;
                stack.pop();
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures(T)));
        System.out.println(Arrays.toString(dailyTemp2(T)));
    }
}

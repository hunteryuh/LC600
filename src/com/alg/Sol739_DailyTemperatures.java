package com.alg;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
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

    // monotonic stack
    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0739.%E6%AF%8F%E6%97%A5%E6%B8%A9%E5%BA%A6.md
    public int[] dailyTemperatures2(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            if (temperatures[i] <= temperatures[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    res[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
                stack.push(i);
            }
        }
        return res;
    }
    // remove redundant branches above to get the following

    public int[] dailyTemperatures3(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            //  取出下标进行元素值的比较
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                res[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }

    public int[] dailyTemperaturesWithDetails(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < n; i++) {
            //  取出下标进行元素值的比较
            if (temperatures[i] <= temperatures[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    res[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
                stack.push(i);
            }
        }
        return res;
    }

    //反向
    // arraydeque  iteration direction is different from Stack
    public int[] dailyT(int[] tmp) {
        int n = tmp.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n-1; i >=0; i--) {
            while (!stack.isEmpty() && tmp[i] >= tmp[stack.peek()]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0: stack.peek() - 1;
            stack.push(i);
        }
        return res;
    }
}

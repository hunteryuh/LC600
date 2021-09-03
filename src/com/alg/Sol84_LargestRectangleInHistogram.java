package com.alg;

import java.util.Stack;

public class Sol84_LargestRectangleInHistogram {
    public static int largestRectangleArea(int[] heights) {

        if (heights == null || heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>(); // maintain a motonomous stack
        int length = heights.length;
        int res = 0;

        for (int i = 0; i <= length; i++) {
            int h = i == length ? 0: heights[i];
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                int currentHeight = heights[stack.pop()];
                int right = i - 1;
                int left = stack.isEmpty() ? 0 : stack.peek() + 1;
                int currentWidth = right - left + 1;   //  i : i - stack.peek() - 1;
                res = Math.max(res, currentHeight * currentWidth);
                i--;
            }
        }
        return res;

    }

    public static int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();  //维护单调递增
        int max = 0;
        for (int i = 0; i <= height.length; i++) {
            int curt = (i == height.length) ? -1 : height[i];
            while (!stack.isEmpty() && curt <= height[stack.peek()]) {    //如果栈顶高度大于当前高度
                int h = height[stack.pop()];        //保存栈顶元素信息
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;        //如果栈已经为空，宽度为i，否则i-s.top()-1
                max = Math.max(max, h * w);
            }
            stack.push(i);                //压入栈中
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums1 = {2,1,5,6,2,3};
        int[] nums2 = {2};
        System.out.println(largestRectangleArea(nums1));
        System.out.println(largestRectangleArea2(nums1));
        System.out.println(largestRectangleArea(nums2));

    }

}

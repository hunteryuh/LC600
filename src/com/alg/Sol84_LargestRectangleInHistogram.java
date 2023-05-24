package com.alg;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Sol84_LargestRectangleInHistogram {
    public static int largestRectangleArea(int[] heights) {

        if (heights == null || heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>(); // maintain a monotonous stack
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

    // Monotonous stack passed
    public int largestRectangleArea_MonoStack(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int n = heights.length;
        stack.push(0);
        int res = 0;
        for (int i = 1; i < n; i++) {
            int cur = heights[i];
            if (cur >= heights[stack.peek()]) {  // stack.peek() is the index, need to compare with the value at this index
                stack.push(i);
            } else {
                while (!stack.isEmpty() && cur < heights[stack.peek()]) {
                    int curh = heights[stack.peek()];
                    stack.pop();
                    int width = stack.isEmpty()? i : i - stack.peek() - 1;
                    int curArea = curh * width;
                    res = Math.max(res, curArea);
                }
                stack.push(i);
            }
        }
        // all items in the stack are increasing now
        while (!stack.isEmpty()) {
            int curh  = heights[stack.pop()];  // heights at the stack.pop index
            int curw = n - (stack.isEmpty() ? -1 : stack.peek()) - 1;
            res = Math.max(res, curh * curw);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {2,1,5,6,2,3};
        int[] nums2 = {2};

        Sol84_LargestRectangleInHistogram ss = new Sol84_LargestRectangleInHistogram();
        ss.largestRectangleArea_MonoStack(nums1);
//        System.out.println(largestRectangleArea(nums1));
//        System.out.println(largestRectangleArea2(nums1));
//        System.out.println(largestRectangleArea(nums2));

    }
    // 10^ 7 will get TLE,
    public int largestArea(int[] heights) {
        return calculate(heights, 0, heights.length - 1);
    }

    private int calculate(int[] heights, int start, int end) {
        if (start > end) return 0;
        int minHeightIndex = start;
        for (int i = start; i<=end; i++) {
            if (heights[i] < heights[minHeightIndex]) {
                minHeightIndex = i;
            }
        }

        return 0;
    }

    // two pointer, time limit exceeded
    public int largestRectangleArea22(int[] heights) {
        int maxArea = 0;
        int length = heights.length;
        for (int i = 0; i < length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }
    public int largestRectangleArea3(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            // the index of first element that is less than i
            int left = getLeftBound(heights, i);
            int right = getRightBound(heights, i);
            int width = right - left - 1;
            int height = heights[i];
            res = Math.max(res, width * height);
        }
        return res;
    }

    private int getLeftBound(int[] nums, int t) {
        int res = -1;
        for (int i = t - 1; i >= 0; i--) {
            if (nums[i] < nums[t]) {
                res = i;
                break;
            }
        }
        return res;
    }

    private int getRightBound(int[] nums, int t) {
        int res = nums.length;
        for (int i = t + 1; i < nums.length; i++) {
            if (nums[i] < nums[t]) {
                res = i;
                break;
            }
        }
        return res;
    }

    // time limit exceeded, divide and conquer  n logn
    // T(n) = n + 2T(n/2) = n + 2 ( n/2 + T(n/4)) = n + n + 2 T(n/4)= n + n + ... + n + x T(1)
    public int largestRectangleArea_DC(int[] heights) {
        return getMaxArea(heights, 0, heights.length - 1);
    }

    private int getMaxArea(int[] nums, int left, int right) {
        if (left > right) return 0;
        int minIndex = left;
        for (int i = left; i <=right; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
        }
        // now minIndex is at min
        int cur = (right - left + 1) * nums[minIndex];
        int leftM = getMaxArea(nums, left, minIndex - 1);
        int rightM = getMaxArea(nums, minIndex + 1, right);
        return Math.max(cur, Math.max(leftM, rightM));
    }

}

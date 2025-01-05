package com.alg;

/*
Given n non-negative integers a1, a2, ..., an,
where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints
of line i is at (i, ai) and (i, 0). Find two lines, which together
with x-axis forms a container, such that the container contains the most water.

        Note: You may not slant the container and n is
        at least 2.

        https://leetcode.com/problems/container-with-most-water/
*/


public class Sol11_ContainerWithMostWater {
    // https://leetcode.com/problems/container-with-most-water/editorial/
    public static int maxArea(int[] height){
        int n = height.length;
        if (n < 2) return 0;
        int lo = 0;
        int hi = n - 1;
        int s = 0;

        while (lo < hi) {
            int minHeight = Math.min(height[lo], height[hi]);
            int width = hi - lo;
            s = Math.max(s, minHeight * width);
            if (height[lo] < height[hi]) lo++;
            else hi--;
        }
        return s;
    }

    public static void main(String[] args) {
        //int[] height = {4,3,2,5,7,3,9,1,0,5,3};
        //System.out.println(maxArea(height));
        int[] h2 = { 1,3,2,5,25,24,5};
        System.out.println(maxArea(h2));
        int[] h0 = {2,2,2,2,2};
        System.out.println(maxArea(h0));
    }

    // brute force
    public int maxArea_bf(int[] height) {
        int maxarea = 0;
        for (int i = 0; i < height.length; i++)
            for (int j = i + 1; j < height.length; j++)
                maxarea = Math.max(maxarea, Math.min(height[i], height[j]) * (j - i));
        return maxarea;
    }
}

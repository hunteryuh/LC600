package com.alg;

/**
 * Created by HAU on 9/23/2017.
 */

/*Given n non-negative integers representing an
elevation map where the width of each bar is 1,
compute how much water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.*/
public class Sol42_TrappingRainWater {
    public static int trap(int[] height){
        int left = 0, right = height.length-1;
        int res = 0;
        int leftMax = 0, rightMax = 0;
        while (left < right){
            if (height[left] < height[right]){
                if (height[left] >= leftMax){
                    leftMax = height[left];
                }else{
                    res += leftMax - height[left];
                }
                left++;
            }else{
                if (height[right] >= rightMax){
                    rightMax = height[right];
                }else{
                    res += rightMax - height[right];
                }
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] w = {0,1,0,2,1,0,1,3,2,1,2,1};
        assert trap(w) == 6;
    }
}

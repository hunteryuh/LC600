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
        int[] n = {3,2,1,2,3};
        System.out.println(trap(n));
        System.out.println(trapWater(n));
        System.out.println(maxRainWater(n));
    }
    // method 2 dp
    /*Time complexity: O(n)O(n).
We store the maximum heights upto a point using 2 iterations of O(n) each.
We finally update \text{ans}ans using the stored values in O(n).

Space complexity: O(n) extra space.*/
    public static int trapWater(int[] height){
        if ( height == null) return 0;
        int ans = 0;
        int size = height.length;
        int[] leftMax = new int[size];
        leftMax[0] = height[0];
        for(int i = 1; i < size; i++){
            leftMax[i] = Math.max(height[i],leftMax[i-1]);
        }
        int[] rightMax = new int[size];
        rightMax[size-1] = height[size - 1];
        for(int i = size - 2; i>=0 ; i--){
            rightMax[i]= Math.max(height[i],rightMax[i+1]);
        }
        for( int i = 1; i < size - 1; i++){
            ans += Math.min(leftMax[i],rightMax[i])- height[i];
        }
        return ans;
    }

    //version 3
    // use 1 int[] to store the maxseen from one side, the water on this colum will be min of the max on both sides minus
    //its own height, if it is larger than 0
    public static int maxRainWater(int[] height){
        int maxSeenSoFar = 0;
        int[] maxSeenFromRight = new int[height.length];
        int maxSeenFromleft = 0;
        int res = 0;
        for(int i = height.length - 1; i >=0; i--){
            if(height[i] > maxSeenSoFar){
                maxSeenSoFar = height[i];
                maxSeenFromRight[i] = maxSeenSoFar;
            }else{
                maxSeenFromRight[i] = maxSeenSoFar; // stores the max height up to this index counted from the right side
            }
        }
        for(int i = 0; i < height.length;i++){
            res += Math.max(0,Math.min(maxSeenFromleft, maxSeenFromRight[i]) - height[i]);
            if ( maxSeenFromleft < height[i]){
                maxSeenFromleft = height[i];
            }
        }
        return res;
    }
}

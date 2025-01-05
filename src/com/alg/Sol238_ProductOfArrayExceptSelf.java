package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 1/22/2018.
 */
/*Given an array of n integers where n > 1, nums, return an array output such that output[i]
is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity?
(Note: The output array does not count as extra space for the purpose of space complexity analysis.)*/
public class Sol238_ProductOfArrayExceptSelf {
    // time O(n)
    // space O(n)
    public int[] productExceptSelf0(int[] nums) {
        int n = nums.length;
        int[] leftP = new int[n];
        int[] rightP = new int[n];
        leftP[0] = 1;
        for (int i = 1; i < n ; i++) {
            leftP[i] = leftP[i-1] * nums[i-1];  // products of all items on the left of i
        }
        rightP[n-1] = 1;
        for (int i = n-2; i>=0; i--) {
            rightP[i] = rightP[i+1] * nums[i+1];
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = leftP[i] * rightP[i];
        }
        return res;
    }

    // space complexity O(1)
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for(int i = 1; i < n ; i++){
            res[i] = res[i-1] * nums[i-1];  // products of all items on the left
        }
        int right = 1;
        for (int i = n-1; i >=0; i--){
            res[i] = res[i] * right; // products of all items on the right multiplied
            right *= nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,4,2};
        String s = Arrays.toString(productExceptSelf(nums));
        System.out.println(s);
    }

    // elegant method 2, space complexity O(1)
    public static int[] productExcSelf(int[] nums) {
        int[] ans = new int[nums.length];
        for(int i = 0, temp = 1; i < nums.length; i++) {
            ans[i] = temp;
            temp *= nums[i];
        }
        for(int i = nums.length - 1, temp = 1; i >=0; i--){
            ans[i] = ans[i] * temp;
            temp *= nums[i];
        }
        return ans;
    }
}

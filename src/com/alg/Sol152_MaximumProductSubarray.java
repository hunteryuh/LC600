package com.alg;

/**
 * Created by HAU on 9/6/2017.
 */
/*
Find the contiguous subarray within an array (containing at least
        one number) which has the largest product.

        For example, given the array [2,3,-2,4],
        the contiguous subarray [2,3] has the largest product = 6.*/
public class Sol152_MaximumProductSubarray {
    public static int maxProduct(int[] nums){
        int n = nums.length;
        int[] maxp = new int[n];
        int[] minp = new int[n];
        maxp[0] = minp[0] = nums[0];
        int res = nums[0];
        //int[] res = new int[n];
        //res[0] = nums[0];
        for (int i = 1; i < n ; i++){
            maxp[i]=minp[i] = nums[i];
            if (nums[i]  > 0 ){
                maxp[i] = Math.max(maxp[i],nums[i] * maxp[i-1]);
                minp[i] = Math.min(minp[i],nums[i] * minp[i-1]);
            }else{
                maxp[i] = Math.max(maxp[i], nums[i] * minp[i-1]);
                minp[i] = Math.min(minp[i], nums[i] * maxp[i-1]);
            }
            res = Math.max(res, maxp[i]);
            //res[i] = maxp[i];
        }
        //int m = res[0];
        //for (int i = 0 ; i < n ; i++){
        //    if ( m < res[i]) m = res[i];
        //}
        //return m;
        return res;
    }

    public static void main(String[] args) {
        int[] n = {2,3,-2,4};
        //System.out.println(maxProduct(n)); //6
        //System.out.println(maxProSub(n));
        int[] x = {0,2};
//        System.out.println(maxProSub(x));
//        System.out.println(maxProSub(n));
        int[] nums = {-4,-3,-2};
        Sol152_MaximumProductSubarray ss = new Sol152_MaximumProductSubarray();
        ss.maxProduct1(nums);
    }
    // simpler method 2
    public static int maxProSub(int[] nums){
        int n = nums.length;
        int max = nums[0], min = nums[0];
        int res = nums[0];
        for ( int i = 1; i < n; i++){
            int temp = max;
            max = Math.max(nums[i], Math.max(max * nums[i],min* nums[i])); // could be itself? ,like {0,2}
            // wrong max =  Math.max(max * nums[i],min* nums[i]); // could be itself.
            min = Math.min(nums[i], Math.min(min * nums[i],temp* nums[i]));
            // wrong:min = Math.min(min * nums[i],temp* nums[i]);
            if (max > res) {
                res = max;
            }
        }
        return res;
    }

    // brute force
    public int maxProduct_br(int[] nums) {
        int res = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int cur = 1;
            for (int j = i; j < nums.length; j++) {
                cur = cur * nums[j];
                res = Math.max(cur, res);

            }
        }
        return res;
    }

    // dp with O(1) space
    public int maxProduct1(int[] nums) {
        int res = nums[0];
        int maxP = nums[0];
        int minP = nums[0];
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int previousMaxp = maxP;
            maxP = Math.max(nums[i], Math.max(maxP * nums[i], minP * nums[i]));
            System.out.println(i + " maxP is " + maxP);
            // to calculate minP, need the maxP of previous step, so we need to cache the maxP using a temp variable
            minP = Math.min(nums[i], Math.min(previousMaxp * nums[i], minP * nums[i]));
            System.out.println(i + "minP is " + minP);
            res = Math.max(maxP, res);
        }
        return res;
    }
}

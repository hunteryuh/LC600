package com.alg;

/**
 * Created by HAU on 5/24/2017.
 */
/*
Find the contiguous subarray within an array (containing at least one number)
which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
*/
public class Sol53_MaximumSubarray {
    public static int maxSubArray(int[] nums){
        if (nums.length ==0) return 0;
        int currentSubarray = nums[0];
        int maxSubarray = nums[0];

        int n = nums.length;
        for(int i = 1; i<n; i++){
            currentSubarray = Math.max(currentSubarray+nums[i],nums[i]);
            maxSubarray = Math.max(currentSubarray, maxSubarray);
        }
        return maxSubarray;
    }

    public int maxSubArray2(int[] nums) {
        if (nums.length ==0) return 0;
        int sum = nums[0];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            sum = Math.max(sum,  count);
            if (count <= 0) {
                count = 0;
            }
        }
        return sum;
    }


    // dp approach
    public static int maxsubdp(int[] nums){
        assert nums.length >=1;
        int n = nums.length;
        int[] sum = new int[n]; // sum[i]: the max subarray ending in nums[i]
        int result = nums[0];
        sum[0] = nums[0];

        for (int i = 1; i < n ;i++){
            sum[i] = Math.max(nums[i],sum[i-1] + nums[i]);
            result = Math.max(result, sum[i]);
        }
        return result;
    }

    public static void main(String[] args){
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        //int k = 2;
        System.out.println(maxSubArray(nums));
        System.out.println(maxsubdp(nums));
    }

    //camp
    public static int maxsubarray(int[] nums){
        int cursum = 0;
        int maxsum = Integer.MAX_VALUE;
        for (int i = 0; i< nums.length;i++){
            if (cursum <=0){
                cursum = nums[i];
            }else{
                cursum += nums[i];
            }

            if (cursum > maxsum){
                maxsum = cursum;
            }
        }
        return maxsum;
    }

    public int maxSubarray(int[] nums) {
        int res = nums[0];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            if (count > res) {
                res = count;
            }
            if (count <= 0) {
                count = 0;
            }
        }
        return res;
    }

    // brute force
    public int maxSub(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int cur = 0;
            for (int j = i; j < nums.length; j++) {
                cur += nums[j];
                if (cur > max) {
                    max = cur;
                }
            }
        }
        return max;
    }
}

package com.alg.dp;

/**
 * Created by HAU on 5/25/2017.
 */

/*

You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed,
the only constraint stopping you from robbing each of them is that
 adjacent houses have security system connected and it will automatically
 contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,
determine the maximum amount of money you can rob tonight without alerting the police.
*/
public class Sol198_House_Robber {
    public static int rob198(int[] nums) {
        if(nums.length==0) return 0;
        int n = nums.length;
        int sum1=0;
        int sum2=0;
        for(int i=0;i<n;i++){
            if (i%2==0)
                sum1 = Math.max(sum1+nums[i],sum2);
            else
                sum2 = Math.max(sum2+nums[i],sum1);
        }

        return Math.max(sum1,sum2);

    }
    //O(1) another way
    public int rob(int[] num) {
        int prevNo = 0;
        int prevYes = 0;
        for (int n: num) {
            int temp = prevNo;
            prevNo = Math.max(prevNo, prevYes);
            prevYes = n + temp;
        }
        return Math.max(prevNo, prevYes);
    }

    public static void main(String[] args){
        int[] nums = {1,2,3,4,5,1,2,3,4,5};
        //int k = 2;
        System.out.println(rob198(nums));
    }

    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0198.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8D.md
    // dp[i]：考虑下标i（包括i）以内的房屋，最多可以偷窃的金额为dp[i]。
    public int houseRob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        if (n == 1) return nums[0];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
//        if (n == 2) return dp[1];
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]  + nums[i]);
        }
        return dp[n-1];
    }

    /* the order is: prev2, prev1, num  */
    public int rob3(int[] nums) {
        if (nums.length == 0) return 0;
        int prev1 = 0;
        int prev2 = 0;
        for (int num : nums) {
            int tmp = prev1;
            prev1 = Math.max(prev2 + num, prev1);
            prev2 = tmp;
        }
        return prev1;
    }
}
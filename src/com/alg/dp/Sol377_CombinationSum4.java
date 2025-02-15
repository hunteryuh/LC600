package com.alg.dp;

import java.util.Arrays;

/**
 * Created by HAU on 11/20/2017.
 */
/*Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.*/
public class Sol377_CombinationSum4 {
    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++){
            // i = 0 does not help
            for (int num: nums){
                if ( i >= num){
                    dp[i] += dp[i - num];
                }
                /* two ways of dp:
                dp[i] += dp[i-num]
                dp[i+num] += dp[i]*/
                /*dp[i] = sum(dp[i - nums[j]]),  (i-nums[j] > 0);
                * 如果允许有负数的话就必须要限制每个数能用的次数了, 不然的话就会得到无限大的排列方式, 比如1, -1, target = 1;*/
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int t = 4;
//        System.out.println(combinationSum4(nums,t));
//        System.out.println(combinationSum42(nums,t));
        Sol377_CombinationSum4 ss = new Sol377_CombinationSum4();
        ss.combinationCounts(nums, t);
    }
    public static int combinationSum42(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++){
            // i starts from 0, not 1 0 + num = num
            for (int num: nums){
                if ( i + num <= target){
                    dp[i+num] += dp[i];
                }
            }
        }
        return dp[target];
    }

    public int combinationCounts(int[] nums, int target) {
        int[] dp = new int[target + 1];
        //dp[t]  the count of combinations whose sum is t
        dp[0] = 1;

        for (int j = 0; j <= target; j++) { // 遍历背包
            for (int i = 0; i < nums.length; i++) { // 遍历物品
                if (j >= nums[i]) {
                    dp[j] += dp[j - nums[i]];
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        // [1, 0, 0, 0, 0]
        //[1, 1, 0, 0, 0]
        //[1, 1, 2, 0, 0]
        //[1, 1, 2, 4, 0]
        //[1, 1, 2, 4, 7]

//            for (int i = 0; i < nums.length; i++) { // 遍历物品
//                for (int j = nums[i]; j <= target; j++) { // 遍历背包
//                    dp[j] += dp[j - nums[i]];
//                }
//                System.out.println(Arrays.toString(dp));
//                // [1, 1, 1, 1, 1]
//                //[1, 1, 2, 2, 3]
//                //[1, 1, 2, 3, 4]
//            }
        return dp[target];
    }


    // 如果把遍历nums（物品）放在外循环，遍历target的作为内循环的话，举一个例子：
    // 计算dp[4]的时候，结果集只有 {1,3} 这样的集合，不会有{3,1}这样的集合，因为nums遍历放在外层，3只能出现在1后面！
    // 如果求组合数就是外层for循环遍历物品，内层for遍历背包。
    // 如果求排列数就是外层for遍历背包，内层for循环遍历物品。


}


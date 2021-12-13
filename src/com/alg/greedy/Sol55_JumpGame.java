package com.alg.greedy;

/**
 * Created by HAU on 2/9/2018.
 */
/*Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.*/
public class Sol55_JumpGame {
    public static boolean canJump(int[] nums) {
        // greedy
        int last = nums.length - 1;
        for (int i = last; i >=0 ; i--) {
            if ( i + nums[i] >= last) {
                last = i;
            }
        }
        return last == 0;
    }
    // greedy 2  O(n)
    public static boolean canJ(int[] nums){
        int max = 0;
        for(int i = 0; i < nums.length ; i++){
            if (max < i) return false;
            if (i + nums[i] > max) max = i + nums[i];
        }
        return true;
    }

    // dynamic programming
    public static boolean canJ2(int[] nums) {
        // f[j] : whether it can jump to jth stone
        // f[j=] = OR_0<i<=j (f[i]  AND i + a[i] >= j)
        // time O(n^2), space O(n)
        int n = nums.length;
        boolean[] f = new boolean[n];
        f[0] = true;
        for (int j = 1; j < n; j++) {  // start with 1, not 0 initiated above
            // previous stone (last step)
            f[j] = false;
            for (int i = 0; i < j; i++) {
                if (f[i] && i + nums[i] >= j) {
                    f[j] = true;
                    break;  // found it is true already, no need to check further
                }
            }
        }
        return f[n - 1];
    }

    public static void main(String[] args) {
        int[] nums = {8,4,2,1,0,2,0};
        System.out.println(canJump(nums));
        System.out.println(canJ(nums));
        System.out.println(canJ2(nums));
    }

    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0055.%E8%B7%B3%E8%B7%83%E6%B8%B8%E6%88%8F.md
    // greedy 2
    public boolean canJump2(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        //覆盖范围
        int coverRange = nums[0];
        //在覆盖范围内更新最大的覆盖范围
        for (int i = 0; i <= coverRange; i++) {
            coverRange = Math.max(coverRange, i + nums[i]);
            if (coverRange >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}

package com.alg.greedy;

/**
 * Created by HAU on 2/9/2018.
 */
/*Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Note:
You can assume that you can always reach the last index.

*/
public class Sol45_JumpGameII {
    public static int jump(int[] nums) {
        int jumps = 0;
        int curEnd = 0;
        int curFarthest = 0;
        for(int i = 0; i < nums.length - 1; i++){
            curFarthest = Math.max(curFarthest, i + nums[i]);
//            if (curFarthest >= nums.length - 1) {
//                jumps++;
//                break; //return jumps;
//            }
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump(nums));
        System.out.println(j2(nums));
    }
    // method 2
    public static int j2(int[] nums){
        int maxReach = nums[0];
        int end = 0;
        int step = 0;
        for(int i = 1; i < nums.length ; i++){
            if (i > end) {
                step++;
                end = maxReach;
                if (end >= nums.length - 1) {
                    return step;
                }
            }
            maxReach = Math.max(maxReach, i + nums[i]);

        }
        return step;
    }
}

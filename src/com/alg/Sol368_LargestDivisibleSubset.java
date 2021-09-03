package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a set of distinct positive integers nums, return the largest subset answer such that every pair
(answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.



Example 1:

Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
Example 2:

Input: nums = [1,2,4,8]
Output: [1,2,4,8]


Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 109
All the integers in nums are unique.
 */
public class Sol368_LargestDivisibleSubset {

    //https://leetcode.com/problems/largest-divisible-subset/discuss/83999/Easy-understood-Java-DP-solution-in-28ms-with-O(n2)-time
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);  // need to initialize as 1 to include itself
        int lastIndex = 0;
        int maxLength = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (maxLength < dp[i]) {
                maxLength = dp[i];
                lastIndex = i;
            }
        }
        System.out.println(Arrays.toString(dp));
        // add all divisible numbers in the result
        int curLargest = nums[lastIndex];
        int curDp = dp[lastIndex];
        for (int i = lastIndex; i >= 0 ; i--) {
            if (curLargest % nums[i] == 0  && curDp == dp[i]) {
                res.add(nums[i]);
                curLargest = nums[i];
                curDp--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,16,7,8};
        //  1 4 7 8 16
        // 1 2 2 3 4

        // 1 2 4 7 8
        // 1 2 3 2 4
        Sol368_LargestDivisibleSubset f = new Sol368_LargestDivisibleSubset();
        List<Integer> res  = f.largestDivisibleSubset(arr);
        System.out.println(res);

        int[] arr2 = {2,3,8,9,27};
//        int[] arr2 = {2,3,11};
        //  1 2 1 3
        System.out.println(f.largestDivisibleSubset(arr2));
    }
}

package com.alg;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*
Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.



Example 1:

Input: nums = [4,3,2,3,5,2,1], k = 4
Output: true
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Example 2:

Input: nums = [1,2,3,4], k = 3
Output: false
 */
public class Sol698_PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return false;
        }
        int n = nums.length;
        int targetSum = Arrays.stream(nums).sum();
        if (targetSum % k != 0) return  false;
        targetSum /= k;
        boolean[] visited = new boolean[n];
        Arrays.sort(nums);
        int[] reversedNums = Arrays.stream(nums)
                .boxed().sorted(Collections.reverseOrder())
                .mapToInt(integer -> integer.intValue())
                .toArray();
        return dfs(reversedNums, k, visited, 0, 0, targetSum);
    }

    // dfs works
    private boolean dfs(int[] nums, int k, boolean[] visited, int start, int curSum, int targetSum) {
        if (k == 0) {
            return true;
        }
        if (curSum == targetSum) {
            if (dfs(nums, k-1, visited, start, 0, targetSum)) {
                return true;
            }
        }
        for (int i = start; i < nums.length; i++) {
            if (visited[i]) continue;
            if (nums[i] > targetSum) return false;
            if (curSum + nums[i] > targetSum) return false;
            visited[i] = true;
            if (dfs(nums, k, visited, start, curSum + nums[i], targetSum)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }

    // 2nd dfs works
    private boolean dfs2(int[] nums, int k, boolean[] visited, int start, int curSum, int targetSum) {
        if (k == 1) {
            return true;
        }
        if (curSum > targetSum) return false;
        if (curSum == targetSum) {
            return dfs(nums, k-1, visited, 0, 0, targetSum);  // start has to be 0
        }
        for (int i = start; i < nums.length; i++) {
            if (visited[i]) continue;
            if (nums[i] > targetSum) return false;
//            if (curSum + nums[i] > targetSum) return false; //need to be removed
            visited[i] = true;
            if (dfs(nums, k, visited, i + 1, curSum + nums[i], targetSum)) { // start changed + 1
                return true;
            }
            visited[i] = false;
        }
        return false;
    }


    public static void main(String[] args) {
        int[] nums = {4,3,2,3,5,2,1 };
        int k = 4;
        Sol698_PartitionToKEqualSumSubsets ss = new Sol698_PartitionToKEqualSumSubsets();
        System.out.println(ss.canPartitionKSubsets(nums, k));
    }
}

package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
Given an integer array nums and an integer k,
return the maximum length of a
subarray
that sums to k. If there is not one, return 0 instead.



Example 1:

Input: nums = [1,-1,5,-2,3], k = 3
Output: 4
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.

Example 2:

Input: nums = [-2,-1,2,1], k = 1
Output: 2
Explanation: The subarray [-1, 2] sums to 1 and is the longest.



Constraints:

    1 <= nums.length <= 2 * 105
    -104 <= nums[i] <= 104
    -109 <= k <= 109


 */
public class Sol325_MaximumSizeSubarraySumEqualsk {
    public int maxSubArrayLen(int[] nums, int k) {
        int currSum = 0, maxLen = 0; // set initial values for cumulative sum and max length sum to k
        HashMap<Integer, Integer> sumToIndexMap = new HashMap<Integer, Integer>(); // key: cumulative sum until index i, value: i
        for (int i = 0; i < nums.length; i++) {
            currSum = currSum + nums[i]; // update cumulative sum

            // two cases where we can update maxLen
            if (currSum == k) maxLen = i + 1; // case 1: cumulative sum is k, update maxLen for sure
            else if (sumToIndexMap.containsKey(currSum - k)) maxLen = Math.max(maxLen, i - sumToIndexMap.get(currSum - k)); // case 2: cumulative sum is more than k, but we can truncate a prefix of the array

            // store cumulative sum in map, only if it is not seen
            // because only the earlier (thus shorter) subarray is valuable, when we want to get the maxLen after truncation
            if (!sumToIndexMap.containsKey(currSum)) {
                sumToIndexMap.put(currSum, i);
            }
        }
        return maxLen;
    }

    // presum with hashmap 2
    // https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/solutions/77778/java-o-n-explain-how-i-come-up-with-this-idea/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days
    public int maxSubArrayLen2(int[] nums, int k) {
        if (nums == null || nums.length == 0)   return 0;
        // key: prefix sum, value: right index of the leftmost subarray
        // that has this prefix sum value
        Map<Integer, Integer> counter = new HashMap<>();
        int maxLen = Integer.MIN_VALUE;
        int sum = 0;    // current running sum

        // add the trivial case of 0-length starting subarray to the map
        counter.put(0, -1); // treat its right index as -1

        // iterate through the array only once is enough
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // see if there is a previous array whose prefix sum is (sum - k)
            int target = sum - k;
            if (counter.containsKey(target)) {
                maxLen = Math.max(maxLen, i - counter.get(target));
            }
            // put current index into map if this is the first time we see the
            // current prefix sum value (which implies current index is leftmost)
            counter.putIfAbsent(sum, i);
        }
        // don't forget to check if there is such a subarray that meet the condition
        return maxLen == Integer.MIN_VALUE ? 0 : maxLen;
    }
}

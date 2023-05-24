package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
You are given a binary array nums containing only the integers 0 and 1.
Return the number of subarrays in nums that have more 1's than 0's.
Since the answer may be very large, return it modulo 109 + 7.

A subarray is a contiguous sequence of elements within an array.



Example 1:

Input: nums = [0,1,1,0,1]
Output: 9
Explanation:
The subarrays of size 1 that have more ones than zeros are: [1], [1], [1]
The subarrays of size 2 that have more ones than zeros are: [1,1]
The subarrays of size 3 that have more ones than zeros are: [0,1,1], [1,1,0], [1,0,1]
The subarrays of size 4 that have more ones than zeros are: [1,1,0,1]
The subarrays of size 5 that have more ones than zeros are: [0,1,1,0,1]

Example 2:

Input: nums = [0]
Output: 0
Explanation:
No subarrays have more ones than zeros.

Example 3:

Input: nums = [1]
Output: 1
Explanation:
The subarrays of size 1 that have more ones than zeros are: [1]



Constraints:

    1 <= nums.length <= 105
    0 <= nums[i] <= 1


 */
// https://leetcode.com/problems/count-subarrays-with-more-ones-than-zeros/solutions/1729813/most-understandable-answer-with-diagram-java/
public class Sol2031_CountSubarraysWithMoreOnesThanZeros {
    int mod = (int)1e9 + 7;
    public int subarraysWithMoreZerosThanOnes(int[] nums) {
        // dp[i][0] : subarray ending at i with same count of 0 and 1
        // dp[i][1] : subarray ending at i with more 1 than 1
        int sum = 0;
        int n = nums.length;
        int[][] dp = new int[n+1][2];
        Map<Integer, Integer> map = new HashMap<>(); // subarray sum with count (frequency of prefix sum)
        map.put(0, 1);
        int res = 0;
        for (int i = 1; i <= n; i++) {
            int num = nums[n - 1];
            sum += (num == 1) ? 1 : -1;
            dp[i][0] = map.getOrDefault(sum, 0);
            if (num == 1) {
                dp[i][1] = (dp[i-1][0] + dp[i-1][1] + 1) % mod;
            } else {
                // remove positions that had number of 1s greater than number of 0s by just 1
                dp[i][1] = (dp[i-1][1] - dp[i][0] + mod) % mod;
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            res = (res + dp[i][1]) % mod;
        }
        return res;
    }

    // https://leetcode.com/problems/count-subarrays-with-more-ones-than-zeros/solutions/1525651/c-java-one-pass-o-n-solution/
    public int subarraysWithMoreOnesThanZeros(int[] nums) {

        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);

        // cnt: cumulative number of new valid subarray ending at i
        int res = 0, cnt = 0, prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                // update prefixSum
                prefixSum++;
                // any subarray ending at i-1 and has sum = 0 will create a new valid subarray
                cnt += map.getOrDefault(prefixSum - 1, 0);
            } else {
                // replace 0 with -1, then update prefixSum
                prefixSum--;
                // any subarray ending at i-1 and has sum = 1 will become invalid.
                cnt -= map.getOrDefault(prefixSum, 0);
            }

            res += cnt;
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
            res %= 1000000007;
        }

        return res;
    }

    // https://leetcode.com/problems/count-subarrays-with-more-ones-than-zeros/solutions/2296542/java-solution-using-segment-tree/
    public int subarraysWithMoreOnesThanZeros2(int[] nums) {
        // dp[i]: subarray ending at i that has more ones than 0
        if (nums.length == 0)
            return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int len = 0, sum = 0;
        int[] dp = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : 1;

            if (map.containsKey(sum)) {
                dp[i] = dp[map.get(sum)];
                if (nums[i] == 1)
                    dp[i] += i - (map.get(sum) + 1);
            } else {
                if (nums[i] == 1) {
                    dp[i] = i + 1;
                    if (sum == 0) {
                        dp[i] -= 1;
                    }
                }
            }
            map.put(sum, i);
            len = (len + dp[i]) % 1000000007;
        }
        return len;
    }

    public int subarraysWithMoreOnesThanZeros3(int[] nums) {
        // 0 1 0 1
        Map<Integer, Integer> freq = new HashMap<>();
        int preSum = 0, count = 0, res = 0, mod = (int)1e9 + 7;
        freq.put(0, 1);
        for (int num : nums) {
            if (num == 1) {
                count += freq.getOrDefault(preSum, 0);
                preSum++;
            } else {
                count -= freq.getOrDefault(preSum - 1, 0);
                preSum--;
            }
            freq.put(preSum, freq.getOrDefault(preSum, 0) + 1);
            res = (res + count) % mod;
        }
        return res;
    }
}

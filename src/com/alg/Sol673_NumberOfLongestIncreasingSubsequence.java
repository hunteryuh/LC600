package com.alg;

public class Sol673_NumberOfLongestIncreasingSubsequence {
    // dp
    // https://leetcode.com/problems/number-of-longest-increasing-subsequence/solutions/107293/java-c-simple-dp-solution-with-explanation/
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int res = 0;
        int maxLength = 0;
        /*
        len[i]: the length of the Longest Increasing Subsequence which ends with nums[i].
        count[i]: the number of the Longest Increasing Subsequence which ends with nums[i].
         */
        int[] len = new int[n];
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            len[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (len[i] < len[j] + 1) {
                        len[i] = len[j] + 1;
                        count[i] = count[j];
                    } else if (len[i] == len[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }
            if (maxLength < len[i]) {
                maxLength = len[i];
                res = count[i];
            } else if (maxLength == len[i]) {
                res += count[i];
            }
        }
        return res;
    }
}

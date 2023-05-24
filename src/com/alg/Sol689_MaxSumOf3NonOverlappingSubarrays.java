package com.alg;

import java.util.Arrays;

/*In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example:
Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.*/
public class Sol689_MaxSumOf3NonOverlappingSubarrays {
    /*dp[i][j] stands for in i th sum, the max non-overlap sum we can have from 0 to j
id[i][j] stands for in i th sum, the first starting index for that sum.*/
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[][] dp = new int[4][nums.length + 1];
        int sum = 0;
        int[] accsum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            accsum[i] = sum; // sum from 0 to index i
        }
        int[][] id = new int[4][nums.length + 1];

        for (int i = 1; i < 4; i++) {
            for (int j = k - 1; j < nums.length; j++) {
                int curmax = j - k < 0 ? accsum[j] : accsum[j] - accsum[j - k] + dp[i - 1][j - k];
                if (j - k >= 0) {
                    dp[i][j] = dp[i][j - 1];
                    id[i][j] = id[i][j - 1];
                }
                if (j > 0 && curmax > dp[i][j - 1]) {
                    dp[i][j] = curmax;
                    id[i][j] = j - k + 1;
                }
            }
        }
        int[] res = new int[3];
        res[2] = id[3][nums.length - 1];
        res[1] = id[2][res[2] - 1];
        res[0] = id[1][res[1] - 1];
        return res;

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 6, 7, 5, 1};
        int k = 2;
        //int[] res = maxSumOfThreeSubarrays(nums,k);  //  [0,3,5]
        int[] res = maxSumof3Sub(nums, k);
        int[] ret3 = maxSumOfThree(nums, k);
        System.out.println(Arrays.toString(ret3));
    }

    // method 2, suppose fix j, find left and right intervals
    public static int[] maxSumof3Sub(int[] nums, int k) {
        // W is an array of sums of windows
        int[] W = new int[nums.length - k + 1];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i - k];
            }
            if (i >= k - 1) W[i - k + 1] = sum; // the sum from index 0 with a length of k
        }
        int[] left = new int[W.length];
        int best = 0;
        for (int i = 0; i < W.length; i++) {
            if (W[i] > W[best]) best = i;
            left[i] = best;
        }
        int[] right = new int[W.length];
        best = W.length - 1;
        for (int i = W.length - 1; i >= 0; i--) {
            if (W[i] > W[best]) best = i;
            right[i] = best;
        }
        int[] ans = new int[]{-1, -1, -1};
        for (int j = k; j < W.length - k; j++) {
            int i = left[j - k];
            int r = right[j + k];
            if (ans[0] == -1 || W[i] + W[j] + W[r] > W[ans[0]] + W[ans[1]] + W[ans[2]]) {
                ans[0] = i;
                ans[1] = j;
                ans[2] = r;
            }
        }
        return ans;
    }

    // version 3

    public static int[] maxSumOfThree(int[] nums, int k) {
        int n = nums.length, maxsum = 0;
        int[] sum = new int[n + 1], posLeft = new int[n], posRight = new int[n], ans = new int[3];
        for (int i = 0; i < n; i++) sum[i + 1] = sum[i] + nums[i];
        // DP for starting index of the left max sum interval
        for (int i = k, tot = sum[k] - sum[0]; i < n; i++) {
            if (sum[i + 1] - sum[i + 1 - k] > tot) {
                posLeft[i] = i + 1 - k;
                tot = sum[i + 1] - sum[i + 1 - k];
            } else
                posLeft[i] = posLeft[i - 1];
        }
        // DP for starting index of the right max sum interval
        posRight[n - k] = n - k;
        for (int i = n - k - 1, tot = sum[n] - sum[n - k]; i >= 0; i--) {
            if (sum[i + k] - sum[i] > tot) {
                posRight[i] = i;
                tot = sum[i + k] - sum[i];
            } else
                posRight[i] = posRight[i + 1];
        }
        // test all possible middle interval
        for (int i = k; i <= n - 2 * k; i++) {
            int l = posLeft[i - 1], r = posRight[i + k];
            int tot = (sum[i + k] - sum[i]) + (sum[l + k] - sum[l]) + (sum[r + k] - sum[r]);
            if (tot > maxsum) {
                maxsum = tot;
                ans[0] = l;
                ans[1] = i;
                ans[2] = r;
            }
        }
        return ans;
    }

}

package com.alg;
/*
Given a 0-indexed integer array nums of size n containing all numbers from 1 to n, return the number of increasing quadruplets.

A quadruplet (i, j, k, l) is increasing if:

0 <= i < j < k < l < n, and
nums[i] < nums[k] < nums[j] < nums[l].


Example 1:

Input: nums = [1,3,2,4,5]
Output: 2
Explanation:
- When i = 0, j = 1, k = 2, and l = 3, nums[i] < nums[k] < nums[j] < nums[l].
- When i = 0, j = 1, k = 2, and l = 4, nums[i] < nums[k] < nums[j] < nums[l].
There are no other quadruplets, so we return 2.
Example 2:

Input: nums = [1,2,3,4]
Output: 0
Explanation: There exists only one quadruplet with i = 0, j = 1, k = 2, l = 3, but since nums[j] < nums[k], we return 0.


Constraints:

4 <= nums.length <= 4000
1 <= nums[i] <= nums.length
All the integers of nums are unique. nums is a permutation.
 */
public class Sol2552_CountIncreasingQuadruplets {
    // https://leetcode.com/problems/count-increasing-quadruplets/solutions/3111821/java-prefix-and-suffix-clean-code-with-explanation-o-n-2-time-complexity/
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        /*
        countLarger[i][j] means how many numbers between index i and index j (both inclusive) that is larger than nums[i]
        countSmaller[i][j] means how many numbers between index i and index j (both inclusive) that is smaller than nums[j]
        */
        int[][] countLarger = new int[n][n], countSmaller = new int[n][n];
        for (int i = 0; i < n; i++) {
            int c = 0;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[i]) {
                    countLarger[i][j] = ++c;
                } else {
                    countLarger[i][j] = c;
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            int c = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    countSmaller[j][i] = ++c;
                } else {
                    countSmaller[j][i] = c;
                }
            }
        }
        long res = 0;
        for (int j = 1; j < n - 2; j++) {
            for (int k = j + 1; k < n - 1; k++) {
                if (nums[k] > nums[j]) continue;
                // find the count how many numbers smaller than nums[k] and index smaller than j, and how many numbers larger than nums[j] and index larger than k， than add the product of them into res
                res += (countSmaller[0][k] - countSmaller[j][k]) * (countLarger[j][n - 1] - countLarger[j][k]);
            }
        }
        return res;
    }
}

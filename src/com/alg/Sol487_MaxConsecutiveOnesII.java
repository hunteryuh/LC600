package com.alg;
/*
Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.



Example 1:

Input: nums = [1,0,1,1,0]
Output: 4
Explanation:
- If we flip the first zero, nums becomes [1,1,1,1,0] and we have 4 consecutive ones.
- If we flip the second zero, nums becomes [1,0,1,1,1] and we have 3 consecutive ones.
The max number of consecutive ones is 4.

Example 2:

Input: nums = [1,0,1,1,0,1]
Output: 4
Explanation:
- If we flip the first zero, nums becomes [1,1,1,1,0,1] and we have 4 consecutive ones.
- If we flip the second zero, nums becomes [1,0,1,1,1,1] and we have 4 consecutive ones.
The max number of consecutive ones is 4.



Constraints:

    1 <= nums.length <= 105
    nums[i] is either 0 or 1.



Follow up: What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?

 */
public class Sol487_MaxConsecutiveOnesII {
    // brute force
    // time :O(N^2), space O(1)
    public int findMaxConsecutiveOnes(int[] nums) {
        int longestSequence = 0;
        for (int left = 0; left < nums.length; left++) {
            int numZeroes = 0;

            // check every consecutive sequence
            for (int right = left; right < nums.length; right++) {
                // count how many 0's
                if (nums[right] == 0) {
                    numZeroes += 1;
                }
                // # update answer if it's valid
                if (numZeroes <= 1) {
                    longestSequence = Math.max(longestSequence, right - left + 1);
                }
            }
        }
        return longestSequence;
    }
    // slding window: longest sequence with at most one 0 in the window
    // time: O (n), space O(1)
    public int findMaxConsecutiveOnes2(int[] nums) {
        int longest = 0;
        int left = 0;
        int right = 0;
        int numZeros = 0;
        while (right < nums.length) {
            // add the right most element into the window
            if (nums[right] == 0) {
                numZeros++;
            }
            // window is invalid( 2 zeros), contract the window
            while (numZeros > 1) {
                if (nums[left] == 0) {
                    numZeros--;
                }
                left++;
            }
            // update longest
            longest = Math.max(longest, right - left + 1);
            right++;
        }
        return longest;
    }

    // if allows at most k zeros
    public int findMaxConsecutiveOnesWithAtMostKZeros(int[] nums, int k) {
        int max = 0;
        int zeros = 0;
        for (int left = 0, right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeros++;
            }
            while (zeros > k) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;

    }

}

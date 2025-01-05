package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
Given an integer array nums and an integer k, return the number of good subarrays of nums.

A good array is an array where the number of different integers in that array is exactly k.

For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.



Example 1:

Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
Example 2:

Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].


Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i], k <= nums.length
 */
public class Sol992_SubarraysWithKDifferentIntegers {
    // https://leetcode.com/problems/subarrays-with-k-different-integers/solutions/235417/sliding-window-logical-thinking/

    // https://leetcode.com/problems/subarrays-with-k-different-integers/solutions/672979/analysis-and-explanation-with-visualization/
    /*
    Solution: Compute subarrays of size AT MOST K and subtract subarrays of size AT MOST K - 1.
Why? atMostK(A, k) includes subarrays of size 1, 2, 3, .... k - 1 (all subrrays with size less than K).
How to get rid of those arrays that have size less than K? Compute subarrays of size K - 1
which will give you subarrays of size 1, 2, 3, .... k - 2.
The subarrays that we don't want from the first list will be cancelled out by the subarrays in the second list.
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        Map<Integer, Integer> window1 = new HashMap<>(), window2 = new HashMap<>();
        int l1 = 0, l2 = 0, result = 0;
        // At each loop we count all "good" subarrays that end at A[r].
        for (int r = 0; r < nums.length; ++r) {
            // Add A[r] to both windows.
            window1.put(nums[r], window1.getOrDefault(nums[r], 0) + 1);
            window2.put(nums[r], window2.getOrDefault(nums[r], 0) + 1);

            while (window1.size() > k) {
                window1.put(nums[l1], window1.get(nums[l1]) - 1);
                if (window1.get(nums[l1]) == 0) window1.remove(nums[l1]);
                l1++;
            }
            while (window2.size() >= k) {
                window2.put(nums[l2], window2.get(nums[l2]) - 1);
                if (window2.get(nums[l2]) == 0) window2.remove(nums[l2]);
                l2++;
            }

            // We now count all subarrays with exactly K distinct elements that start in A[l1, l2 - 1] and that end at A[r].
            // These are the subarrays we count: A[l1, r], A[l1 + 1, r], A[l1 + 2, r], ..., A[l2 - 2, r], A[l2 - 1, r].
            result += l2 - l1;
        }
        return result;
    }
}

package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
You are given an integer array nums and an integer x. In one operation, you can either remove the
leftmost or the rightmost element from the array nums and subtract its value from x.
Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.



Example 1:

Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.

Example 2:

Input: nums = [5,6,7,8,9], x = 4
Output: -1

Example 3:

Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.



Constraints:

    1 <= nums.length <= 105
    1 <= nums[i] <= 104
    1 <= x <= 109


 */
public class Sol1658_MinOperationsToReduceXtoZero {
    // https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/discuss/935935/Java-Detailed-Explanation-O(N)-Prefix-SumMap-Longest-Target-Sub-Array
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int num: nums) sum += num;  // sum = Arrays.stream(nums).sum();

        int target = sum - x;
        if (target == 0) return nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        /*
        What happens if below cur == target? In that case, we don't need to exclude any elements
                    from the left side. What's the length of my subarray in this case? It's i + 1, since our
                    arrays are zero-indexed. Thus, in my map, I need to store - 1 so that the subtraction i - a
                    evaluates to i + 1. This is why we made a map.put(0, -1) call earlier.
         */
        map.put(0, -1);
        int maxL = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i];
            if (map.containsKey(cur - target)) {
                maxL = Math.max(maxL, i - map.get(cur - target));
            }
            map.put(cur, i);
        }
        return maxL == Integer.MIN_VALUE? -1 : nums.length - maxL;

    }

    public int minOperations2(int[] nums, int x) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        int n = nums.length;
        int maxi = -1;
        int left = 0;
        int current = 0;

        for (int right = 0; right < n; right++) {
            // sum([left ,..., right]) = total - x
            current += nums[right];
            // if larger, move `left` to left
            while (current > total - x && left <= right) {
                current -= nums[left];
                left += 1;
            }
            // check if equal
            if (current == total - x) {
                maxi = Math.max(maxi, right - left + 1);
            }
        }
        return maxi != -1 ? n - maxi : -1;
    }

    // directly check the sum for both ends
    public int minOperations3(int[] nums, int x) {
        int cur = 0;
        for (int n : nums) cur += n;
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int left = 0;
        for (int right = 0; right < n ; right++) {
            // sum([0,...left) + (right,...n-1]) = x
            cur -= nums[right];
            // if smaller, move `left` forward
            while (cur < x && left <= right) {
                cur += nums[left];
                left += 1;
            }
            if (cur == x) {
                min = Math.min(min,  n - (right - left + 1));
            }

        }
        return min  != Integer.MAX_VALUE ? min : -1;
    }
}

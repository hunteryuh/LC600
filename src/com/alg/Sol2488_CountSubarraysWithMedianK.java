package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
You are given an array nums of size n consisting of distinct integers from 1 to n and a positive integer k.

Return the number of non-empty subarrays in nums that have a median equal to k.

Note:

The median of an array is the middle element after sorting the array in ascending order.
If the array is of even length, the median is the left middle element.
For example, the median of [2,3,1,4] is 2, and the median of [8,4,3,5,1] is 4.
A subarray is a contiguous part of an array.


Example 1:

Input: nums = [3,2,1,4,5], k = 4
Output: 3
Explanation: The subarrays that have a median equal to 4 are: [4], [4,5] and [1,4,5].
Example 2:

Input: nums = [2,3,1], k = 3
Output: 1
Explanation: [3] is the only subarray that has a median equal to 3.


Constraints:

n == nums.length
1 <= n <= 105
1 <= nums[i], k <= n
The integers in nums are distinct.
 */
public class Sol2488_CountSubarraysWithMedianK {
    // https://leetcode.com/problems/count-subarrays-with-median-k/solutions/2851944/java-python-3-c-1-pass-o-n-codes-count-the-prefix-sum-of-the-balance-of-greater-samller/
    public int countSubarrays(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        int balance = 0;
        boolean found = false;
        for (int num: nums) {
            if (num < k) {
                balance--;
            } else if (num > k) {
                balance++;
            } else {
                found = true;
            }
            if (found) {
                // refer to 2Sum, if prefix sum is equal, then between the two index it is an subarray with sum 0
                // i.e. ith index is the median (if sorted)
                // map is not updated for found == true
                // find x in prefix hashmap
                // greater - smaller == 0 or 1
                // => prefix[i] - prefix[x] == 0 or 1
                // => curBalance - prefix[x] == 0 or curBalance - prefix[x] == 1
                // => prefix[x] == curBalance or prefix[x] == curBalance - 1
                count += map.getOrDefault(balance, 0) /* odd*/ + map.getOrDefault(balance - 1, 0) /*even*/;
            } else {
                map.put(balance, map.getOrDefault(balance, 0) + 1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Sol2488_CountSubarraysWithMedianK ss = new Sol2488_CountSubarraysWithMedianK();
        int[] nums = {3, 5, 4};
        System.out.println(ss.countSubarrays(nums, 4));
    }

    // https://leetcode.com/problems/count-subarrays-with-median-k/solutions/2852086/easy-map-solution/
    public int countSubarrays2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int less = 0, great = 0;
        int pivot = -1;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == k) {
                pivot = i;
                break;
            }
        }

        for (int i = pivot; i < n; ++i) {
            if (nums[i] > k) great++;
            else if (nums[i] < k) less++;
            map.put(great - less, map.getOrDefault(great - less, 0) + 1);
        }

        int count = 0;
        less = great = 0;
        for (int i = pivot; i >= 0; --i) {
            if (nums[i] > k) great++;
            else if (nums[i] < k) less++;
            int key = less - great;
            count += map.getOrDefault(key, 0) + map.getOrDefault(key + 1, 0);
        }

        return count;
    }
}

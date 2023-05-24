package com.alg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.

A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:

0 <= i < j < nums.length
|nums[i] - nums[j]| == k
Notice that |val| denotes the absolute value of val.



Example 1:

Input: nums = [3,1,4,1,5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:

Input: nums = [1,2,3,4,5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:

Input: nums = [1,3,1,5,4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
Example 4:

Input: nums = [1,2,4,4,3,3,0,9,2,3], k = 3
Output: 2
Example 5:

Input: nums = [-1,-2,-3], k = 1
Output: 2
 */
public class Sol532_KDiffPairsInAnArray {
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        int res = 0;
        // HashMap<Element, Frequency>
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for (Integer i : map.keySet()) {
            if (k == 0) {
                //count how many elements in the array that appear more than twice.
                if (map.get(i) > 1) {
                    res++;
                }
            } else {
                //2. Only count (element+k) to avoid duplicate: (1, 2) k = 1; only calculate 1 + 1 = 2, never calculate 2 - 1 = 1;
                // if 5 1 3 stored in this order, counter will be added if it finds 1 and 3 but not 5
                if (map.containsKey(i + k)) {
                    res++;
                }
            }
        }
        return res;
    }

    // one pass, check n+k and n-k
    public int findPairs0(int[] nums, int k) {
        if(k < 0) return 0;

        int pairs = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int n: nums) {
            int count = map.getOrDefault(n, 0);
            map.put(n, count + 1);

            if(k != 0 && count == 0) {
                // if k != 0 and this is the first time meeting this number, check n - k and n + k
                if(map.containsKey(n - k)) {
                    pairs++;
                }

                if(map.containsKey(n + k)) {
                    pairs++;
                }
            } else if(k == 0 && count == 1) {
                // if k == 0, only when we have met the number twice do we count it as a pair
                pairs++;
            }
        }

        return pairs;
    }

    public int findPairs2(int[] nums, int k) {
        if (k < 0) return 0;
        Set<Integer> numbers = new HashSet<>();
        Set<Integer> found = new HashSet<>();
        for (int n : nums) {
            if (numbers.contains(n + k)) found.add(n);
            if (numbers.contains(n - k)) found.add(n - k);
            numbers.add(n);
        }
        return found.size();
    }
}

package com.alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].

Return the answer in an array.



Example 1:

Input: nums = [8,1,2,2,3]
Output: [4,0,1,1,3]
Explanation:
For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
For nums[1]=1 does not exist any smaller number than it.
For nums[2]=2 there exist one smaller number than it (1).
For nums[3]=2 there exist one smaller number than it (1).
For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
Example 2:

Input: nums = [6,5,4,8]
Output: [2,1,0,3]
 */
public class Sol1365_HowManyNumbersAreSmallerThanTheCurrentNumber {
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sorted.length; i++) {
            map.putIfAbsent(sorted[i], i);  // use putIfAbsent to avoid overwrite; otherwise the later value will replace the previous value at the same key
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = map.get(nums[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {8,1,2,2,3};
        int[] res = smallerNumbersThanCurrent(nums);
        System.out.println(Arrays.toString(res));
    }
}

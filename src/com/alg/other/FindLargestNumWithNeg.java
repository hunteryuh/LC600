package com.alg.other;

import java.util.HashSet;
import java.util.Set;

/*
Write a function that, given an array A of N integers, returns the lagest integer K > 0 such that both values K and -K exist in array A. If there is no such integer, the function should return 0.

Example 1:

Input: [3, 2, -2, 5, -3]
Output: 3
Example 2:

Input: [1, 2, 3, -4]
Output: 0
 */
public class FindLargestNumWithNeg {
    public int findNum(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int num: nums) {
            set.add(num);
            if (set.contains(num * (-1))) {
                res = Math.max(res, Math.abs(num));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-41,3,2,5,41};
        int[] nums2 = {3,2,-2,5,-3};
        FindLargestNumWithNeg ff = new FindLargestNumWithNeg();
        System.out.println(ff.findNum(nums2));
    }
}

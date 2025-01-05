package com.alg;

import java.util.Arrays;

/*
Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.

Since the result may be very large, so you need to return a string instead of an integer.



Example 1:

Input: nums = [10,2]
Output: "210"

Example 2:

Input: nums = [3,30,34,5,9]
Output: "9534330"



Constraints:

    1 <= nums.length <= 100
    0 <= nums[i] <= 109


 */
public class Sol179_LargestNumber {
    // https://leetcode.com/problems/largest-number/solutions/53158/my-java-solution-to-share/
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        String[] s_num = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            s_num[i] = String.valueOf(nums[i]);
        }

        // Comparator<String> comp = new Comparator<String>() {
        //      public int compare(String str1, String str2) {
        //          String s1 = str1 + str2;
        //          String s2 = str2 + str1;
        //          return s2.compareTo(s1);
        //      }
        // };
        // 3, 30  3 prior to 30
        // 2, 90
        // 9, 5
        Arrays.sort(s_num, (a, b) -> (b + a).compareTo(a + b)); // if negative value, a is prior to b

        if (s_num[0].equals("0")) { // edge case if first (and all others) numbers are 0
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : s_num) {
            sb.append(str);
        }
        return sb.toString();

    }
}

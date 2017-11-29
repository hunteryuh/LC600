package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 7/14/2017.
 */

/*Given an array containing n distinct numbers taken
        from 0, 1, 2, ..., n, find the one that is missing from the array.

        For example,
        Given nums = [0, 1, 3] return 2.

        ask for linear runtime complexity
        using only constant extra space complexity*/

// numers may not come in ascending order....

public class Sol268_MissingNumber {
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (Integer s:nums){  // or int s: nums
            sum += s;
        }
        return n * (n + 1)/2 - sum;  // sum of  arithmetic progression or arithmetic sequence

/*      expected sum
        int exp;
        for (int i = 1; i <= nums.length; i++){
            exp += i;
        }*/
    }

    public static void main(String[] args) {
        int[] nums  = { 0,1,2,4,5,6,7};
        System.out.println(missingNumber(nums));
        System.out.println(missingNum(nums));
        int[] n2 = {0};
        System.out.println(missingNumber(n2));
        System.out.println(missingNum(n2));
    }
    // sort method, time O(nlgn)
    public static int missingNum(int[] nums){
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != i){
                return i;
            }
        }
        return nums.length;  // if it is the largest number
    }
    //bit method
    public static int missingno(int[] nums){
        int miss = 0;
        for (int i = 0; i <nums.length; i++){
            miss ^= i ^ nums[i];
        }
        return miss;
    }
}

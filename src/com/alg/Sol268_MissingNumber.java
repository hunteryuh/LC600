package com.alg;

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
        for (Integer s:nums){
            sum += s;
        }
        return n * (n + 1)/2 - sum;
    }

    public static void main(String[] args) {
        int[] nums  = { 0,1,2,4,5,6,7};
        System.out.println(missingNumber(nums));
        int[] n2 = {1};
        System.out.println(missingNumber(n2));
    }
}

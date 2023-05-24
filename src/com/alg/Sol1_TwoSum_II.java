package com.alg;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by HAU on 6/26/2017.
 */
/*
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up
to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target,
where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution and you may not use the same element twice.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2*/
public class Sol1_TwoSum_II {
    public static int[] twoSum(int[] nums, int target) {

        int[] res = new int[2];
        int n = nums.length;

        // assume array is sorted.

        for ( int i = 0; i < n; i++){
            int t = Arrays.binarySearch(nums,target - nums[i]);
            if ( t > i){
                res[0] = i+1;
                res[1] = t+1;
                break;
            }
        }
        return res;
    }

    public static int[] twoSum2(int[] numbers, int target) {

        int[] res = new int[2];

        // assume array is sorted.
        // element can be equal?!! en.

        for ( int i = 0; i < numbers.length; i++){
            int t = i;
            int lo = i + 1;
            int hi = numbers.length - 1;
            int key = target - numbers[i];
            while ( lo <= hi) {
                int mid = lo + (hi - lo) / 2;

                if (key > numbers[mid]) lo = mid + 1;
                else if (key < numbers[mid]) hi = mid - 1;
                else {
                    t = mid;
                    break;
                }
            }
            if ( t > i){
                res[0] = i+1;
                res[1] = t+1;
                break;
            }
        }

        return res;
    }
    public static void main(String[] args) {
        int nums[] = {2,4,4,10};
        int target = 8;
        //int res[] = twoSum(nums,target);
        int res[] = twoSum2(nums,target);
        System.out.print(Arrays.toString(res));
    }
}

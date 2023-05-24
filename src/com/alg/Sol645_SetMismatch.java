package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 8/10/2017.
 */

/*The set S originally contains numbers from 1 to n. But unfortunately,
        due to the data error, one of the numbers in the set got duplicated
        to another number in the set, which results in repetition of one number
        and loss of another number.

        Given an array nums representing the data status of this set
        after the error. Your task is to firstly find the number occurs twice and
        then find the number that is missing. Return them in the form of an array.*/
public class Sol645_SetMismatch {
    public static int[] findErrorNums(int[] nums){
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++){
            if ( nums[Math.abs(nums[i]) - 1] < 0) res[0] = Math.abs(nums[i]);  // if nums[i] is already * -1, need to use absolute value
            else nums[Math.abs(nums[i]) -1 ] *= -1;
        }
        for (int i = 0; i < nums.length ; i ++){
            if (nums[i] > 0) res[1] = i +1;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums0 = { 1,2,1,4,3};
        int[] nums = { 2,2};
        int[] result = findErrorNums(nums);
        System.out.println(Arrays.toString(result));
    }
}

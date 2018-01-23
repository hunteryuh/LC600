package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 1/22/2018.
 */
/*Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution and you may not use the same element twice.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2*/
public class Sol167_TwoSumII {
    public static int[] twoSum(int[] num, int target) {
        int[] indice = new int[2];
        if( num == null || num.length < 2) return indice;
        int left  =0;
        int right = num.length - 1;
        while ( left < right){
            int sum = num[left] + num[right];
            if( sum == target){
                indice[0] = left;
                indice[1] = right;
                break;
            }else if( sum > target){
                right--;
            }else{
                left++;
            }
        }
        return indice;
    }

    public static void main(String[] args) {
        int[] n = {2,5,6,10};
        int t = 11;
        int[] res= twoSum(n,t);
        System.out.println(Arrays.toString(res));
    }
}

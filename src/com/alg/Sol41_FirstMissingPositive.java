package com.alg;

/**
 * Created by HAU on 1/28/2018.
 */
/*Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.*/
public class Sol41_FirstMissingPositive {
    /*The basic idea is to traversal and try to move the current value to position whose index is exactly the value (swap them).
    Then travelsal again to find the first unusal value, which can not be corresponding to its index.*/
    public static int firstMissingPositive(int[] nums) {
        int i = 0;
        int n = nums.length;
        while ( i < n){
            // If the current value is in the range of (0,length) and it's not at its correct position,
            // swap it to its correct position.
            // Else just continue;
            if(nums[i] >= 0 && nums[i] < n && nums[nums[i]]!= nums[i]){
                swap(nums,i, nums[i]);
            }else{
                i++;
            }
        }
        int k = 1;
        // Check from k=1 to see whether each index and value can be corresponding.
        while ( k < n && nums[k] == k) k++;
        // If it breaks because of empty array or reaching the end. K must be the first missing number.
        if( n == 0 || k < n){
            return k;
        }else{ // If k is hiding at position 0, K+1 is the number.
            // either when array is [0], or [1].
            return nums[0] == k ? k + 1 : k;
        }
    }
    private static void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,-1,-2,3};
        System.out.println(firstMissingPositive(nums));
    }
    // method 2
    public static int firstMissingPositive2(int[] nums) {
        int len=nums.length;
        //int res=0;
        if(len==0) return 1;
        int[] n1=new int[len];
        for(int i=0;i<len;i++){
            if(nums[i]>0&&nums[i]<=len){
                n1[nums[i]-1]=nums[i];
            }
        }
        for(int i=0;i<len;i++){
            if(n1[i]==0) return i+1;
        }
        return len+1;
    }
}

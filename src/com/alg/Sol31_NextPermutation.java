package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 7/11/2017.
 */

/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

        If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

        The replacement must be in-place, do not allocate extra memory.

        Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
        1,2,3 → 1,3,2
        3,2,1 → 1,2,3
        1,1,5 → 1,5,1
*/

public class Sol31_NextPermutation {
    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        if ( n == 1) return;
        int i = n - 1;
        while ( i > 0 && nums[i] <= nums[i-1]){
            i--;
        }
        if (i == 0){
            reverse_sub(nums,0,n-1);
            return;
        }
        if ( i == n - 1){
            swap(nums,i,i-1);
            return;
        }

        reverse_sub(nums,i,n-1);

        int k = i - 1;
        while ( i < n){
            if (nums[i] <= nums[k]){ // including equal
                i++;
            }
			else {
                swap(nums,i,k);
                break;
            }
        }

    }
    private static void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    private static void reverse_sub(int[] nums, int i, int j){
        if ( i >= j) return;
        while ( i < j){
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
/*        int[] nums = { 5,2,7,3,1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        int[] t1 = {1,2,3};
        nextPermutation(t1);
        System.out.println(Arrays.toString(t1));

        int[] t2 = {3,2,1};
        nextPermutation(t2);
        System.out.println(Arrays.toString(t2));

        int[] t3 = { 1,1,5};
        nextPermutation(t3);
        System.out.println(Arrays.toString(t3));*/

//test
        int[] t4 = { 1,5,1};
//        nextPermutation(t4);
//        System.out.println(Arrays.toString(t4));

        Sol31_NextPermutation s = new Sol31_NextPermutation();
        int[] t5 = { 1, 1, 2};
        int[] x = s.nextPermutation2(t5);
        System.out.println(Arrays.toString(x));
    }

    // https://www.lintcode.com/problem/52/
    public int[] nextPermutation2(int[] nums) {

        // write your code here
        int n = nums.length;
        if (n == 1) return nums;
        int i = n - 1;
        while (i >= 1 && nums[i] <= nums[i-1]) {
            i--;
        }

        if (i == 0) {
            return reverse(nums, 0, n-1);
        }

        int[] res = new int[n];
        int j ;
        for (j = 0; j < i - 1; j++) {
            res[j] = nums[j];
        }
        int k = i;

        while (nums[k] > nums[i-1]) {
            k++;
            if (k == n) break;
        }
        res[j] = nums[k-1];
        nums[k-1] = nums[i-1];
        j = i;
        int m = n - 1;
        while (j < n) {
            res[j] = nums[m];
            j++;
            m--;
        }
        return res;
    }

    private int[] reverse(int[] nums, int start, int end) {
        int n = nums.length;
        int[] res = new int[n];
        while (start <= end) {
            res[start] = nums[end];
            res[end] = nums[start];
            start++;
            end--;
        }
        return res;
    }

    public void nextPerm(int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                    Arrays.sort(nums, i + 1, n);
                    return;
                }
            }
        }
        Arrays.sort(nums);

    }

    

}

package com.alg;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by HAU on 7/22/2017.
 */

/*Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

        For example,
        Given [3,2,1,5,6,4] and k = 2, return 5.*/
public class Sol215_kthLargestElementInAnArray {
    public static int findKthLarget_s2(int[] nums, int k){
        int n = nums.length;
        //O(nlog n )
        Arrays.sort(nums);
        return nums[n-k];


    }
    public static int findKthLarget(int[] nums, int k){
        shuffle(nums);
        int n = nums.length;
        int s = n - k;
        int lo = 0;
        int hi = n - 1;
        while ( lo < hi){
            int j = partition(nums,lo, hi);
            if ( j < s) lo = j + 1;
            else if (j > s) hi = j - 1;
            else break;
        }
        return nums[s];
    }



    private static int partition(int[] nums, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true){
            while ( i < hi && nums[++i] < nums[lo]);
            while ( j > lo && nums[lo] < nums[--j]);
            if ( i >= j) break;
            swap(nums,i,j);
        }
        swap(nums,lo,j);
        return j;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    private static void shuffle(int[] nums){
        for (int i = 1 ; i < nums.length; i++){  //i can start 1, as 0 is always swapped with 0
            int r = (int) Math.random() * (i+1);
            swap(nums,i,r);
        }
    }
    private static void shuffle_2(int[] nums){
        Random random = new Random();
        for (int i = 1 ; i < nums.length; i++){
            int r = random.nextInt( i + 1);
            swap(nums,i,r);
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1,4,2,6,5,0};
        System.out.println(findKthLarget(nums,2));
    }
}

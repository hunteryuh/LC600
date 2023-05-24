package com.alg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by HAU on 7/20/2017.
 */

/*Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist.
        Assume that there is only one duplicate number,
        find the duplicate one.*/

// if sort, nlogn , and check difference of neighbor, array is modified..
    // O(N) in the below
public class Sol287_FindtheDuplicateNumber {
    public static int findDuplicate(int[] nums){
        //is the same as find the cycle entry point in linkedlist!

        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;

    }
    // binary search method, need some proof
    public static int findDup_bs(int[] nums){
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi){
            int mid = lo + (hi - lo)/2;
            int count = 0;
            for ( int t: nums){
                if ( t<=mid){
                    count++;
                }
            }
            if ( count <= mid) {
                lo = mid + 1;
            } else hi = mid;
        }
        return lo;
    }

    public static void main(String[] args) {
        int[] n = {2,1,3,4,5,6,4};
        System.out.println(findDuplicate(n));
        System.out.println(findDup_bs(n));
        int[] s = { 6,1,2,4,3,6,5};
        System.out.println(findDuplicate(s));
        System.out.println(findDup_bs(s));
        int[] a = {1,3,2,4,2};
        System.out.println(findDuplicate(a));
        System.out.println(findDup_bs(a));
    }

    // sort O(nlogn)
    public int findDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return nums[i];
            }
        }
        return -1;
    }

    // Set O(n)
    public int findDuplicate3(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        return -1;
    }
}

package com.alg.other;

public class Uber_ShiftNumberToMakeArray {
    public boolean canShift(int[] nums) {
        //4 5 1 2 3
        int n = nums.length;
        boolean dropped = false;
        for (int i = 1; i < n; i++) {
            if (nums[i] < 1 || nums[i] > n) {
                return false;
            }
            if (nums[i] < nums[i-1]) {
                if (dropped) return false;
                dropped = true;
            } else if (nums[i] - nums[i-1] != 1) {
                return false;
            }
        }
        return !dropped || nums[n-1] + 1 == nums[0];
    }

    public static void main(String[] args) {
        int[] nums = { 4, 5, 1 ,2 ,3  }; // true
        Uber_ShiftNumberToMakeArray uu= new Uber_ShiftNumberToMakeArray();
        System.out.println(uu.canShift(nums));
    }
}

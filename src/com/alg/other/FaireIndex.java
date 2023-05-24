package com.alg.other;

import java.util.Arrays;

public class FaireIndex {
    public int numOfFairIndexes(int[] nums1, int[] nums2) {
        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum();
        if (sum1 != sum2) return 0;
        int count = 0;
        int n = nums1.length;
        int temp1 = 0;
        int temp2 = 0;
        for (int i = 0; i < n; i++) {
            temp1 += nums1[i];
            temp2 += nums2[i];
            if (temp1 == temp2 && temp1 * 2 == sum1) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] num1 = {4,-1,0,3};
        int[] num2 = {-2,5,0,3};
        FaireIndex ff = new FaireIndex();
        System.out.println(ff.numOfFairIndexes(num1, num2));
    }

}

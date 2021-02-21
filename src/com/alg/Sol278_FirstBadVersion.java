package com.alg;

/**
 * Created by HAU on 11/15/2017.
 */
/*Suppose you have n versions [1, 2, ..., n] and you want
to find out the first bad one, which causes all the following ones to be bad.*/
public class Sol278_FirstBadVersion {
    public static int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) /2;
            if (isBadVersion(mid)){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
    private static boolean isBadVersion(int t){
        return t>0;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2,-1,-1,-1};
        System.out.println(firstBadVersion(5));  // not real test
    }
}

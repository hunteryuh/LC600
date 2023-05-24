package com.alg;
/*
You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.

Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.



Example 1:

Input: flowerbed = [1,0,0,0,1], n = 1
Output: true
Example 2:

Input: flowerbed = [1,0,0,0,1], n = 2
Output: false
 */
public class Sol605_CanPlaceFlowers {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        if (flowerbed.length == 1) {
            return n + flowerbed[0] <= 1;
        }
        for (int i = 0; i < flowerbed.length ; i++) {
            if (i == 0 && flowerbed[i] == 0 && flowerbed[1] == 0) {
                flowerbed[i] = 1;
                count++;
            }
            if (i != 0 && i <flowerbed.length - 1 && flowerbed[i-1] == 0 && flowerbed[i] == 0 && flowerbed[i+1] == 0) {
                flowerbed[i] = 1;
                count++;
            }
            if (i != 0 && i == flowerbed.length - 1 && flowerbed[i-1] == 0 && flowerbed[i] == 0) {
                flowerbed[i] = 1;
                count++;
            }
            if (count >= n) {  // deal with n == 0
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[] bed = {1,0, 0, 0,0, 1};
        int n = 1;
        System.out.println(canPlaceFlowers(bed, n));
        int[] bed2 = {1,0, 0,0, 1};
        int k = 2;
        System.out.println(canPlaceFlowers(bed2, k));
    }
    // solution from leetcode  https://leetcode.com/problems/can-place-flowers/solution/

    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        int i = 0, count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
            i++;
        }
        return count >= n;
    }


    public boolean canPlaceFlowers3(int[] flowerbed, int n) {
        int i = 0, count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                // used || to handle i == 0 and i == n - 1 cases in case it is out of index bound
                flowerbed[i++] = 1;
                count++;
            }
            if(count>=n)
                return true;
            i++;  // go to i + 2
        }
        return false;
    }
}

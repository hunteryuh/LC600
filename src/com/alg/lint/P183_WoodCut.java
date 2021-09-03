package com.alg.lint;

/*

https://www.lintcode.com/problem/183/
Description
Given n pieces of wood with length L[i] (integer array).
Cut them into small pieces to guarantee you could have equal or more than
k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k,
return the maximum length of the small pieces.
 */
public class P183_WoodCut {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public static int woodCut(int[] L, int k) {
        // write your code here
        if (L == null || L.length == 0) {
            return 0;
        }
        int max = L[0];

        for (int i = 0; i < L.length; i++) {
            max = Math.max(max, L[i]);
        }
        int start = 1;
        int end = max;
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (count(mid, L) >= k) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (count(end, L) >= k) {
            return end;
        }
        if (count(start, L) >= k) {
            return start;
        }
        return 0;
    }

    private static int count(int length, int[] nums) {
        int sum = 0;
        for(int num: nums) {
            sum += num/length;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] L = {232, 124, 456};
        int k = 7;
        System.out.println(woodCut(L, k));
        System.out.println(woodCut(L, 1555));
    }
}

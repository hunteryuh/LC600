package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 Given a string n representing an integer, return the closest integer (not including itself), which is a palindrome. If there is a tie, return the smaller one.

 The closest is defined as the absolute difference minimized between two integers.
 */
public class Sol564_FindtheClosestPalindrome {
    public static String nearestPalindromic(String n) {
        int order = (int) Math.pow(10, n.length()/2);
        Long ans = Long.valueOf(new String(n)); //5633
        Long noChange = mirror(ans); //5633->5665
        Long larger = mirror( ans/order * order + order + 1);// 5633->5701->5775
        Long smaller = mirror( ans/order * order - 1); //5633->5599->5555
        if (noChange > ans) {
            larger =  Math.min(larger, noChange);
        } else if (noChange < ans){
            smaller = Math.max(noChange, smaller);
        }
        Long res;
        if (ans - smaller <= larger - ans){
            res = smaller;
        }else  {
            res = larger;
        }
        return String.valueOf(res);
    }

    private static Long mirror(Long ans) {
        char[] arr = String.valueOf(ans).toCharArray();
        int i = 0;
        int j = arr.length - 1;
        while (i < j){
            arr[j] = arr[i]; // only update the right half
            j--;i++;
        }
        return Long.valueOf(new String(arr));
    }

    public static void main(String[] args) {
        String num = "5633";
        System.out.println(nearestPalindromic(num));
    }

    // https://leetcode.com/problems/find-the-closest-palindrome/solutions/122957/java-compare-five-candidates-get-result-easy-to-understand/
    public String nearestPalindromic2(String n) {
        //12345  1234
        int len = n.length();
        int middle = len % 2 == 0 ? len/2 : len/2 + 1;
        String leftHalfString = n.substring(0, middle);
        long leftHalf = Long.parseLong(leftHalfString);
        List<Long> candidates = new ArrayList<>();
        candidates.add(getPalindrome(leftHalf, len % 2 == 0)); // 12321
        candidates.add(getPalindrome(leftHalf + 1, len % 2 == 0)); // 12421
        candidates.add(getPalindrome(leftHalf - 1, len % 2 == 0)); //12221
        candidates.add((long) (Math.pow(10, len - 1) - 1)); //9999
        candidates.add((long) (Math.pow(10, len)) + 1);  //100001

        long diff = Long.MAX_VALUE;
        long nl = Long.parseLong(n);
        long res = 0;
        for (long can: candidates) {
            if (can == nl) continue;
            if (Math.abs(can - nl) < diff) {
                diff = Math.abs(can - nl);
                res = can;
            } else if (Math.abs(can - nl) == diff) {
                res = Math.min(res, can);
            }
        }
        return String.valueOf(res);

    }

    private long getPalindrome(Long half, boolean isEven) {
        long res = half;
        if (!isEven) {
            half = half / 10;
        }
        while (half > 0) {
            res = res * 10 + half % 10;
            half /= 10;
        }
        return res;
    }
}

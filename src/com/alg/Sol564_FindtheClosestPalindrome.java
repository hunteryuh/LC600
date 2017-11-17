package com.alg;

/**
 * Created by HAU on 11/16/2017.
 */
public class Sol564_FindtheClosestPalindrome {
    public static String nearestPalindromic(String n) {
        int order = (int) Math.pow(10, n.length()/2);
        Long ans = Long.valueOf(new String(n)); //5633
        Long noChange = mirror(ans); //5633->5665
        Long larger = mirror( ans/order * order + order + 1);// 5633->5701->5775
        Long smaller = mirror( ans/order * order - 1); //5633->5599->5555
        if ( noChange > ans){
            larger =  Math.min(larger, noChange);
        }else if ( noChange < ans){
            smaller = Math.max(noChange,smaller);
        }
        Long res;
        if ( ans - smaller <= larger - ans){
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
            arr[j] = arr[i];
            j--;i++;
        }
        return Long.valueOf(new String(arr));
    }

    public static void main(String[] args) {
        String num = "5633";
        System.out.println(nearestPalindromic(num));
    }
}

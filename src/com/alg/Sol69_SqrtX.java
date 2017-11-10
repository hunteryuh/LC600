package com.alg;

/**
 * Created by HAU on 8/21/2017.
 */
/*Implement int sqrt(int x).

        Compute and return the square root of x.*/
public class Sol69_SqrtX {
    public static int mySqrt_slow(int x){
        int res = 0;
        while (res * res < x){
            res++;
        }
        if ( res * res > x)
            return res - 1;
        return res;  // time limited for largest int
    }
    public static int mySqrt(int x){
        // binary search [0, n/2 + 1], time O(log n)
        if ( x == 0 || x== 1) return x;
        int start = 1;
        int end = x /2 ;
        int ans = 0;
        while (start <= end){
            int mid = start + (end - start)/2;
            if (mid  <= x / mid){
                ans = mid;
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }


        return ans;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
        System.out.println(Integer.MAX_VALUE);
    }
}

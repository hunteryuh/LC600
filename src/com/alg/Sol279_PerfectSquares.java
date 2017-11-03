package com.alg;

/**
 * Created by HAU on 11/2/2017.
 */
/*Given a positive integer n,
find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.*/
public class Sol279_PerfectSquares {
    public static int numSquares(int n){
        if( n<= 0) return -1;
        int[] counts = new int[n+1];
        counts[0] = 0;
        for (int i = 1; i <= n ; i++){
            counts[i] = Integer.MAX_VALUE;
            for (int j = 1; j *j <= i; j++){
                if (counts[i] > counts[i - j *j] + 1){
                    counts[i] = counts[i - j* j] + 1;
                }
            }
        }
        return counts[n];
    }

    public static void main(String[] args) {
        int n = 22;
        System.out.println(numSquares(n));
    }
}

package com.alg;

/**
 * Created by HAU on 12/29/2017.
 */
/*given an array of intergers, find the greatest common divisor for all
* GCD*/
public class Sol0_amz_GCDofArray {
    public static int GCDArray(int[] nums){
        if(nums == null || nums.length == 0) return 0;
        int gcd = nums[0];
        for(int i = 1; i < nums.length ; i++){
            gcd = getGCD(gcd, nums[i]);
        }
        return gcd;
    }

    private static int getGCD(int n1, int n2) {
        if ( n1 == 0 || n2 == 0) return 0;
        while(n1 != 0 && n2 != 0){
/*            if ( n2 > n1){
                n1 = n1 ^ n2;
                n2 = n1 ^ n2;
                n1 = n1 ^ n2; // switch n1 and n2
            }*/
            int temp = n1 % n2;
            n1 = n2;
            n2 = temp;
        }
        return n1 + n2;
    }
    private static int findGCD(int a, int b){
        while (b!= 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a; //( if b == 0) return a
    }

    public static void main(String[] args) {
        int[] nums = { 6,90,27};
        System.out.println(GCDArray(nums));
    }
}

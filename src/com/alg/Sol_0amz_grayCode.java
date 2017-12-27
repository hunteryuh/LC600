package com.alg;

/**
 * Created by HAU on 12/26/2017.
 */
/* Given two hexadecimal numbers find if they can be consecutive in gray code
For example: 10001000, 10001001
return 1
since they are successive in gray code

Example2: 10001000, 10011001
return -1
since they are not successive in gray code.*/
public class Sol_0amz_grayCode {
    public static int isConGrayCode(int t1, int t2){
        int x = (t1 ^ t2);
        int total = 0;
        x =  (x & (x-1));
            //(x & (x - 1)) will have all the bits equal to the x except for the rightmost 1 in x.
        return x == 0 ? 1 : -1;


    }

    public static void main(String[] args) {
        int t1 = Integer.parseInt("10011001",2);
        int t2 = Integer.parseInt("10001001",2);
        System.out.println(isConGrayCode(t1,t2));

    }
}

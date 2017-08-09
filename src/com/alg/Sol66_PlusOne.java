package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 6/26/2017.
 */
/*Given a non-negative integer represented as a non-empty array of digits,
plus one to the integer.

   You may assume the integer do not contain any leading zero,
   except the number 0 itself.

        The digits are stored such that the most significant digit is
        at the head of the list.*/
public class Sol66_PlusOne {
    public static int[] plusOne_alt(int[] digits) {
        int n = digits.length;
        for(int i = n - 1; i >= 0; i--){
            if (digits[i] != 9){
                digits[i]++;
                return digits;
            }else {
                digits[i] = 0;
                if (i == 0){
                    digits[i] = 10;  // can accept double digits at the leading index?
                    return digits;
                }

            }

        }
        return digits;
    }
    public static int[] plusOne(int[] digits) {
        int n = digits.length;

        for(int i = n - 1; i >= 0; i--){
            if (digits[i] != 9){
                digits[i]++;
                return digits;
            }else {
                digits[i] = 0;
                if (i == 0){
                    int[] res = new int[n+1];
                    res[i] = 1;  //
                    return res;
                }

            }

        }
        return digits;
    }

    public static void main(String[] args) {
        int[] num = {0};
        int[] res = plusOne(num);
//        for ( int i: res){
//            System.out.print(i+ " ");
//        }
        System.out.println(Arrays.toString(res));
    }
}

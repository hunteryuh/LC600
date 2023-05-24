package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAU on 1/4/2018.
 */
/*A self-dividing number is a number that is divisible by every digit it contains.

For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.

Also, a self-dividing number is not allowed to contain the digit zero.

Given a lower and upper number bound, output a list of every possible self dividing number, including the bounds if possible.

Example 1:
Input:
left = 1, right = 22
Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]*/
public class Sol728_SelfDividingNumbers {
    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for(int i = left; i <= right; i++){
            boolean isValid = true;
            int x = i;
            while (x > 0) {
                int d = x % 10;
                x /= 10;
                if ( d == 0 || i % d > 0){
                    isValid = false;
                    break;
                }

            }
            if(isValid){
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int left = 21, right = 22;
        System.out.println(selfDividingNumbers(left,right));
    }
}

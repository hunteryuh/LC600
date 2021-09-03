package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
Given an integer n, return any array containing n unique integers such that they add up to 0.


Example 1:

Input: n = 5
Output: [-7,-1,1,3,4]
Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
Example 2:

Input: n = 3
Output: [-1,0,1]
Example 3:

Input: n = 1
Output: [0]
*/

public class Sol1304_FindNUniqueIntegersSumUpToZero {
    public static int[] sumZero(int n) {
        // 1, -1, 2, -2,  n = 4
        // 1,-1,2,-2,0   n = 5
        int[] res = new int[n];
        int init = 1;
        for(int i = 0 ; i < n/2; i++) {
            res[i] = init;
            res[n - 1 - i] = -init;
            init++;
        }
//        if (n % 2 == 1) {
//            res[n/2] = 0;
//        }  // not needed as the initialization makes all values to be 0

        return res;
    }


    public static void main(String[] args) {
        int n = 5;
        int[] res = sumZero(n);
        System.out.println(Arrays.toString(res));

    }
}

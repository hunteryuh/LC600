package com.alg;

import java.util.ArrayList;

/**
 * Created by HAU on 1/18/2018.
 */
/*The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.*/
public class Sol60_PermutationSequence {
    public static String getPermuation(int n, int k){
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> num = new ArrayList<>();
        int fact = 1;
        for( int i = 1; i <= n; i++){
            fact *= i;
            num.add(i); //  // numbers = {1, 2, 3, 4}
        }
        for(int i = 0, g = k - 1; i < n; i++){
            fact = fact / ( n - i); // 24 /4  6/3
            int index = g / fact;  //  13/ 6
            sb.append(num.remove(index));
            g -= index * fact;


        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int n = 4;
        int k = 14;
        System.out.println(getPermuation(n,k));  // 14th : 3142
    }
}

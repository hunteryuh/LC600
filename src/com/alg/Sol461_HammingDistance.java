package com.alg;

/**
 * Created by HAU on 9/1/2017.
 */

/*The Hamming distance between two integers
        is the number of positions at which
        the corresponding bits are different.

        Given two integers x and y, calculate
        the Hamming distance.*/
public class Sol461_HammingDistance {
    public static int hammingDistance(int x, int y){
        int xor = x ^ y;
        int count = 0;
        for(int i = 0; i < 32 ; i++){
            count += (xor >> i) & 1;
        }
        return count;
    }

    public static void main(String[] args) {
        int x = 5;
        int y = 6;
        //System.out.println(hammingDistance(x,y));
        assert hammingDistance(x,y) == 2;
    }
}

package com.alg;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by HAU on 9/9/2017.
 */

/*
* Given an integer array with even length, where different numbers in this array represent different kinds of candies. Each number means one candy of the corresponding kind. You need to distribute these candies equally in number to brother and sister.
* Return the maximum number of kinds of candies the sister could gain.
* */
public class Sol575_DistributeCandies {
    public static int distributeCandies(int[] candies){
        Set<Integer> set = new HashSet<>();
        for (int c : candies){
            set.add(c);
        }
        if ( set.size() < candies.length / 2) return set.size();
        return candies.length/2;
    }

    public static void main(String[] args) {
        int[] candies = { 1,1,1,2,3,1,1,1};
        System.out.println(distributeCandies(candies));

    }
}

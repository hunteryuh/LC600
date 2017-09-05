package com.alg;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by HAU on 9/4/2017.
 */
/*Given an array of integers, every element appears twice except for one.
        Find that single one.*/
public class Sol136_SingleNumber {
    public static int singleNumber(int[] nums){
        HashSet<Integer> set  = new HashSet<>();
        for (int n : nums){
            if (set.contains(n)){
                set.remove(n);
            }else{
                set.add(n);
            }
        }
        assert set.size() == 1;
        return set.iterator().next();
    }

    //bit manipulation
    public static int sN(int[] nums){
        int res = 0;
        for (int n: nums){
            res ^= n;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,5,2,3};
        System.out.println(singleNumber(nums));
        System.out.println(sN(nums));
    }

}

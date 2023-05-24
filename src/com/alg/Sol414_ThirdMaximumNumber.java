package com.alg;

import java.util.TreeSet;

/**
 * Created by HAU on 9/6/2017.
 */

/*
* Given a non-empty array of integers, return the third maximum number in this array. If it does not exist,
* return the maximum number. The time complexity must be in O(n).*/
public class Sol414_ThirdMaximumNumber {
    public static int thirdMax2(int[] nums){
        //using set
        TreeSet<Integer> set = new TreeSet<>();
        for (int n : nums){
            set.add(n);
            if (set.size() > 3){
                set.remove(set.first());
            }
        }
        return set.size() == 3? set.first():set.last();
    }

    public static int thirdMax(int[] nums ){
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        int i = 0;
        //int n = nums.length;
        for (int n : nums){
            if ( n > first){
                third  = second;
                second = first;
                first = n;
            }else if ( n > second && n < first){
                third = second;
                second = n;
            }else if ( n > third && n < second){
                third = n;
            }
        }
        if ( third == second || third == Long.MIN_VALUE){
            return (int)first;
        }else return (int)third;
    }

    public static void main(String[] args) {
        int[] n = {1,2,-2147483648};
        assert Integer.MIN_VALUE == thirdMax(n);
        int[] t = { 1,2};
        assert 2 == thirdMax(t);
    }
}

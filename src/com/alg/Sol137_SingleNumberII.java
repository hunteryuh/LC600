package com.alg;

/**
 * Created by HAU on 9/4/2017.
 */
/*Given an array of integers, every element appears three times
        except for one, which appears exactly once. Find that single one.*/
public class Sol137_SingleNumberII {
    public static int singleNumberII(int[] nums){
        int res = 0;
        for (int i = 0; i < 32; i++){
            int sum = 0;
            for (int j = 0; j < nums.length; j++){
                if ( ((nums[j] >> i ) & 1) == 1 ){
                    sum++;    // sum+= (nums[j] >> i ) &1
                }
            }
            res |= (sum%3) << i;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] n  = { 7,7,11,7};
        System.out.println(singleNumberII(n));
    }

}

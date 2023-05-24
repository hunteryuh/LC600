package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 11/21/2017.
 */
/*Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?*/
public class Sol260_SingleNumberIII {
    public static int[] singleNumber(int[] nums) {
        // bit manipulation
        /*think of xor as "this or that, but not both!".*/
        int diff = 0;
        for (int num: nums){
            diff ^= num;
        }
        /*我们先把原数组全部异或起来，那么我们会得到一个数字，
        这个数字是两个不相同的数字异或的结果，我们取出其中任意一位为‘1’的位，
        为了方便起见，我们用 a &= -a 来取出最右端为‘1’的位，
        然后和原数组中的数字挨个相与，那么我们要求的两个不同的数字就被分到了两个小组中，
        分别将两个小组中的数字都异或起来，就可以得到最终结果了，*/
        // get its last set bit  negative: just flip 1 as 0 and 0 as 1 then and,
        // the last most 1 bit
        diff &= -diff;
        int[] res = new int[2];
        for (int num : nums){
            if((num & diff) == 0) {
                // the bit is not set
                res[0] ^= num;
            }else{
                //the bit is set
                res[1] ^= num;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] n = {1,2,2,1,5,4};
        int[] r = singleNumber(n);
        System.out.println(Arrays.toString(r));
    }
}

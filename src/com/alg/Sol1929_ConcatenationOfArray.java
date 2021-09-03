package com.alg;

public class Sol1929_ConcatenationOfArray {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] res = new int[n*2];
        for (int i = 0; i < n*2; i++) {
//            if (i < n) {
//                res[i] = nums[i];
//            } else {
//                res[i] = nums[i-n];
//            }
            res[i] = nums[i%n];

        }
        return res;
    }
}

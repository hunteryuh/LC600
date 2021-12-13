package com.alg;

/**
 * Created by HAU on 7/26/2017.
 */
public class Sol12_IntegerToRoman {
    public static String intToRoman(int num){
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < nums.length; i++){
            while (num >= nums[i]) {
                num -= nums[i];
                res.append(symbols[i]);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        int n = 432;
        System.out.println(intToRoman(n));
    }
}

package com.alg;

/**
 * Created by HAU on 12/2/2017.
 */
/*Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"*/
public class Sol273_IntegerToEnglishWords {
    //Time Complexity - O(n)， Space Complexity - O(n)。
    private static final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};
    public static String numberToWords(int num) {
        if ( num == 0) return "Zero";
        String res ="";
        int i = 0;

        while (num > 0){
            if (num % 1000 != 0){
                res = helper( num%1000) + THOUSANDS[i] +" " + res;
            }
            num /= 1000;
            i++;
        }
        return res.trim();
    }
    private static String helper(int num){
        if (num == 0){
            return "";
        }else if( num < 20){
            return LESS_THAN_20[num] + " ";
        }else if ( num < 100){
            return TENS[num/10] + " " + helper(num % 10);
        }else  // num > 100
        {
            return LESS_THAN_20[num/100] + " Hundred " + helper( num %100);
        }
    }

    public static void main(String[] args) {
        int n1 = 324;
        int n2 = 32400;
        System.out.println(numberToWords(n1));
        System.out.println(numberToWords(n2));
    }
}

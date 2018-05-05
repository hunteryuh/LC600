package com.alg;

import java.util.HashMap;
import java.util.Map;

/*X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.

A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.

Now given a positive number N, how many numbers X from 1 to N are good?

Example:
Input: 10
Output: 4
Explanation:
There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.*/
public class Sol788_RotatedDigits {
    public static int rotatedDigits(int N) {
        int count = 0;
        for (int i = 2; i <= N; i++){
            if(isGood(i))
                count++;
        }
        return count;
    }

    private static boolean isGood(int n) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0','0');
        map.put('1','1');
        map.put('2','5');
        map.put('5','2');
        map.put('6','9');
        map.put('8','8');
        map.put('9','6');

        String original = String.valueOf(n);
        String rotated = "";
        for(char c: original.toCharArray()){
            if(map.containsKey(c)){
                rotated += map.get(c);
            }else{
                return false;
            }
        }
        return !rotated.equals(original);
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(rotatedDigits(n)); //4
        int m = 19;
        System.out.println(rotatedDigits2(m)); //8
    }
    public static int rotatedDigits2(int N) {
    /*Using a int[] for dp.
dp[i] = 0, invalid number
dp[i] = 1, valid and same number
dp[i] = 2, valid and different number*/
        int[] dp = new int[N+1];
        int count = 0;
        for (int i = 0; i <= N; i++){
            if(i<10){
                if (i == 0 || i == 1 || i == 8){
                    dp[i] = 1;
                }else if( i ==2 || i == 5 || i == 6 || i ==9){
                    dp[i] = 2;
                    count++;
                }
            }else{
                int a = dp[i/10];
                int b = dp[i%10];
                if ( a ==1 && b ==1){
                    dp[i] = 1;
                }else if( a >= 1 && b >= 1){
                    dp[i] = 2;
                    count++;
                }
            }
        }
        return  count;
        /*Basics:
        0=<i<10

    dp[i] = 1   dp[i/10] ==1 && dp[i%10]==1
           2  dp[i/10] ==1 && dp[i%10]==2 ||  dp[i/10] ==2 && dp[i%10]==1
           0 other*/
    }

}

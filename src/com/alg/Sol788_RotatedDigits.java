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
    }

}

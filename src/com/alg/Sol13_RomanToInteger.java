package com.alg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HAU on 7/26/2017.
 */
public class Sol13_RomanToInteger {
    public static int romanToInt(String s){
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);

        int n = s.length();
        int res = 0;
        for (int i = 0; i < n - 1; i++){
            if (map.get(s.charAt(i) ) < map.get(s.charAt(i+1) )){
                res -= map.get(s.charAt(i));
            }else{
                res += map.get(s.charAt(i));
            }
        }
        res += map.get(s.charAt(n-1));   // last digit special ,added
        return res;
    }
    public static int roman2Int(String s){
        int r;
        r = value(s.charAt(0));
        for (int i = 1; i < s.length();i++){
            r += value(s.charAt(i));
            if (value(s.charAt(i-1)) < value(s.charAt(i))){
                r -= value(s.charAt(i-1)) *2;
            }
        }
        return r;
    }
    private static int value(char s){
        switch (s){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
        }
        return 0;
    }

    public static void main(String[] args) {
        String s1 = "IV";  // -1 + 5
        String s2 = "MCMIV";
        System.out.println(romanToInt(s1));
        System.out.println(romanToInt(s2));
        System.out.println(roman2Int(s1));
        System.out.println(roman2Int(s2));

    }
}

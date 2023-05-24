package com.alg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by HAU on 9/8/2017.
 */

/*
* Given a string, determine if a permutation of the string
* could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.*/
public class Sol266_PalindromeSubstring {
    public static boolean canPermutePalindrome(String s){
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0) + 1);
        }
        int count = 0;
        for (char c : map.keySet()){
            count += map.get(c) % 2;
        }
        return count <= 1;
    }
    public static boolean canPerPalset(String s){
        Set<Character> set = new HashSet<>();
        for (int i = 0 ; i < s.length(); i++){
            if(!set.add(s.charAt(i))){
                set.remove(s.charAt(i));
            }

        }
        return set.size()<=1;
    }

    public static void main(String[] args) {
        String s = "radsdar";
        System.out.println(canPermutePalindrome(s));
        System.out.println(canPerPalset(s));
    }
}

package com.alg;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by HAU on 9/4/2017.
 */
/*
Given a string which consists of lowercase or uppercase letters,
        find the length of the longest palindromes that can be built with those letters.*/
public class Sol409_LongestPalindrome {
    public static int longestPalindrome(String s){
        if ( s == null || s.length() == 0) return 0;
        HashSet<Character> set = new HashSet<>();
        int count = 0;
        for (char c: s.toCharArray()){
            if (set.contains(c)){ // set contains
                set.remove(c);  // set remove
                count++;
            }else{
                set.add(c);
            }
        }
        if (set.isEmpty()) return count*2;
        return count*2 + 1;

    }

    public int longestPalindrome3(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) set.remove(c);
            else set.add(c);
        }

        int odd = set.size();
        return s.length() - (odd == 0 ? 0 : odd - 1);
    }


    public static int longestPalindrome2(String s){
        boolean[] map = new boolean[128];
        int len = 0;
        for (char c : s.toCharArray()) {
            map[c] = !map[c];         // flip on each occurrence, false when seen n*2 times
            if (!map[c]) len+=2;
        }
        if (len < s.length()) len++; // if more than len, atleast one single is present
        return len;

    }

    public static void main(String[] args) {
        String s = "abccccdd";
        System.out.println(longestPalindrome(s));
        System.out.println(longestPalindrome2(s));
    }


}

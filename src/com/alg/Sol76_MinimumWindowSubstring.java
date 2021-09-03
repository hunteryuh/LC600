package com.alg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HAU on 8/16/2017.
 */
/*
Given a string S and a string T, find the minimum window in S which will
contain all the characters in T in complexity O(n).

        For example,
        S = "ADOBECODEBANC"
        T = "ABC"
        Minimum window is "BANC".

        Note:
        If there is no such window in S that covers all characters in T,
        return the empty string "".

        If there are multiple such windows, you are guaranteed that there will
        always be only one unique minimum window in S.*/

    // https://www.jiuzhang.com/problem/minimum-window-substring/
public class Sol76_MinimumWindowSubstring {
    public static  String minWindow(String s, String t){
        // s : source; t: target to find
        if (t.length() > s.length()) return "";
        String result = "";
        // define hashmap target for char counter in t
        HashMap<Character,Integer> target = new HashMap<>();
        for (char c : t.toCharArray()){
            if (target.containsKey(c)){
                target.put(c,target.get(c) + 1);
            } else {
                target.put(c,1);
            }
        }
        // define a second hashmap for char counter in
        HashMap<Character,Integer> map = new HashMap<>();
        int left = 0;
        int minL = s.length() + 1; // has to be bigger than the length
        int count = 0;
        for (int i = 0; i < s.length(); i++){
            char m = s.charAt(i);
            if (target.containsKey(m)){
                if (map.containsKey(m) ){
                    if (map.get(m) < target.get(m)){
                        count++;
                    }
                    map.put(m,map.get(m) + 1);
                }else{
                    map.put(m,1);
                    count++;
                }
            }
            if (count == t.length()){
                // all char in t are found
                char cleft = s.charAt(left);
                while (!map.containsKey(cleft) || map.get(cleft) > target.get(cleft)){
                    if ( map.containsKey(cleft) && map.get(cleft) > target.get(cleft)){
                        map.put(cleft,map.get(cleft) - 1);
                    }
                    cleft = s.charAt(++left);
                }
                if ( i-left+1 < minL){
                    minL = i - left + 1;
                    result = s.substring(left, i+1);
                }

            }
        }
        return result;

    }

    public static String minWindow2(String s, String t) {
        // s : source; t: target to find
        if (s == null || s.length() == 0 || t.length() > s.length()) return "";
        HashMap<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            if (!targetMap.containsKey(c)) {
                targetMap.put(c, 1);
            } else {
                targetMap.put(c, targetMap.get(c) + 1);
            }
        }
        int left = 0;
        int minLength = s.length() + 1;
        int count = 0;
        int minLeft = 0;
        for (int right = 0; right < s.length(); right++) {
            char curr = s.charAt(right);
            if (targetMap.containsKey(curr)) {
                if (targetMap.get(curr) > 0) count++;
                targetMap.put(curr, targetMap.get(curr) - 1); // if s[right] is part of the target string

                while (count == t.length()) {
                    if (right - left + 1 < minLength) {
                        minLength = right - left + 1;
                        minLeft = left;
                    }
                    // move left pointer right, s[left] is the one to remove
                    char leftChar = s.charAt(left);
                    if (targetMap.containsKey(leftChar)) {
                        if (targetMap.get(leftChar) == 0) count--; // if there is no more this char, decrease the count
                        targetMap.put(leftChar, targetMap.get(leftChar) + 1);  // need to count this again after we move left pointer + 1 for the counter to match in the next step
                    }
                    left++;
                }
            }
        }
        if (minLength > s.length()) return "";
        return s.substring(minLeft, minLeft + minLength);

    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANCC";
        String t = "BCA";
        System.out.println(minWindow(s,t)); //BANC
        System.out.println(minWindow2(s,t));
        String s1 = "a";
        String t1 = "a";
        System.out.println(minWindow(s1,t1));
    }

    // redo on 6/19/2021
    public String minWindow3(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        Map<Character, Integer> sMap = new HashMap<>();
        int left = 0;
        int count = 0;
        int length = s.length() + 1;
        int minLeft = 0;
        String res = "";
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (tMap.containsKey(c)) {
                sMap.put(c, sMap.getOrDefault(c, 0) + 1);
                if (sMap.get(c).equals(tMap.get(c))) {
                    count++;
                }
            }

            while (count == tMap.size()) {
                if (right - left + 1 < length) {
                    length = right - left + 1;
                    minLeft = left;
                }
                char cl = s.charAt(left);
                if (tMap.containsKey(cl)) {
                    sMap.put(cl, sMap.get(cl) - 1);
                    if (sMap.get(cl) < tMap.get(cl)) {
                        count--;
                    }
                }
                left++;
            }

        }

        if (length == s.length() + 1)  return "";
        return s.substring(minLeft, minLeft + length);
    }
}

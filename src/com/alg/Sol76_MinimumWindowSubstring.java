package com.alg;

import java.util.HashMap;

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
public class Sol76_MinimumWindowSubstring {
    public static  String minWindow(String s, String t){
        // s : source; t: target to find
        if ( t.length() > s.length()) return "";
        String result = "";
        // define hashmap target for char counter in t
        HashMap<Character,Integer> target = new HashMap<>();
        for (char c : t.toCharArray()){
            if (target.containsKey(c)){
                target.put(c,target.get(c) + 1);
            }else{
                target.put(c,1);
            }
        }
        //define a second hashmap for char counter in
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
                    result = s.substring(left,i+1);
                }

            }
        }
        return result;

    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANCC";
        String t = "BCA";
        //System.out.println(minWindow(s,t));
        String s1 = "a";
        String t1 = "a";
        System.out.println(minWindow(s1,t1));
    }
}

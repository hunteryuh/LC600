package com.alg;

import java.util.HashMap;

/**
 * Created by HAU on 8/5/2017.
 */

/*Given two strings s and t, determine if they are isomorphic.

        Two strings are isomorphic if the characters in s can be replaced to get t.

        All occurrences of a character must be replaced with another character
        while preserving the order of characters.
        No two characters may map to the same character but a character may map to itself.*/
public class Sol205_IsomorphicStrings {
    public static boolean isIsomorphic(String s, String t){
        if (s.length() != t.length()) return false;
        HashMap<Character,Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            char si = s.charAt(i);
            char ti = t.charAt(i);

            if (map.containsKey(si)){
                if (map.get(si) != ti){
                    return  false;
                }
            }else{
                if(map.containsValue(ti)){
                    return false;
                }
                map.put(si,ti);
            }
        }
        return true;
    }

    public static boolean isIsomorph(String s, String t){
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        for ( int i = 0; i < s.length();i++){
            if(m1[s.charAt(i)] != m2[t.charAt(i)]){
                return  false;
            }
            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "foo";
        String t = "abb";
        System.out.println(isIsomorphic(s,t));
        System.out.println(isIsomorph(s,t));
        String s2 = "paper";
        String t2 = "cader";
        System.out.println(isIsomorphic(s2,t2));
        System.out.println(isIsomorph(s2,t2));
    }
}

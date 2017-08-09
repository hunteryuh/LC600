package com.alg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by HAU on 7/24/2017.
 */
public class Sol3_LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring_2(String s) {
        if ( s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int n = s.length();
        int result = 0;
        int i = 0, j = 0;
        Set<Character> set = new HashSet<>();
        while (i < n && j < n){
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                result = Math.max(j-i, result);
            }else{
                set.remove(s.charAt(i++));
            }
        }
        return result;
    }

    public static int lengthOfLongestSubstring(String s) {
        if ( s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int n = s.length();
        int result = 0;


        HashMap<Character,Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < n ; j ++){
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(i, map.get(s.charAt(j)));
            }
            map.put(s.charAt(j), j + 1);
            result = Math.max(result, j + 1 - i);

        }
        return result;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
        String s2 = " abcabcdb";
        System.out.println(lengthOfLongestSubstring(s2));
        String s3 = "bbb";
        System.out.println(lengthOfLongestSubstring(s3));
        String s4 = "tmmuxzt";
        System.out.println(lengthOfLongestSubstring(s4));
    }
}
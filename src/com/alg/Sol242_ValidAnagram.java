package com.alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HAU on 10/8/2017.
 */
/*Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.*/
public class Sol242_ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        //   time : O(n), space: O(n)
        if(s.length() != t.length() ){ //|| s.equals(t)
            return false;
        }
        Map<Character,Integer> counts = new HashMap<>();
        for (int i = 0; i< s.length(); i++){
            Character c = s.charAt(i);
            if (counts.containsKey(c)){
                counts.put(c,counts.get(c) + 1);
            }else{
                counts.put(c,1);
            }
            // one line
            //counts.put(c, counts.getOrDefault(c,0) + 1);
        }
        for (int i = 0; i< t.length(); i++){
            Character ch = t.charAt(i);
            if(!counts.containsKey(ch) || counts.get(ch) == 0){
                //is about to be negative if t contains more ch than that in s
                return false;
            }
            counts.put(ch,counts.get(ch) - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "silent";
        String t = "listen";
        System.out.println(isAnagram(s,t));
        String t2 = "lendsi";
        System.out.println(isAnagram3(s,t2));
        System.out.println(isAnagram3(s,t));
        System.out.println(isAnagram4(s,t));
    }

    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);  // O(n log n) with the sorting
    }

    // method 2 using int array
    public static boolean isAnagram3(String s, String t){
        int[] alpha = new int[26]; //'z'-'a'+1 = 26
        for(char c:s.toCharArray()) alpha[c - 'a']++;
        for(char c:t.toCharArray()) alpha[c - 'a']--;
        for(int i: alpha) if (i!= 0) return false;
        return true;
    }
    // version 3 using array
    // time : O(n), space: O(1)
    public static boolean isAnagram4(String s, String t){
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26]; //'z'-'a'+1 = 26
        for(char c:s.toCharArray()) count[c - 'a']++;
        for(char c:t.toCharArray()) {
            if(count[c-'a'] == 0){
                return false;
            }
            count[c - 'a']--;
        }

        return true;
    }
    // follow up: what if the inputs contain unicode characters?
    // int [256] ( 1k)
    // each unicode： 16 bits, 2 bytes 2^16 = 64k, too large, can use hashtable

}

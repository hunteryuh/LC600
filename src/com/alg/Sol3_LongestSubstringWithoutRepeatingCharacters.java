package com.alg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by HAU on 7/24/2017.
 */
/*Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3.
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.*/
public class Sol3_LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring_2(String s) {
        /*We use HashSet to store the characters in current window [i, j) (j = i initially).
        Then we slide the index jj to the right. If it is not in the HashSet, we slide j further.
        Doing so until s[j] is already in the HashSet. At this point, we found the maximum size of substrings
        without duplicate characters start with index i. If we do this for all i, we get our answer.*/
        if ( s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int n = s.length();
        int result = 0;
        int i = 0, j = 0;
        Set<Character> set = new HashSet<>();
        while (i < n && j < n){
            // try to extend the sliding window [i,j]
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

        /*The reason is that if s[j] have a duplicate in the range [i, j) with index j'​ , we don't need to increase i
         little by little. We can skip all the elements in the range [i, j'] and let i to be j' + 1 directly.*/
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
        System.out.println(lengthOfLongestSubstring3(s));
        System.out.println(lengthOfLongestSubstring(s));
        String s2 = " abcabcdb";
        System.out.println(lengthOfLongestSubstring(s2));
        String s3 = "bbb";
        System.out.println(lengthOfLongestSubstring(s3));
        String s4 = "tmmuxzt";
        System.out.println(lengthOfLongestSubstring(s4));
    }

    // method 3, assume ASCII 128
    public static int lengthOfLongestSubstring3(String s){
        int n = s.length();
        int res = 0;
        int[] index = new int[128];
        for (int j = 0, i = 0; j < n; j++){
            i = Math.max(index[s.charAt(j)],i);
            res = Math.max(res, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return res;
    }
}
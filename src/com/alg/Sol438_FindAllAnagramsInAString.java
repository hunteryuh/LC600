package com.alg;

import java.util.*;

/**
 * Created by HAU on 11/15/2017.
 */
/*Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]*/
public class Sol438_FindAllAnagramsInAString {
    public static  List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if ( s == null || s.length() == 0 || p == null || p.length()==0)
            return list;
        int[] hash = new int[256];
        for (char c: p.toCharArray()){
            hash[c]++;
        }
        int left = 0, right  = 0;
        int count = p.length();
        while (right < s.length()){
            // sliding window
            if(hash[s.charAt(right++)]-- >= 1){
                count--;
                //right++;
            }
            if (count == 0) list.add(left);
            if ( right - left  == p.length() && hash[s.charAt(left++)]++ >= 0){
                count++;
                //left++;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String s = "abcpebcat";
        String p = "acb";
        System.out.println(findAnagrams(s,p));
    }

    // int[] comparison Arrays.equals java 8
    // Arrays.compare(a, b) java9 0 if equal
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] target = new int[26];
        int[] current = new int[26];
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            target[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            current[c - 'a']++;
            if (i >= p.length() - 1) { // enough length
                int startIndex = i - p.length() + 1;
                if (Arrays.equals(current, target)) { // compare two arrays
                    res.add(startIndex);
                }
                // move left pointer + 1
                current[s.charAt(startIndex) - 'a']--;
            }
        }
        return res;
    }

    public List<Integer> findAnagram3(String s, String p) {
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> pMap = new HashMap<>();
        for (char c: p.toCharArray()) {
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            sMap.put(cur, sMap.getOrDefault(cur, 0) + 1);
            if (i + 1 >= p.length()) {
                int index = i + 1 - p.length();
                if (sMap.equals(pMap)) {
                    res.add(index);
                }
                char left = s.charAt(index);
                sMap.put(left, sMap.get(left) - 1);
                // below to remove entries that do not exist so that map comparison works
                if (sMap.get(left) == 0) {
                    sMap.remove(left);
                }
            }
        }
        return res;
    }
}

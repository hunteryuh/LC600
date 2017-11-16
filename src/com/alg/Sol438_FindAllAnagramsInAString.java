package com.alg;

import java.util.ArrayList;
import java.util.List;

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
}

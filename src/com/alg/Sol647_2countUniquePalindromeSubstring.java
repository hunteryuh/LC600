package com.alg;

import java.util.HashSet;

/**
 * Created by HAU on 11/30/2017.
 */
/*“aabaa ” 返回5， 有 [a, b, aba, aa, aabaa], 楼主用的dp，*/
public class Sol647_2countUniquePalindromeSubstring {
    public static int countUniquePalSubstrings(String s) {
        HashSet<String> set = new HashSet<>();
        for ( int i = 0;i < s.length();i++){
            helper(s,i,i,set);
            helper(s,i,i+1,set);
        }
        return set.size();
    }

    private static void helper(String s, int i, int j, HashSet<String> set) {
        while( i>=0 && j<s.length() && s.charAt(i)== s.charAt(j)){
            set.add(s.substring(i,j+1));
            i--;j++;
        }
    }

    public static void main(String[] args) {
        String s = "aabaa";
        System.out.println(countUniquePalSubstrings(s));
        //5
    }

}

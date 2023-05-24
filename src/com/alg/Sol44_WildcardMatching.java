package com.alg;

/**
 * Created by HAU on 7/27/2017.
 */

/*
Implement wildcard pattern matching with support for '?' and '*'.

        '?' Matches any single character.
    '*' Matches any sequence of characters (including the empty sequence).
*/
public class Sol44_WildcardMatching {
    public static boolean isMatch(String s, String p){
        int i = 0, j = 0;
        int ps = -1, pp = -1; // two index pointers
        int ns = s.length(), np = p.length();
        while ( i < ns){  // check ith char in string s
            if ( j < np && ( p.charAt(j) == '?'  || p.charAt(j) == s.charAt(i) )){
                i++;
                j++;
            }else if ( j < np && p.charAt(j) == '*'){
                ps = i;
                pp = j;
                j++;
            }else if ( pp != -1){
                j = pp + 1;
                i = ps + 1;
                ps++;
            }else {
                return false;
            }
        }
        while ( j < np && p.charAt(j) == '*'){
            j++;
        }
        return j == np;
    }

    public static void main(String[] args) {
        String s1 = "baaabcd";
        String p1 = "**ba**cd";
        String p2 = "b*";
        String p3 = "a*";
        System.out.println(isMatch(s1,p1));
        System.out.println(isMatch(s1,p2));
        System.out.println(isMatch(s1,p3));
    }
}

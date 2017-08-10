package com.alg;

/**
 * Created by HAU on 8/9/2017.
 */
/*
Implement regular expression matching with support for '.' and '*'.
'.' Matches any single character.
'*' Matches zero or more of the preceding element.
isMatch("ab", ".*") ? true
isMatch("aab", "c*a*b") ? true
*/
public class Sol10_RegularExpressionMatching {
    public static boolean isMatch(String s, String p) {
        if (p.length() == 0) return s.length() == 0;
        if (p.length() == 1) {
            if (s.length() < 1) return false;
            else if (s.charAt(0) != p.charAt(0) && p.charAt(0) != '.') {
                return false;
            } else {
                return isMatch(s.substring(1), p.substring(1));
            }
        }
        // p.length > 1
        // //case 1: when the second char of p is not '*',
        if (p.charAt(1) != '*') {
            if (s.length() < 1) return false;
            else if (s.charAt(0) != p.charAt(0) && p.charAt(0) != '.') {
                return false;
            } else {
                return isMatch(s.substring(1), p.substring(1));
            }
            ////case 2: when the second char of p is '*', complex case.
        }else{
            // '*' stands for 0 preceding element
            if (isMatch(s,p.substring(2))) return  true;

            // '*' stands for 1 or more preceding element
            int i = 0;
            while ( i < s.length() && ( s.charAt(i) == p.charAt(0) || p.charAt(0) =='.') ){
                if (isMatch(s.substring(i+1), p.substring(2)) ){
                    return true;
                }
                i++;
            }
            return false;

        }
    }
    public static  boolean isMatch_short(String s, String p) {

        if(p.length() == 0)
            return s.length() == 0;

        //p's length 1 is special case
        if(p.length() == 1 || p.charAt(1) != '*'){
            if(s.length() < 1 || (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0)))
                return false;
            return isMatch(s.substring(1), p.substring(1));

        }else{
            int len = s.length();

            int i = -1;
            while(i<len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))){
                if(isMatch(s.substring(i+1), p.substring(2)))
                    return true;
                i++;
            }
            return false;
        }
    }


    public static void main(String[] args) {
/*        String s = "aabc";
        String p = "a*bc";
        //System.out.println(isMatch(s,p));
        assert isMatch(s,p);*/

/*        String s1 = "aa";
        String p1 = "a";
        assert isMatch(s1,p1);
        */
        String s2 = "ab";
        String p2 = ".*c";
        assert isMatch(s2,p2);
    }
}

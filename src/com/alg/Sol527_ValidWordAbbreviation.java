package com.alg;

/**
 * Created by HAU on 8/5/2017.
 */
public class Sol527_ValidWordAbbreviation {

    public static boolean validWordAbb(String word, String abbr){
        int i = 0, j  = 0;
        char[] s = word.toCharArray();
        char[] t = abbr.toCharArray();

        while (i< s.length && j < t.length){
            if (Character.isDigit(t[j])){
                if (t[j] == '0'){
                    return  false;
                }
                int val = 0;
                while ( j < t.length && Character.isDigit(t[j])){
                    val = val * 10 + t[j] - '0';
                    j++;
                }
                i += val;
            }else {
                if (s[i++] != t[j++]){
                    return false;
                }
            }
        }
        return i == s.length && j == t.length;
    }

    public static void main(String[] args) {
        String s = "internationalization";
        String abbr = "i12iz4n";
        System.out.println(validWordAbb(s,abbr));
    }
}

package com.alg;

/**
 * Created by HAU on 8/4/2017.
 */

/*
Write a function to find the longest
common prefix string amongst an array of strings.
*/


public class Sol14_LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs){
        if (strs.length == 0) return "";
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++){
            for (int j = 1; j < strs.length; j++){
                if (i == strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)){
                    return res.toString();
                }
            }
            res.append(strs[0].charAt(i));
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String[] arr = {"apple", "apps","appss","a"};
        System.out.println(longestCommonPrefix(arr));
    }
}

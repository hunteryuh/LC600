package com.alg;

/**
 * Created by HAU on 12/26/2017.
 */
public class Sol0_amz_removeVowel {
    public static String removeVowel(String str){
        String vowels = "aeiouAEIOU";
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < str.length(); i++){
            if (vowels.indexOf(str.charAt(i)) != -1) continue;
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(removeVowel(s));
    }
}

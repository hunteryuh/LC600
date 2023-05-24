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
    public static String removeVowel3(String str){
        String vowels = "aeiouAEIOU";
        String res = str.replaceAll("[aeiouAEIOU]","");
        return res;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(removeVowel(s));
        System.out.println(removeV2(s));
        System.out.println(removeVowel3(s));
    }
    // method2
    public static String removeV2(String str) {
        // corner case
        if (str == null || str.length() == 0) {
            return null;
        }
        String vowels = "AEIOUaeiou";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (vowels.contains(ch + "")) continue;
            sb.append(ch);
        }
        return sb.toString();

    }


}

package com.alg;

/**
 * Created by HAU on 6/12/2017.
 */
/*Given s = "the sky is blue",
        return "blue is sky the".*/
public class Sol151_ReverseWordsInAString {
    public static String reverseWords(String s){
        if (s == null || s.length() == 0)
            return "";
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for ( int i = arr.length - 1; i >= 0; i--){
            if(true)  //!arr[i].equals("")
                sb.append(arr[i]).append(" ");
        }
        //if (sb.length() == 0) return "";
        return sb.length()==0 ? "": sb.substring(0,sb.length() - 1);
    }

    // in-place
    public static void reverseWds(char[] s){
        int i = 0;
        for (int j = 0; j < s.length; j++){
            if (s[j] == ' '){
                helper(s,i,j-1);
                i = j + 1;
            }
        }
        helper(s,i,s.length-1);
        helper(s,0, s.length - 1);

    }

    private static void helper(char[] s, int i, int j) {
        while (i < j){
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;

        }
    }

    public static void main(String[] args) {
        String s = "how old are you";
        System.out.println(reverseWords(s));
        String t = " ";
        System.out.println(reverseWords(t));

        char[] sca = s.toCharArray();
        reverseWds(sca);
        System.out.println(sca);
    }


}

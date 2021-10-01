package com.alg;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by HAU on 6/12/2017.
 */
/*Given s = "the sky is blue",
        return "blue is sky the".*/
public class Sol151_ReverseWordsInAString {
    public static String reverseWords(String s){
        if (s == null || s.length() == 0)
            return "";
        String[] arr = s.trim().split(" +");
        StringBuilder sb = new StringBuilder();

        for ( int i = arr.length - 1; i >= 0; i--){
                sb.append(arr[i]).append(" ");
        }
        //if (sb.length() == 0) return "";
        return sb.length()==0 ? "": sb.substring(0,sb.length() - 1);
    }

    public static String reverseWds(String s){
        //similar as above, use string concatenation
        String res = "";
        String[] words = s.trim().split("\\s+");
        /*private final String REGEX = "\\d"; // a single digit
In this example \d is the regular expression; the extra backslash is required for the code to compile. */
        for (int i = words.length - 1; i >0; i--){
            res += words[i] + " ";
        }
        return res + words[0];
    }
    public static String reverseWs(String s){
        // built in functions used
        String[] words = s.trim().split(" +"); // one or more whitespace " "
        //System.out.println();
        Collections.reverse(Arrays.asList(words)); // Arrays.asList(array) return a list view of the specified array
//        System.out.println(Arrays.deepToString(words));
        return String.join(" ",words);
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
//        System.out.println(reverseWords(s));
        System.out.println(reverseWs(s));
        System.out.println(reverseWords(s));
        String t = " ";
        System.out.println(reverseWords(t));

        char[] sca = s.toCharArray();
        reverseWds(sca);
        System.out.println(sca);
    }

    public String reserveWords(String s) {
        //"ab  cd de"
        String[] strings = s.trim().split(" +");
        int n = strings.length;
        StringBuilder sb = new StringBuilder();
        for (int i = n-1 ; i >= 0; i--) {
            sb.append(strings[i]);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}

package com.alg;

/**
 * Created by HAU on 6/12/2017.
 */
/*Given s = "the sky is blue",
        return "blue is sky the".*/
public class Sol557_ReverseWordsInAStringIII {


    // in-place
    public static String reverseWds(String s){
        char[] arr = s.toCharArray();
        int i = 0;
        for (int j = 0; j < arr.length; j++){
            if (arr[j] == ' '){
                helper(arr,i,j-1);
                i = j + 1;
            }
        }
        helper(arr,i,arr.length-1);
        //System.out.println(arr);

        StringBuilder sb = new StringBuilder();
        for ( char c: arr) {
            sb.append(c);
        }
        return sb.toString();
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

        System.out.println(reverseWds(s));
    }


}

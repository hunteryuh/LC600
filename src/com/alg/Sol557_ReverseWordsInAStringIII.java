package com.alg;

/**
 * Created by HAU on 6/12/2017.
 */
/*Given s = "the sky is blue",
        return "blue is sky the".

        https://leetcode.com/problems/reverse-words-in-a-string-iii/

 */
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

//        System.out.println(reverseWds(s));
        char[] out = s.toCharArray();
        reverseString(out);
        System.out.println(out);
    }

    public static void reverseString(char[] s) {
        int start = 0;


        reverseArray(s, 0, s.length - 1);
        for(int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverseArray(s, start, i - 1);
                start = i + 1;
            }
        }
        reverseArray(s, start, s.length - 1);
    }

    private static void reverseArray(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }


}

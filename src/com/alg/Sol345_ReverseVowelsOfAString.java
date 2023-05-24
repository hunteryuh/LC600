package com.alg;

/**
 * Created by HAU on 12/26/2017.
 */
/*Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".*/
public class Sol345_ReverseVowelsOfAString {
    // only letters
    public static String reverseVowels(String s) {
        int n = s.length();
        int i = 0, j = n - 1;
        char[] arr = s.toCharArray();
        String vowels = "aeiouAEIOU";
        while ( i < j){
            while ( i<n && vowels.indexOf(arr[i]) == -1) i++;
            while ( j>0 && vowels.indexOf(arr[j]) == -1) j--;
            if ( i >= j) break;
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;j--;
        }
        return new String(arr); //
        //String.valueOf(arr);

    }

    public static void main(String[] args) {
        String s = "hello";  // holle
        String t = "leetcode"; // leotcede
        String a = ",.";
        System.out.println(reverseVowels(a));
        System.out.println(reverseVowels(s));
        System.out.println(reverseVowels(t));
    }
}

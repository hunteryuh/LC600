package com.alg;

/**
 * Created by HAU on 7/27/2017.
 */
public class Sol344_ReverseString {
    public static String reverseString(String s){
        StringBuilder res = new StringBuilder(s);
        return res.reverse().toString();
    }
    public static String reverseString2(String s){

        char[] r = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j){
            swap(r,i,j);
            i++;
            j--;
        }
        return new String(r);
    }

    private static void swap(char[] r, int i, int j) {
        char c = r[i];
        r[i] = r[j];
        r[j] = c;
    }

    public static void main(String[] args) {
        String s = "abcde";
        String t = reverseString(s);
        assert  t.equals("edcba");
        //System.out.println(reverseString(s));
       // System.out.println(reverseString2(s));
    }

    // in one part
    public String reverseString3(String s) {
        char[] word = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char temp = word[i];
            word[i] = word[j];
            word[j] = temp;
            i++;
            j--;
        }
        return new String(word);
    }
}

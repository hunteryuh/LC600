package com.alg;

/**
 * Created by HAU on 7/27/2017.
 */
/*Given a string and an integer k, you need to reverse the first k characters
        for every 2k characters counting from the start of the string.
        If there are less than k characters left, reverse all of them.
        If there are less than 2k but
        greater than or equal to k characters, then reverse
        the first k characters and left the other as original.*/
public class Sol541_ReverseStringII {
    public static String reverseStr(String s, int k){

        char[] r = s.toCharArray();
        int n = s.length();
        for (int i = 0; i<n; i +=2*k){
            int j = Math.min(i +k-1,n-1);

            swapK(r,i,j);
        }
        return new String(r);  // use char[] to build a string
    }
    private static void swapK(char[] r, int i, int j) {
        while ( i < j) {
            char c = r[i];
            r[i] = r[j];
            r[j] = c;
            i++;j--;
        }
    }


    public static void main(String[] args) {
        String s = "abcdefghj";
        int k = 3;
        System.out.println(reverseStr(s,k));


    }
}

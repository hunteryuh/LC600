package com.alg;

/**
 * Created by HAU on 7/29/2017.
 */

/*Given a string s consists of upper/lower-case
        alphabets and empty space characters ' ', return
        the length of last word in the string.

        If the last word does not exist, return 0.*/
public class Sol58_LengthofLastWord {
    public static  int lengthOfLastWord(String s){
        if ( s == null || s.length()== 0) return 0;
        //if (s.length())
        int n = s.length();
        int i;
        for (i = n - 1; i>=0;i--){
            if(s.charAt(i) !=' '){
                break;
            }
        }
        int j;
        for (j = i; j>=0;j--){
            if(s.charAt(j) ==' '){
                break;
            }
        }
        return j==0? i:i-j;
    }

    public static void main(String[] args) {
        String s ="hello world  ";
        System.out.println(lengthOfLastWord(s));
        String s2 = " aa b ";
        System.out.println(lengthOfLastWord(s2));
        String s3 = "abcd";
        System.out.println(lengthOfLastWord(s3));
    }
}

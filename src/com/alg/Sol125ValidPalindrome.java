package com.alg;

/**
 * Created by HAU on 5/27/2017.
 */

/*Given a string, determine if it is a palindrome, considering
only alphanumeric characters and ignoring cases.

        For example,
        "A man, a plan, a canal: Panama" is a palindrome.
        "race a car" is not a palindrome.

        Note:
        Have you consider that the string might be empty? This is a good question to ask during an interview.

        For the purpose of this problem, we define empty string as valid palindrome.

        https://leetcode.com/problems/valid-palindrome/

 */
public class Sol125ValidPalindrome {
    public static boolean isPalindrome(String s) {
        //boolean res = false;
        if (s.isEmpty()) return true;
        //String str="";
        StringBuilder str = new StringBuilder();
        for(int i=0;i<s.length();i++){
            char c= s.toLowerCase().charAt(i);
            if( (c>='a' && c<='z') || (c>='0' && c<='9'))
                str.append(c);
        }
        int i = 0;
        int j = str.length()-1;
        while( i<j ){
            if (str.charAt(i) != str.charAt(j)) return false;
            i++;
            j--;
        }

        return true;  // time limit exceeded...
    }

    public boolean isPalindrome0(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) return false;
            i++; j--;
        }
        return true;
    }

    public static boolean isPalindrome1L(String s) {
        if (s.isEmpty()) return true;
        int i = 0;
        int j = s.length()-1;

        while(i<j){
            char c = s.charAt(i);
            char d = s.charAt(j);
            if(!((c>='a' && c<='z')||(c>='A' && c<='Z') || (c>='0' && c<='9') ))
                i++;
            else if( !( (d>='a' && d<='z') ||(d>='A' && d<='Z')|| (d>='0' && d<='9') ))
                j--;
            else {
                if (Character.toLowerCase(c) != Character.toLowerCase(d)) return false;  // key to save time
                // only do converting to lower case when comparison is checked
                else {
                    i++;
                    j--;
                }
            }
        }

        return true; //
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        String t = "race a car";
        System.out.println(isPalindrome1L(s));
        System.out.println(isPalindrome(t));
    }

    public boolean isPalindrome1(String s) {
        StringBuilder builder = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                builder.append(Character.toLowerCase(ch));
            }
        }

        String filteredString = builder.toString();
        String reversedString = builder.reverse().toString();

        return filteredString.equals(reversedString);
    }
}

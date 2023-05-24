package com.alg;

/**
 * Created by HAU on 1/9/2018.
 */
/*Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.*/
public class Sol680_ValidPalindromeII {
    // O(n) time
    public static boolean validPalindromeII(String s) {
        if (s == null || s.length() == 0) return true;
        int n = s.length();
        int i = 0, j = n - 1;
        while ( i < j ){
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s,i+1,j) || isPalindrome(s,i,j-1);
            }
            i++;
            j--;
        }
        return true;
    }


    private static boolean isPalindrome(String s, int i, int j ){
        while( i<j && i >= 0 && j <= s.length() - 1){
            if(s.charAt(i) != s.charAt(j)) return false;
            i++;j--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "aba";
        String s2 = "abca";
        System.out.println(validPalindromeII(s1));
        System.out.println(validPalindromeII(s2));
    }

    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                break;
            }
        }
        if (left >= right) return true;
        if (isPalin(s, left + 1, right) || isPalin(s, left, right - 1)) return true;
        return false;
    }

    private boolean isPalin(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}

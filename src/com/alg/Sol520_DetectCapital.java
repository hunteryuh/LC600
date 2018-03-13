package com.alg;
/*Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital if it has more than one letter, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
Example 1:
Input: "USA"
Output: True
Example 2:
Input: "FlaG"
Output: False*/
public class Sol520_DetectCapital {
    public static boolean detectCapitalUsereg(String word) {
        return word.matches("[A-Z]+|[a-z]+|[A-Z][a-z]+");
    }
    public static boolean detectCapitalUse(String word) {
        //verbose
        int numCap = 0;
        for(int i = 0; i < word.length();i++){
            if(Character.isUpperCase(word.charAt(i))) numCap++;

        }
        if(numCap == 1) return Character.isUpperCase(word.charAt(0));
        return numCap == 0 || numCap == word.length();
    }
    // built-in string method 3
    public static boolean detectCapital(String word){
        return word.equals(word.toLowerCase()) ||
                word.equals(word.toUpperCase()) ||
                Character.isUpperCase(word.charAt(0)) &&
                        word.substring(1).equals(word.substring(1).toLowerCase());
    }

    public static void main(String[] args) {
        String s = "abc";
        String t = "Temp";
        String v = "tP";
        System.out.println(detectCapital(s));
        System.out.println(detectCapital(t));
        System.out.println(detectCapital(v));
    }
}

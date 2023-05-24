package com.alg;

import java.util.HashSet;
import java.util.Set;

/*
A pangram is a sentence where every letter of the English alphabet appears at least once.

Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.



Example 1:

Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
Output: true
Explanation: sentence contains at least one of every letter of the English alphabet.
Example 2:

Input: sentence = "leetcode"
Output: false
 */
public class Sol1832_CheckSentencePangram {
    public static boolean checkIfPangram(String sentence) {
        Set<Character> set = new HashSet<>();
        for (char c: sentence.toCharArray()) {
            set.add(c);
        }
        return set.size() == 26;
    }

    public static void main(String[] args) {
        System.out.println(checkIfPangram("adfdafda"));
        System.out.println(checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
    }

    // https://leetcode.com/problems/check-if-the-sentence-is-pangram/discuss/1164135/Simple-solution-no-setmap
    public static boolean checkIfPangram2(String s) {
        int max = (1 << 26) - 1, temp = 0;  // 26 "1" after any number of "0"s

        for (char c : s.toCharArray()) {
            temp |= 1 << c - 'a';   // the digit becomes 1 if the diff between it and a is the position of digits
            if (temp == max) return true;  // if found 26 "1" can return immediately
        }
        return false;
    }
    //
    public boolean checkIfPangram3(String sentence) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < 26; i++)
        {
            if(sentence.indexOf(alphabet.charAt(i)) < 0) // check the index of all letters in the alphabet
                return false;
        }
        return true;
    }

    public boolean checkIfPangram4(String sentence) {
        for(char i = 'a'; i <= 'z'; i++) {
            if(sentence.indexOf(i) == -1) return false;
        }
        return true;
    }
}

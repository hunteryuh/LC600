package com.alg;
/*
Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.

A string is represented by an array if the array elements concatenated in order forms the string.



Example 1:

Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
Output: true
Explanation:
word1 represents string "ab" + "c" -> "abc"
word2 represents string "a" + "bc" -> "abc"
The strings are the same, so return true.
Example 2:

Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
Output: false
Example 3:

Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
Output: true
 */
public class Sol1662_CheckTwoStringArrays {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        for (String w : word1) {
            sb1.append(w);
        }
        StringBuilder sb2 = new StringBuilder();
        for (String w : word2) {
            sb2.append(w);
        }
        return sb1.toString().equals(sb2.toString());
    }

    public boolean arrayStringsAreEqual2(String[] word1, String[] word2) {
        return String.join("", word1).equals(String.join("", word2));
    }

    // constant space
    public static boolean arrayStringsAreEqual3(String[] word1, String[] word2) {
        int w1 = 0, w2 = 0;
        int p1 = 0, p2 = 0;
        while (w1 < word1.length && w2 < word2.length) {
            String c1 = word1[w1];
            String c2 = word2[w2];
            if (c1.charAt(p1) != c2.charAt(p2)) {
                return false;
            }
            if (p1 == c1.length() - 1) {
                p1 = 0;
                w1++;
            } else {
                p1++;
            }
            if (p2 == c2.length() - 1) {
                p2 = 0;
                w2++;
            } else {
                p2++;
            }
        }
        return w1 == word1.length && w2 == word2.length;
    }

    public static void main(String[] args) {
        String[] word1 = {"ab", "c"};
        String[] word2 = {"a", "bc"};
        System.out.println(arrayStringsAreEqual3(word1, word2));


    }
}

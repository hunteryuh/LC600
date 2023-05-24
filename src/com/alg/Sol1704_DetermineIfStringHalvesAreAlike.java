package com.alg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.

Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.

Return true if a and b are alike. Otherwise, return false.



Example 1:

Input: s = "book"
Output: true
Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
Example 2:

Input: s = "textbook"
Output: false
Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
Notice that the vowel o is counted twice.
 */
public class Sol1704_DetermineIfStringHalvesAreAlike {
    public boolean halvesAreAlike(String s) {
        int len = s.length();  // abcd 4
        String half1 = s.substring(0, len / 2);
        String half2 = s.substring(len / 2);
        int count1 = 0;
        Set<Character> set = Stream.of('a', 'e', 'i', 'o', 'u').collect(Collectors.toSet());
        for (char c: half1.toCharArray()) {
            if (set.contains(Character.toLowerCase(c))) {
                count1++;
            }
        }

        int count2 = 0;
        for (char c: half2.toCharArray()) {
            if (set.contains(Character.toLowerCase(c))) {
                count2++;
            }
        }
        return count1 == count2;


    }

    public boolean halvesAreAlike2(String s) {
        int n = s.length();
        return countVowel(0, n / 2, s) == countVowel(n / 2, n, s);
    }

    private int countVowel(int start, int end, String s) {
        String vowels = "aeiouAEIOU";
        int answer = 0;
        for (int i = start; i < end; i++) {
            if (vowels.indexOf(s.charAt(i)) != -1) {
                answer++;
            }
        }
        return answer;
    }
}

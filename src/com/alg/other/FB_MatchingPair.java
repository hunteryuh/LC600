package com.alg.other;

import java.util.HashSet;
import java.util.Set;

/*
Given two strings s and t of length N, find the maximum number of possible matching pairs in strings s and t after swapping exactly two characters within s.
A swap is switching s[i] and s[j], where s[i] and s[j] denotes the character that is present at the ith and jth index of s, respectively. The matching pairs of the two strings are defined as the number of indices for which s[i] and t[i] are equal.
Note: This means you must swap two characters at different indices.
Signature
int matchingPairs(String s, String t)
Input
s and t are strings of length N
N is between 2 and 1,000,000
Output
Return an integer denoting the maximum number of matching pairs
Example 1
s = "abcd"
t = "adcb"
output = 4
Explanation:
Using 0-based indexing, and with i = 1 and j = 3, s[1] and s[3] can be swapped, making it  "adcb".
Therefore, the number of matching pairs of s and t will be 4.
Example 2
s = "mno"
t = "mno"
output = 1
Explanation:
Two indices have to be swapped, regardless of which two it is, only one letter will remain the same. If i = 0 and j=1, s[0] and s[1] are swapped, making s = "nmo", which shares only "o" with t.
 */
public class FB_MatchingPair {

    public int MatchingPair(String s, String t) {
        int matchCount = 0;
        int n = s.length();
        boolean isDup = false;
        Set<Character> common = new HashSet<>();
        Set<String> misMatch = new HashSet<>();
        for (int i = 0; i < n; i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (sc == tc) {
                matchCount++;
                if (common.contains(sc)) {
                    isDup = true;
                }
                common.add(sc);
            } else {
                misMatch.add(sc + "" + tc);
            }
        }
        if (matchCount == n) {
            return isDup? n : n - 2 ;  // aab  aab;  abc  abc
        }
        if (matchCount == n - 1) {  // abc  abd;  axa; aya ; acb  abb ;    abc  abd
            String mis = String.valueOf(misMatch);
            if (isDup || common.contains(mis.charAt(0)) || common.contains(mis.charAt(1))) {
                return matchCount;
            }
            return matchCount - 1;
        }
        for (String ms : misMatch) {
            if (misMatch.contains(ms.charAt(1) + "" + ms.charAt(0))) {
                return matchCount + 2;    // abcd   abdc
            }
        }

        Set<Character> unMatchedS = new HashSet<>();
        Set<Character> unMatchedT = new HashSet<>();
        for (String ms: misMatch) {
            if (unMatchedS.contains(ms.charAt(1)) || unMatchedT.contains(ms.charAt(0))) {
                return matchCount + 1;    // abcd    adce
            }
            unMatchedS.add(ms.charAt(0));
            unMatchedT.add(ms.charAt(1));
        }

        return matchCount;
    }
}

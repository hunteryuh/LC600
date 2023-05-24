package com.alg;

import java.util.ArrayList;
import java.util.List;

/*
You are given two string arrays words1 and words2.

A string b is a subset of string a if every letter in b occurs in a including multiplicity.

For example, "wrr" is a subset of "warrior" but is not a subset of "world".
A string a from words1 is universal if for every string b in words2, b is a subset of a.

Return an array of all the universal strings in words1. You may return the answer in any order.



Example 1:

Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
Output: ["facebook","google","leetcode"]
Example 2:

Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
Output: ["apple","google","leetcode"]


Constraints:

1 <= words1.length, words2.length <= 104
1 <= words1[i].length, words2[i].length <= 10
words1[i] and words2[i] consist only of lowercase English letters.
All the strings of words1 are unique.
 */
public class Sol916_WordSubsets {
    // https://leetcode.com/problems/word-subsets/solutions/175854/java-c-python-straight-forward/
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] count = new int[26];
        int[] temp;
        for (String b: words2) {
            temp = getCount(b);
            for (int i = 0; i < 26; i++) {
                count[i] = Math.max(count[i], temp[i]);
            }
        }
        List<String> res = new ArrayList<>();
        for (String a: words1) {
            temp = getCount(a);
            int i;
            for (i = 0; i < 26; i++) {
                if (temp[i] < count[i]) {
                    break;
                }
            }
            if (i == 26) {
                res.add(a);
            }
        }
        return res;
    }

    private int[] getCount(String b) {
        int[] res = new int[26];
        for (char c : b.toCharArray()) {
            res[c - 'a']++;
        }
        return res;
    }
}

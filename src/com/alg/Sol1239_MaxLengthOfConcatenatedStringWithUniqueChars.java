package com.alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
You are given an array of strings arr. A string s is formed
by the concatenation of a subsequence of arr that has unique characters.

Return the maximum possible length of s.

A subsequence is an array that can be
derived from another array by deleting some or no elements without changing the order of the remaining elements.



Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All the valid concatenations are:
- ""
- "un"
- "iq"
- "ue"
- "uniq" ("un" + "iq")
- "ique" ("iq" + "ue")
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
Explanation: The only string in arr has all 26 characters.


Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lowercase English letters.
 */
public class Sol1239_MaxLengthOfConcatenatedStringWithUniqueChars {
    // https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/discuss/546637/Java-simple-DFS
    int res = 0;
    public int maxLength(List<String> arr) {

        dfs(arr, 0, "");
        return res;
    }

    private void dfs(List<String> arr, int start, String s) {
        if (isValid(s)) {
            res = Math.max(res, s.length());
        } else {
            return;
        }
        for (int i = start; i < arr.size(); i++) { // start with "start"
            String next = arr.get(i);
            dfs(arr, i + 1, s + next);
        }
    }


    private boolean isValid(String s) {
        char[] chars = s.toCharArray();
        int[] freq = new int[26];
        for(char c: chars) {
            freq[c - 'a']++;
            if (freq[c - 'a'] > 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList(
                "a", "abc", "d", "de", "def");
        Sol1239_MaxLengthOfConcatenatedStringWithUniqueChars ss = new Sol1239_MaxLengthOfConcatenatedStringWithUniqueChars();
        System.out.println(ss.maxLength(list));
    }
}

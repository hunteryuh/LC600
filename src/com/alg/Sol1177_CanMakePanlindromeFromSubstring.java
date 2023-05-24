package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string s, we make queries on substrings of s.

For each query queries[i] = [left, right, k], we may rearrange the substring s[left], ..., s[right], and then choose up to k of them to replace with any lowercase English letter.

If the substring is possible to be a palindrome string after the operations above, the result of the query is true. Otherwise, the result is false.

Return an array answer[], where answer[i] is the result of the i-th query queries[i].

Note that: Each letter is counted individually for replacement so if for example s[left..right] = "aaa", and k = 2, we can only replace two of the letters.  (Also, note that the initial string s is never modified by any query.)



Example :

Input: s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
Output: [true,false,false,true,true]
Explanation:
queries[0] : substring = "d", is palidrome.
queries[1] : substring = "bc", is not palidrome.
queries[2] : substring = "abcd", is not palidrome after replacing only 1 character.
queries[3] : substring = "abcd", could be changed to "abba" which is palidrome. Also this can be changed to "baab" first rearrange it "bacd" then replace "cd" with "ab".
queries[4] : substring = "abcda", could be changed to "abcba" which is palidrome.
 */
public class Sol1177_CanMakePanlindromeFromSubstring {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> res = new ArrayList<>();
        for (int[] query: queries) {
            int start = query[0];
            int end = query[1];
            int rep = query[2];

            res.add(canMake(start, end, rep, s));
        }
        return res;
    }

    // time exceeded...
    private Boolean canMake(int start, int end, int rep, String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.remove(c);
            } else {
                map.put(c, 1);
            }

        }
        int size = map.size();
        if (rep >= size /2) {
            return true;
        }
        return false;

    }

    public static void main(String[] args) {
        String s = "abcda";
        int[][] queires = {{3,3,0}, {1,2,0}, {0,3,1}, {0,4,1}, {0,3,2}};
        Sol1177_CanMakePanlindromeFromSubstring ss  = new Sol1177_CanMakePanlindromeFromSubstring();
        List<Boolean> res = ss.canMakePalindQueries(s, queires);
        System.out.println(res);
    }

    // use prefix sum
    //https://leetcode.com/problems/can-make-palindrome-from-substring/discuss/1003124/Java-Prefix-Sum-O(N)-Solution
    public List<Boolean> canMakePalindQueries(String s, int[][] queries) {
        List<Boolean> res = new ArrayList<>();
        int n = s.length();
        int[][] prefixSum = new int[n+1][4];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j< 26; j++) {
                int curAdd = s.charAt(i-1) - 'a' == j ? 1 : 0;
                prefixSum[i][j] = prefixSum[i-1][j] + curAdd;   // prefix[i][j]  count of letter "j" before index i (excluded in s, i - 1)
            }
        }

//        for (int i = 1; i <= n; i++) {
//            prefixSum[i] = prefixSum[i - 1];  // not working, update prefixSum[i][j] will also update prefixSum[i-1][j], due to the "same address"?
//            int j = s.charAt(i - 1) - 'a';
//            prefixSum[i][j] = prefixSum[i-1][j] + 1;
//        }
        for (int[] query: queries) {
            int odd = 0;
            for (int i = 0; i < 26; i++) {
                int start = query[0];
                int end = query[1];
                if ( (prefixSum[end + 1][i] - prefixSum[start][i]) % 2 == 1 ) {   // to include "end" char, need to use prefixSum[end + 1]
                    odd++;
                }
            }
            int rep = query[2];
            // Similarly if the number of charcters in a string with odd frequency are let's say cnt, we can make cnt/2 changes to get a Palindrome.
            res.add(rep >= odd /2);
        }
        return res;
    }
}

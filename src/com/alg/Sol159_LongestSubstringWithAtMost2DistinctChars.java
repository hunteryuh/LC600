package com.alg;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
Given a string s, return the length of the longest substring that contains at most two distinct characters.



Example 1:

Input: s = "eceba"
Output: 3
Explanation: The substring is "ece" which its length is 3.

Example 2:

Input: s = "ccaabbb"
Output: 5
Explanation: The substring is "aabbb" which its length is 5.



Constraints:

    1 <= s.length <= 105
    s consists of English letters.


 */
public class Sol159_LongestSubstringWithAtMost2DistinctChars {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0 ) return 0;
        if (s.length() <= 2) return s.length();
        int left = 0;
        int right = 0;
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = right; i < s.length(); i++) {
            char cur = s.charAt(i);
            map.put(cur, i);
            if (map.size() > 2) {
                int tobeRemovedIndex = Collections.min(map.values());
                left = tobeRemovedIndex + 1;
                map.remove(s.charAt(tobeRemovedIndex));
            }
            res = Math.max(res, i - left + 1);

        }
        return res;
    }
}

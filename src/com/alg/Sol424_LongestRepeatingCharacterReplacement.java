package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character.
You can perform this operation at most k times.

Return the length of the longest substring
containing the same letter you can get after performing the above operations.



Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.


Constraints:

1 <= s.length <= 105
s consists of only uppercase English letters.
0 <= k <= s.length
 */
public class Sol424_LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            int maxFreq = 0;
            for (int val: map.values()) {
                maxFreq = Math.max(maxFreq, val);
            }
            // 记录每个窗口中替换最多k个字符可以满足条件的最大长度(即区间内除去出现最多频数的字符之后剩下的字符个数<= k)
            // length of the window - the "length" of most frequent char
            // i - left + 1 - maxFreq is the number of replacement needed to make a valid window (with repeating chars)
            while (i - left + 1 - maxFreq > k) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                left++;
            }
            res = Math.max(res, i - left + 1);
        }
        return res;
    }

    // optimize to get maxfreq
    // https://leetcode.com/problems/longest-repeating-character-replacement/solutions/91271/java-12-lines-o-n-sliding-window-solution-with-explanation/
    public int characterReplacement0(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int res = 0;
        int maxFreq = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            maxFreq = Math.max(maxFreq, map.get(c));
            // 记录每个窗口中替换最多k个字符可以满足条件的最大长度(即区间内除去出现最多频数的字符之后剩下的字符个数<= k)
            // length of the window - the "length" of most frequent char
            // i - left + 1 - maxFreq is the number of replacement needed to make a valid window (with repeating chars)
            if (i - left + 1 - maxFreq > k) { // while should be changed to if, as it only run once anyway
                // did not need to decrement maxFreq, although we removed one char in the window as it won't affect the result
                // which can only get larger when maxFreq increases
                /*
                maxFreq may be invalid at some points, but this doesn't matter, because it was valid earlier in the string,
                and all that matters is finding the max window that occurred anywhere in the string. Additionally,
                it will expand if and only if enough repeating characters appear in the window to make it expand.
                So whenever it expands, it's a valid expansion.
                 */
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                left++;
            }
            res = Math.max(res, i - left + 1);
        }
        return res;
    }
}

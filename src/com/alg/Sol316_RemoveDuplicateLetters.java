package com.alg;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Given a string s, remove duplicate letters so that every letter appears once and only once.
You must make sure your result is the smallest in lexicographical order among all possible results.



Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"


Constraints:

1 <= s.length <= 104
s consists of lowercase English letters.


Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 */
public class Sol316_RemoveDuplicateLetters {
    // https://leetcode.com/problems/remove-duplicate-letters/editorial/
    public String removeDuplicateLetters(String s) {
        // find pos - the index of the leftmost letter in our solution
        // we create a counter and end the iteration once the suffix doesn't have each unique character
        // pos will be the index of the smallest character we encounter before the iteration ends
        int[] cnt = new int[26];
        int pos = 0;
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            if (--cnt[s.charAt(i) - 'a'] == 0) break;
        }
        System.out.println(Arrays.toString(cnt));
        // our answer is the leftmost letter plus the recursive call on the remainder of the string
        // note that we have to get rid of further occurrences of s[pos] to ensure that there are no duplicates
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }

    public static void main(String[] args) {
        Sol316_RemoveDuplicateLetters ss = new Sol316_RemoveDuplicateLetters();
        String s = "cbab";
        String ans = ss.removeDuplicateLetters(s);
        System.out.println(ans);

        ss.smallestSubsequence(s);
    }

    // time : O(n)
    // space: O(1)
    public String smallestSubsequence(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        Set<Character> set = new HashSet<>();
        Map<Character, Integer> lastOccurrence = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence.put(s.charAt(i), i);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!set.contains(c)) {
                // compare peek element in the stack to the current c
                // exists?
                // exists, but larger than current c, need to remove
                // if it appears later
                while (!stack.isEmpty() && c < stack.peek() && lastOccurrence.get(stack.peek()) > i) {
//                    set.remove(stack.pop());
                    set.remove(stack.peek());
                    stack.pop();
                }
                set.add(c);
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();

//        System.out.println(String.valueOf(stack));
//        for (char c: stack) {
//            sb.append(c);
//        }
//        return sb.toString();
    }
}

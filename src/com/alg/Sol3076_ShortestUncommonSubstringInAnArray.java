package com.alg;

import com.alg.other.AP_DesignHashMap;

import java.util.*;

/*
You are given an array arr of size n consisting of non-empty strings.

Find a string array answer of size n such that:

answer[i] is the shortest
substring
 of arr[i] that does not occur as a substring in any other string in arr.
 If multiple such substrings exist, answer[i] should be the
lexicographically smallest
. And if no such substring exists, answer[i] should be an empty string.
Return the array answer.



Example 1:

Input: arr = ["cab","ad","bad","c"]
Output: ["ab","","ba",""]
Explanation: We have the following:
- For the string "cab", the shortest substring that does not occur in any other string is either "ca" or "ab", we choose the lexicographically smaller substring, which is "ab".
- For the string "ad", there is no substring that does not occur in any other string.
- For the string "bad", the shortest substring that does not occur in any other string is "ba".
- For the string "c", there is no substring that does not occur in any other string.
Example 2:

Input: arr = ["abc","bcd","abcd"]
Output: ["","","abcd"]
Explanation: We have the following:
- For the string "abc", there is no substring that does not occur in any other string.
- For the string "bcd", there is no substring that does not occur in any other string.
- For the string "abcd", the shortest substring that does not occur in any other string is "abcd".


Constraints:

n == arr.length
2 <= n <= 100
1 <= arr[i].length <= 20
arr[i] consists only of lowercase English letters.
 */
public class Sol3076_ShortestUncommonSubstringInAnArray {
    // https://leetcode.com/problems/shortest-uncommon-substring-in-an-array/solutions/5712400/easy-to-understand-no-fancy-stuff-pure-optimization/
    // time: O(n * (m * m + mlogm)), n arr size; m is average length of all strings
    public String[] shortestSubstrings(String[] arr) {
        String[] res = new String[arr.length];
        Map<String, Integer> countMap = new HashMap<>();
        buildMap(arr, countMap);

        for (int i = 0; i < arr.length; i++) {
            String str = arr[i];
            List<String> candidates = new ArrayList<>();
            boolean further = true;
            for (int sublen = 1; sublen <= str.length() && further; sublen++) {
                for (int j = 0; j <= str.length() - sublen; j++) {
                    String substr = str.substring(j, j + sublen);
                    if (countMap.get(substr) == 1) {
                        candidates.add(substr);
                        further = false;
                    }
                }
            }
            if (candidates.isEmpty()) {
                res[i] = "";
            } else if (candidates.size() == 1) {
                res[i] = candidates.get(0);
            } else {
                Collections.sort(candidates); // mlogm
                res[i] = candidates.get(0);
            }
        }
        return res;

    }

    private Map<String, Integer> buildMap(String[] arr, Map<String, Integer> map) {
        for (String ss: arr) {
            // Duplicates within ith string should be counted once
            // (Example : "aa")
            // a should be counted once in the hashmap
            Set<String> set = new HashSet<>();
            for (int i = 0; i < ss.length(); i++) {
                for (int j = i + 1; j <= ss.length(); j++) {
                    set.add(ss.substring(i, j));// add to set for each substring
                }
            }
            for (String substring: set) {
                map.put(substring, map.getOrDefault(substring, 0) + 1);
            }
        }
        return map;

    }

    // trie + hashset
    class Trie {
        Trie[] children;
        Map<Character, Trie> childrenMap;
        Set<Integer> fromSet;
        Trie() {
            this.children = new Trie[26];
            this.childrenMap =  new HashMap<>();
            this.fromSet = new HashSet<>();
        }
    }
    public void insert(String s, int from, Trie root) {
        // why build all substrings start from i from root
        for (int i = 0; i < s.length(); i++) {
            Trie node = root;
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if (node.childrenMap.containsKey(c)) {
                    node.childrenMap.put(c, new Trie());
                }
                node = node.childrenMap.get(c);
                node.fromSet.add(from);
            }
        }
    }
    public String[] shortestSubstrings2(String[] arr) {
        Trie trie = new Trie();
        for (int i = 0; i < arr.length; i++) {
            insert(arr[i], i, trie);
        }
        for (int i = 0; i < arr.length; i++) {
            String min = null;
            for (int j = 0; j < arr[i].length(); j++) {
                String best = sub(arr[i], j, trie, i);
                if (best == null) {
                    continue;
                }
                if (min == null || best.length() < min.length()) {
                    min = best;
                } else if (best.length() == min.length() && best.compareTo(min) < 0) {
                    min = best;
                }
            }
            arr[i] = min == null? "" : min;
        }
        return arr;
    }

    private String sub(String word, int start, Trie node, int fromId) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < word.length(); i++) {
            node = node.childrenMap.get(word.charAt(i));
            sb.append(word.charAt(i));
            if (node.fromSet.size() == 1 && node.fromSet.contains(fromId)) {
                return sb.toString();
            }
        }
        return null;
    }
}

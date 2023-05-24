package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted
lexicographically by the rules of this new language.

Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.

A string s is lexicographically smaller than a string t if at the first letter where they differ,
the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same,
then s is smaller if and only if s.length < t.length.



Example 1:

Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"

Example 2:

Input: words = ["z","x"]
Output: "zx"

Example 3:

Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".



Constraints:

    1 <= words.length <= 100
    1 <= words[i].length <= 100
    words[i] consists of only lowercase English letters.


 */
public class Sol269_AlienDictionary {
    // https://leetcode.com/problems/alien-dictionary/solution/
    public String alienOrder(String[] words) {
//        Set<Character> set = new HashSet<>();// cannot save pres for the cur char
        Map<Character, Integer> indegrees = new HashMap<>();
        // get all chars in the set
        // graph adjacency list
        Map<Character, List<Character>> graph = new HashMap<>();
        for (String word: words) {
            for (char c : word.toCharArray()) {
                indegrees.put(c, 0);
                graph.put(c, new ArrayList<>());
            }
        }
        // build the graph with adjacency list
        // Find the first non match and insert the corresponding relation.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i+1];
            // Check that word2 is not a prefix of word1. "abc"  "ab"
            if (word2.length() < word1.length() && word1.startsWith(word2)) {
                return "";
            }
            int len = Math.min(word1.length(), word2.length());
            for (int j = 0; j < len; j++) {
                char pre = word1.charAt(j);
                char cur = word2.charAt(j);
                if (pre != cur) {
                    graph.get(pre).add(cur);
                    indegrees.put(cur, indegrees.get(cur) + 1);
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        // start bfs, breadth-first-search
        Queue<Character> queue = new LinkedList<>();
        // how to build indegrees map?
        for (char c : indegrees.keySet()) {
            if (indegrees.get(c) == 0) {
                queue.offer(c);
                sb.append(c);
            }
        }
        System.out.println(sb);
        while (!queue.isEmpty()) {
            char c = queue.poll();
            // sb.append(c); works as well if line 104 is at here.
            for (char next: graph.get(c)) {
                indegrees.put(next, indegrees.get(next) - 1);
                if (indegrees.get(next) == 0) {
                    queue.offer(next);
                    sb.append(next);
                }
            }
        }

//        System.out.println(sb.toString());
        if (sb.length() == indegrees.size()) {
            return sb.toString();
        }
        return "";
    }

    public static void main(String[] args) {
        String[] strings = {"x", "z", "x"};
        Sol269_AlienDictionary ss = new Sol269_AlienDictionary();
        ss.alienOrder(strings);
    }
}

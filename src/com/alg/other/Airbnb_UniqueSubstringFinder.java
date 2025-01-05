package com.alg.other;

import com.alg.TreeNode;

import java.util.*;
// https://www.1point3acres.com/bbs/thread-1099312-1-1.html
/*
Given a list of strings, return a map where the values are the
smallest unique substring of each string (compared to the rest of the strings in the list).
The idea is that typing in the map values will auto complete the map keys.
 */
public class Airbnb_UniqueSubstringFinder {
    public static void main(String[] args) {
        List<String> input = Arrays.asList(
                "The Phantom Menace",
                "Attack of the Clones",
                "Revenge of the Sith",
                "A New Hope",
                "The Empire Strikes Back",
                "The Return of the Jedi",
                "The Force Awakens",
                "The Last Jedi"
        );

        /*
        output = {
  'The Phantom Menace': 'to',
  'Attack of the Clones': 'tt',
  'Revenge of the Sith': 'v',
  'A New Hope': 'ho',
  'The Empire Strikes Back': 'b',
  'The Return of the Jedi': 'u',
  'The Force Awakens': 'aw',
  'The Last Jedi': 'tj',
}   
         */

        Airbnb_UniqueSubstringFinder aa = new Airbnb_UniqueSubstringFinder();

        Map<String, String> result = aa.findUniqueSubstrings0(input);
        System.out.println(result);
    }

    // not working, check leetcode 3076
    private Map<String, String> findUniqueSubstrings0(List<String> strings) {
        Map<String, String> uniqueSubstrings = new HashMap<>();

        for (String str : strings) {
            String uniqueSubstring = findSmallestUniqueSubstring(str, strings);
            uniqueSubstrings.put(str, uniqueSubstring);
        }

        return uniqueSubstrings;
    }

    private String findSmallestUniqueSubstring(String str, List<String> strings) {
        Set<String> existingSubstrings = new HashSet<>(strings);
        for (int len = 1; len <= str.length(); len++) {
            for (int start = 0; start <= str.length() - len; start++) {
                String substring = str.substring(start, start + len);
                if (!existingSubstrings.contains(substring) || substring.equals(str)) {
                    return substring;
                }
            }
        }
        return ""; // This should never happen if input is valid
    }

    class TrieNode {
        Map<Character, TrieNode> childrenMap;
        int count;
        TrieNode() {
            childrenMap = new HashMap<>();
            count = 0;
        }
    }
    private Map<String, String> findUniqueSubstrings(List<String> strings) {
        TrieNode root = new TrieNode();
        Map<String, Integer> countMap = new HashMap<>();

        for (String s : strings) {
            addToTrie(s, root);
        }
        Map<String, String> uniqueSubStringResult = new HashMap<>();
        for (String s: strings) {
            String uniqueS = findSmallestSubString(s, root);
            uniqueSubStringResult.put(s, uniqueS);
        }
        return uniqueSubStringResult;
    }

    private String findSmallestSubString(String s, TrieNode root) {
        TrieNode node = root;
        String res = s;
        StringBuilder sb = new StringBuilder();
        for (char c: s.toCharArray()) {
            sb.append(c);
            node = node.childrenMap.get(c);
            if (node != null) {
                if (node.count == 1) {
                    if (sb.length() < res.length()) {
                        res = sb.toString();
                    }
                } else {

                }
            }
        }
        return s; // fallback, should not happen if input is valid
    }
    private void addToTrie(String s, TrieNode root) {
        TrieNode node = root;
        for (char c: s.toCharArray()) {
            node.childrenMap.putIfAbsent(c, new TrieNode());
            node = node.childrenMap.get(c);
            node.count++;
        }
    }
}

package com.alg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HAU on 8/4/2017.
 */

/*
Write a function to find the longest
common prefix string amongst an array of strings.

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.



Constraints:

    1 <= strs.length <= 200
    0 <= strs[i].length <= 200
    strs[i] consists of only lowercase English letters.

*/


public class Sol14_LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        // vertical scanning
        //Time complexity : O(S) , where S is the sum of all characters in all strings.
        //Even though the worst case is still the same as Approach #1, in the best case there are at most
        // n*minLenn∗minLen comparisons where minLenminLen is the length of the shortest string in the array.
        if (strs.length == 0) return "";
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++){
            for (int j = 1; j < strs.length; j++){
                if (i == strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)){
                    return res.toString();
                }
            }
            res.append(strs[0].charAt(i));
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String[] arr = {"apple", "apps","appss","apt"};
        //System.out.println(longestCommonPrefix(arr));
        // output:  ap
//        System.out.println(longestCommonPrefix2(arr));

        Sol14_LongestCommonPrefix ss = new Sol14_LongestCommonPrefix();
        String[] input = {"ab", "a"};
        System.out.println(ss.longestCommonPrefixWithTrie(input));
    }
    public static String longestCommonPrefix2(String[] strs) {
        // horizontal scanning
        // Time complexity : O(S) , where S is the sum of all characters in all strings.
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                //String indexOf(String str) : This method returns the index within this
                // string of the first occurrence of the specified substring.
                //If it does not occur as a substring, -1 is returned. and -1 != 0
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    // worked as of 11/25/2024
    public String longestCommonPrefixWithTrie(String[] strs){
        if (strs.length == 1) return strs[0];
        int n = strs.length;
        TrieNode root = new TrieNode();
        for (String word: strs) {
            addWord(word, root);
        }
        return searchLCP(strs[0], root, n);
    }

    private String searchLCP(String word, TrieNode root, int n) {
        TrieNode node = root;
        StringBuilder sb  = new StringBuilder();
        for (char c: word.toCharArray()) {
            if (node.childrenMap.get(c).count == n) {
                node = node.childrenMap.get(c);
                sb.append(c);
            } else {
                return sb.toString();
            }
        }
        return sb.toString();
    }

    private void addWord(String word, TrieNode root) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            node.childrenMap.putIfAbsent(c, new TrieNode());
            node = node.childrenMap.get(c);
            node.count++;
        }
        node.isWord = true;
    }

    class TrieNode{
        Map<Character, TrieNode> childrenMap;
        int count;
        boolean isWord;
        TrieNode() {
            childrenMap = new HashMap<>();
            isWord = false;
            count = 0;
        }
    }
}

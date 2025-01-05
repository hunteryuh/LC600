package com.alg.dp;

import java.util.*;

/*
Given a string s and a dictionary of strings wordDict,
add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.



Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]

Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []



Constraints:

    1 <= s.length <= 20
    1 <= wordDict.length <= 1000
    1 <= wordDict[i].length <= 10
    s and wordDict[i] consist of only lowercase English letters.
    All the strings of wordDict are unique.


 */
public class Sol140_WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<Integer, List<String>> memo = new HashMap<>(); // startIndex, result list
        Set<String> wordDic = new HashSet<>(wordDict);
        return dfs(s, 0, wordDic, memo);
    }

    private List<String> dfs(String s, int start, Set<String> wordDic, Map<Integer, List<String>> memo) {
        if (memo.containsKey(start)) {
            return memo.get(start);
        }
        List<String> result = new ArrayList<>();
        if (start == s.length()) {
            result.add("");
            return result;
        }

        String curr = s.substring(start);
        for (String word: wordDic) {
            if (curr.startsWith(word)) {
                List<String> subList = dfs(s, start + word.length(), wordDic, memo);
                for (String sub: subList) {
                    result.add(word + (sub.isEmpty() ? "": " ") + sub);
                }
            }
        }
        memo.put(start, result);
        return result;
    }

    public List<String> wordBreak2(String s, Set<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
    }

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {
        if (map.containsKey(s))
            return map.get(s);

        LinkedList<String>res = new LinkedList<String>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }

    public static void main(String[] args) {
        Sol140_WordBreakII ss = new Sol140_WordBreakII();
        List<String> wordDict = Arrays.asList("cat","cats","and","sand","dog");
        String s = "catsanddog";
//        List<String> res = ss.wordBreak2(s, new HashSet<>(wordDict));
        List<String> r2 = ss.wordBreak6(s, wordDict);
        System.out.println(r2);
    }

    // trie optimization
    // https://leetcode.com/problems/word-break-ii/solutions/1325635/trie-dfs-java/
    TrieItem root = new TrieItem();
    public List<String> wordBreak3(String s, List<String> wordDict) {
        for(String word : wordDict) {
            addWord(word, root);
        }
        List<String> res = new ArrayList<>();
        List<String> current = new ArrayList<>();
        dfs(s, 0, root, current, res);
        return res;
    }
    private void dfs(String s, int index, TrieItem node, List<String> current, List<String> res) {
        if(index == s.length()) {
            if(node == root) {
                res.add(String.join(" ", current));
            }
            return;
        }
        if (node == null) return;//invalid
        TrieItem trie = node.children[s.charAt(index) - 'a'];
        if(trie != null && trie.isWord) {
            current.add(trie.word);
            dfs(s, index + 1, root, current, res); // find a word, search from the trie root
            current.remove(current.size() - 1);
        }
        dfs(s, index + 1, trie, current, res); // dfs for the next char which is not end of word
    }
    private void addWord(String word, TrieItem root) {
        TrieItem ptr = root;
        for(int i = 0; i < word.length(); ++i) {
            int idx = word.charAt(i) - 'a';
            if (ptr.children[idx] == null) ptr.children[idx] = new TrieItem();
            ptr = ptr.children[idx];
        }
        ptr.isWord = true;
        ptr.word = word;
    }

    class TrieItem {
        boolean isWord;
        String word;
        TrieItem[] children;
        TrieItem() {
            children = new TrieItem[26];
        }
    }

    // trie solution 2
    class TrieNode {

        boolean isEnd;
        TrieNode[] children;

        TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }
    }

    class Trie {

        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }
    }

    public List<String> wordBreak6(String s, List<String> wordDict) {
        // Build the Trie from the word dictionary
        Trie trie = new Trie();
        for (String word : wordDict) {
            trie.insert(word);
        }

        // Map to store results of subproblems
        Map<Integer, List<String>> dp = new HashMap<>();

        // Iterate from the end of the string to the beginning
        for (int startIdx = s.length(); startIdx >= 0; startIdx--) {
            // List to store valid sentences starting from startIdx
            List<String> validSentences = new ArrayList<>();

            // Initialize current node to the root of the trie
            TrieNode currentNode = trie.root;

            // Iterate from startIdx to the end of the string
            for (int endIdx = startIdx; endIdx < s.length(); endIdx++) {
                char c = s.charAt(endIdx);
                int index = c - 'a';

                // Check if the current character exists in the trie
                if (currentNode.children[index] == null) {
                    break;
                }

                // Move to the next node in the trie
                currentNode = currentNode.children[index];

                // Check if we have found a valid word
                if (currentNode.isEnd) {
                    String currentWord = s.substring(startIdx, endIdx + 1);

                    // If it's the last word, add it as a valid sentence
                    if (endIdx == s.length() - 1) {
                        validSentences.add(currentWord);
                    } else {
                        // If it's not the last word, append it to each sentence formed by the remaining substring
                        List<String> sentencesFromNextIndex = dp.get(endIdx + 1);
                        for (String sentence : sentencesFromNextIndex) {
                            validSentences.add(currentWord + " " + sentence);
                        }
                    }
                }
            }

            // Store the valid sentences in dp
            dp.put(startIdx, validSentences);
        }
        System.out.println(dp);

        // Return the sentences formed from the entire string
        return dp.getOrDefault(0, new ArrayList<>());
    }


}

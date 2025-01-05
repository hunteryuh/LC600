package com.alg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by HAU on 8/16/2017.
 */

/*In English, we have a concept called root, which can be followed by some other words to form another longer word
        - let's call this word successor. For example, the root an, followed by other, which can form another word another.

        Now, given a dictionary consisting of many roots and a sentence.
        You need to replace all the successor in the sentence with the root forming it.
        If a successor has many roots can form it, replace it with the root with the shortest length.

        You need to output the sentence after the replacement.

        \s	Matches the whitespace. Equivalent to [\t\n\r\f].*/

//solution 0: can use Trie
    // solution without using Trie below
public class Sol648_replaceWords {
    public static String replaceWords(List<String> dict, String sentence){
        if (dict == null || dict.size() == 0) return sentence;
        Set<String> set = new HashSet<>();
        set.addAll(dict);

        StringBuilder sb  = new StringBuilder();
        String[] words = sentence.split("\\s+");
        //String[] words = sentence.split(" ");

        for (int i = 0; i < words.length; i++){
            String prefix = "";
            for (int j = 0; j < words[i].length();j++){
                prefix = words[i].substring(0,j+1);
                if (set.contains(prefix)) {
                    break;
                }

            }
            sb.append(" " + prefix);
        }
        return sb.deleteCharAt(0).toString();
    }

    // https://leetcode.com/problems/replace-words/solutions/5271729/interview-approach-brute-force-to-optimised-approach/?envType=company&envId=tiktok&favoriteSlug=tiktok-thirty-days
    public String replaceWords2(List<String> dictionary, String sentence) {
        Set<String> set = new HashSet<>(dictionary);
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean found = false;
            for (int j = 1; j <= word.length(); j++) {
                String prefix = word.substring(0, j);
                if (set.contains(prefix)) {
                    sb.append(prefix);
                    found = true;
                    break;
                }
            }
            if (!found) {
                sb.append(word);
            }
            if (i != words.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    // trie solution
    public String replaceWords3(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for(String dict: dictionary) {
            trie.insert(dict);
        }
        String[] tokens = sentence.split(" ");
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < tokens.length;i++) {
            String token = tokens[i];
            String prefix = trie.findShortestPrefix(token);
            sb.append(prefix);
            if (i < tokens.length-1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    class Trie {

        class TrieNode {
            TrieNode[] children;
            boolean isEndOfWord;

            TrieNode() {
                children = new TrieNode[26];
                isEndOfWord = false;
            }
        }

        private TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (curr.children[index] == null) {
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }
            curr.isEndOfWord = true;
        }

        public boolean search(String word) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (curr.children[index] == null) {
                    return false;
                }
                curr = curr.children[index];
            }
            return curr.isEndOfWord;
        }

        public boolean startsWith(String prefix) {
            TrieNode curr = root;
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (curr.children[index] == null) {
                    return false;
                }
                curr = curr.children[index];
            }
            return true;
        }

        public String findShortestPrefix(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null) {
                    return word;
                }
                node = node.children[index];
                if (node.isEndOfWord) {
                    return word.substring(0, i + 1);
                }
            }
            return word;
        }
    }

    public static void main(String[] args) {
        String sentence = "the cattle was rattled by the battery";
        List<String> dict = Arrays.asList("cat", "bat", "rat");
        System.out.println(replaceWords(dict,sentence));
    }

}

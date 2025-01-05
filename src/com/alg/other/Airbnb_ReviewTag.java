package com.alg.other;

import java.util.HashMap;
import java.util.Map;
// https://leetcode.com/discuss/interview-question/430362/airbnb-phone-screen-tag-words
public class Airbnb_ReviewTag {
    public String addTagsInReview(String review, Map<String, String> tags) {
        // build trie
        TrieNode root = new TrieNode();
        for (String tag: tags.keySet()) {
            add(tag, root);
        }
        StringBuilder result = new StringBuilder();
        char[] ip = review.toCharArray();
        int i = 0;
        while (i < ip.length) {
            int j = i + 1;
            boolean found = false;
            boolean appended = false;
            while (j < ip.length) {
                String prefix = review.substring(i, j).toLowerCase();
                if (hasPrefix(prefix, root)) {
                    j++;
                    found = true;
                } else {
                    break;
                }
            }
            while (j > i && found) {
                String word = review.substring(i, j).toLowerCase();
                if (hasWord(word, root)
                        && (j + 1 == ip.length || !Character.isLetterOrDigit(review.charAt(j)))
                        && i > 0 && !Character.isLetterOrDigit(review.charAt(i - 1))) {
                    // make sure "word" is not a prefix of a longer word in the review text
                    // make sure "word" is not a suffix of a longer word in the review text
                   String val = tags.get(word);
                   result.append("[" + val + "]");
                   result.append("{" + review.substring(i, j) + "}");
                   i = j;
                   appended = true;
                   break;
                } else {
                    j--;
                }
            }
            if (!appended) {
                result.append(ip[i]);
                i++;
            }
        }
        return result.toString();
    }
    // what if the tag word is a prefix of a word in the review text,
    // "sand is there", thus "sand" will be replaced as [person]{san}d

    private boolean hasWord(String word, TrieNode root) {
        TrieNode cur = root;
        for (char c: word.toCharArray()) {
            if (cur.children.get(c) == null) {
                return false;
            }
            cur = cur.children.get(c);
        }
        return cur.isEnd;
    }
    private boolean hasPrefix(String prefix, TrieNode root) {
        TrieNode cur = root;
        for (char c: prefix.toCharArray()) {
            if (cur.children.get(c) == null) {
                return false;
            }
            cur = cur.children.get(c);
        }
        return true;
    }
    private void add(String word, TrieNode root) {
        TrieNode cur = root;
        for (char c: word.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode());
            }
            cur = cur.children.get(c);
        }
        cur.isEnd = true;
    }


    class TrieNode {
        Map<Character, TrieNode> children;
//        TrieNode[] children;  // change to Map to accommodate space in the tag
        boolean isEnd;
        TrieNode() {
            children = new HashMap<>();
            isEnd = false;
        }
    }

    public static void main(String[] args) {
        testCase2();
    }

    private void testCase1() {
        Map<String, String> tags = new HashMap<>();
        tags.put("san francisco", "city");
        tags.put("airbnb", "business");
        String review = "I visited San Francisco for work and stayed at Airbnb. I really loved the city and the home where I stayed.";
        Airbnb_ReviewTag aa = new Airbnb_ReviewTag();
        System.out.println(aa.addTagsInReview(review, tags));
    }

    private static void testCase2() {
        Map<String, String> tags = new HashMap<>();
        tags.put("san francisco", "city");
        tags.put("airbnb", "business");
        tags.put("san", "person");
        tags.put("francisco", "person");
        tags.put("city", "location");
        String review = "5san I travelled to San Francisco for work and stayed at Airbnb. I really loved the city and the home where I stayed. I stayed with San and Francisco. They both were really good and san's hospitality was outstanding.";
        Airbnb_ReviewTag aa = new Airbnb_ReviewTag();
        System.out.println(aa.addTagsInReview(review, tags));
    }

    // approach 2 from perplexity
    public class ReviewHighlighterTrie {
        class TrieNode {
            Map<Character, TrieNode> children = new HashMap<>();
            String tag = null;
            boolean isEndOfWord = false;
        }

        class Trie {
            TrieNode root = new TrieNode();

            void insert(String word, String tag) {
                TrieNode current = root;
                for (char c : word.toLowerCase().toCharArray()) {
                    current = current.children.computeIfAbsent(c, k -> new TrieNode());
                }
                current.isEndOfWord = true;
                current.tag = tag;
            }
        }

        public String highlightReview(String review, Map<String, String> tags) {
            Trie trie = new Trie();
            for (Map.Entry<String, String> entry : tags.entrySet()) {
                trie.insert(entry.getKey(), entry.getValue());
            }

            StringBuilder result = new StringBuilder();
            int i = 0;
            while (i < review.length()) {
                int longestMatch = findLongestMatch(review, i, trie.root);
                if (longestMatch > 0) {
                    String matchedWord = review.substring(i, i + longestMatch);
                    String tag = findTag(trie.root, matchedWord.toLowerCase());
                    result.append("[").append(tag).append("]{").append(matchedWord).append("}");
                    i += longestMatch;
                } else {
                    result.append(review.charAt(i));
                    i++;
                }
            }

            return result.toString();
        }

        private int findLongestMatch(String review, int start, TrieNode root) {
            TrieNode current = root;
            int longestMatch = 0;
            int i = start;
            int lastMatchIndex = -1;

            while (i < review.length()) {
                char c = Character.toLowerCase(review.charAt(i));
                if (!current.children.containsKey(c)) {
                    break;
                }
                current = current.children.get(c);
                if (current.isEndOfWord && (i == review.length() - 1 || !Character.isLetterOrDigit(review.charAt(i + 1)))) {
                    longestMatch = i - start + 1;
                    lastMatchIndex = i;
                }
                i++;
            }

            return (lastMatchIndex == -1 || (start > 0 && Character.isLetterOrDigit(review.charAt(start - 1)))) ? 0 : longestMatch;
        }

        private String findTag(TrieNode root, String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    return null;
                }
                current = current.children.get(c);
            }
            return current.tag;
        }
    }


}

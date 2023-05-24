package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/*
You are given an array of strings products and a string searchWord.

Design a system that suggests at most three product names from products after each character of searchWord is typed.
Suggested products should have common prefix with searchWord.
If there are more than three products with a common prefix return the three lexicographically minimums products.

Return a list of lists of the suggested products after each character of searchWord is typed.



Example 1:

Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"].
After typing mou, mous and mouse the system suggests ["mouse","mousepad"].

Example 2:

Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
Explanation: The only word "havana" will be always suggested while typing the search word.



Constraints:

    1 <= products.length <= 1000
    1 <= products[i].length <= 3000
    1 <= sum(products[i].length) <= 2 * 104
    All the strings of products are unique.
    products[i] consists of lowercase English letters.
    1 <= searchWord.length <= 1000
    searchWord consists of lowercase English letters.

https://leetcode.com/problems/search-suggestions-system/

https://leetcode.com/problems/search-suggestions-system/solutions/957656/search-suggestions-system/
 */
public class Sol1268_SearchSuggestionsSystem {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // trie
        TrieNode root = new TrieNode();
        for (String product: products) insert(root, product);

        List<List<String>> results = new ArrayList<>();
        for (char c: searchWord.toCharArray()) {
            if ((root = root.children[c - 'a']) == null) break;
            results.add(root.getTopThree());
        }

        while (results.size() < searchWord.length())
            results.add(new ArrayList<>());
        return results;
    }

    private void insert(TrieNode root, String word) {
        for (char c : word.toCharArray()) {
            if (root.children[c - 'a'] == null)
                root.children[c - 'a'] = new TrieNode();
            root = root.children[c - 'a'];
            root.addToPQ(word);
        }
    }

    class TrieNode {
        public TrieNode[] children;
        public PriorityQueue<String> pq;

        public TrieNode() {
            children = new TrieNode[26];
            pq = new PriorityQueue<>((a,b) -> b.compareTo(a));
        }

        public void addToPQ(String word) {
            pq.add(word);
            if (pq.size() > 3) pq.poll();
        }

        public List<String> getTopThree() {
            List<String> topThree = new ArrayList<>();
            while (!pq.isEmpty()) topThree.add(pq.poll());
            Collections.reverse(topThree);
            return topThree;
        }
    }

    // binary search
    List<List<String>> binarySearch(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) {
            String prefix = searchWord.substring(0, i + 1);
            //index of the search key, if it is contained in the array;
            // otherwise, (-(insertion point) - 1).
            // The insertion point is defined as the point at which the key
            // would be inserted into the array: the index of the first element greater than the key,
            // or a.length if all elements in the array are less than the specified key.
            // Note that this guarantees that the return value will be >= 0 if and only if the key is found
            int insert = Arrays.binarySearch(products, prefix);

            if (insert < 0) {
                insert = -insert - 1;
            }
            List<String> suggestions = new ArrayList<>();
            // For example, if A[i] is a prefix of A[j],
            //A[i] must be the prefix of A[i + 1], A[i + 2], ..., A[j] in a sorted list of words
            for (int j = insert; suggestions.size() < 3 && j < products.length && products[j].startsWith(prefix); j++) {
                suggestions.add(products[j]);
            }
            res.add(suggestions);
        }
        return res;
    }

    public List<List<String>> suggestedProducts2(String[] products, String searchWord) {
        // priority queue
        List<List<String>> result = new ArrayList<>();
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.reverseOrder()); // max heap, poll "largest" string out first
        for (int i = 1; i <= searchWord.length(); i++) {  // k: length of searchWord
            String t = searchWord.substring(0, i);  // can be prebuilt with O(K)
            for (String product: products) {  //N
                if (product.startsWith(t)) {
                    pq.offer(product);  //log3
                }
                if (pq.size() > 3) {
                    pq.poll();
                }
            }
            LinkedList<String> temp = new LinkedList<>();
            while (!pq.isEmpty()) {
                temp.addFirst(temp.poll());
            }
            result.add(temp);
        }
        return result;
    }

}

package com.alg.other;

import java.util.*;
/*

asked on 12/8/2023 doordash
Suppose you are in charge of implementing the restaurant search bar at DoorDash.
Your goal is to, given a list of N restaurant names, handle M subsequent search queries,
where for each query you return up to K restaurants whose name contains the query as a prefix.
For example, if K=2 and the search query is "pan", we may return "panda express" and "panera bread".
 */


public class DD_prefixSearch {

    // time complexity
    /*
    O(n⋅m) , where n is the number of restaurants and m is the average length of a restaurant name.
     */
    // space complexity

    // follwup: lexicographical order of the result

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();  // next characters
        List<String> words = new ArrayList<>(); // words that have prefixes ending at this node
    }

    public class RestaurantSearch {
        TrieNode root = new TrieNode();

        public RestaurantSearch(String[] restaurants) {
            for (String restaurant : restaurants) {
                TrieNode node = root;
                for (char c : restaurant.toCharArray()) {
                    node.children.putIfAbsent(c, new TrieNode());
                    node = node.children.get(c);
                    node.words.add(restaurant);
                }
            }
        }
        public List<List<String>> multiSearch(String[] queries, int k) {
            List<List<String>> res = new ArrayList<>();
            for (String query: queries) {
                res.add(search(query, k));
            }
            return res;
        }
        public List<String> search(String query, int k) {
            TrieNode node = root;
            for (char c : query.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    return Collections.emptyList();
                }
                node = node.children.get(c);
            }
            List<String> result = new ArrayList<>();
            for (int i = 0; i < Math.min(k, node.words.size()); i++) {
                result.add(node.words.get(i));
            }
            return result;
//            return node.words.subList(0, Math.min(k, node.words.size()));
        }
    }

    public static void main(String[] args) {
        String[] restaurants = {"panda express", "panera bread", "poke bar", "pancake house", "papa johns"};
        DD_prefixSearch ddp = new DD_prefixSearch();
        RestaurantSearch search = ddp.new RestaurantSearch(restaurants);
        String[] queries = new String[]{"pan", "po"};
        System.out.println(search.multiSearch(queries, 2));  // prints ["panda express", "pancake house"]
    }
}

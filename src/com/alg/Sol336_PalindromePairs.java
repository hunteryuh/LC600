package com.alg;

import java.util.*;

/*Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]*/
public class Sol336_PalindromePairs {
    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if ( words == null || words.length < 2 ) return res;
        Map<String,Integer> map = new HashMap<>();
        //build the map save the key-val pairs: String - idx
        for(int i = 0; i < words.length; i++){
            map.put(words[i], i);
        }
        for(int i = 0; i < words.length; i++){
            // 枚举word里所有的切割位置
            for (int j = 0; j <= words[i].length(); j++){ // notice it should be "j <= words[i].length()"
                String s1 = words[i].substring(0, j);
                String s2 = words[i].substring(j);
                if (isPalind(s1)) { //word2 + s1 + s2 is palindrome
                    String str2reverse = new StringBuilder(s2).reverse().toString();
                    if(map.getOrDefault(str2reverse,i)!=i){
                        res.add(Arrays.asList(map.get(str2reverse),i)); // [word2 index,i]
                    }
                }
                if (isPalind(s2) && !s2.isEmpty()) { // s1 + s2 + word2 is palindrome
                    // (avoid duplicate): s2.length != 0 as when j = 0, s1 is empty and palindrome, when j = words[i].length, s2 is empty and palindrome

                    String s1reverse = new StringBuilder(s1).reverse().toString();
                    if( map.getOrDefault(s1reverse,i) != i){
                        res.add(Arrays.asList(i, map.get(s1reverse)));
                    }
                }
            }
        }
        return res;

    }

    /*Another way to avoid duplicates is to use
    Set<List<Integer>> ret = new HashSet<>(); and return new ArrayList<>(ret);*/

    private static boolean isPalind(String str) {
        for(int i = 0, j = str.length() - 1; i <=j; i++,j--){
            if(str.charAt(i)!=str.charAt(j)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        //String[] strings = {"abc","cba","tts","s","stt"};
        String[] s2 = {"aba","ba","a","caba"};
        String[] s3 = {"abcd","dcba"};
        System.out.println("adfad".substring(0,0));

        System.out.println(palindromePairs(s2));
        System.out.println(palindromePairs(s3));

        /*Input:
["aba","ba","a","caba"]
Output:
[[2,1],[0,3]]
Expected:
[[0,1],[2,1],[0,3]]*/
    }
}

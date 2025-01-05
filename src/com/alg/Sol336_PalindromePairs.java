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
                    if(map.getOrDefault(s1reverse,i) != i){
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
        for (int i = 0, j = str.length() - 1; i <= j; i++,j--) {
            if(str.charAt(i) != str.charAt(j)) return false;
        }
        return true;
    }

    //https://leetcode.com/problems/palindrome-pairs/solutions/79210/the-easy-to-unserstand-java-solution/?envType=company&envId=airbnb&favoriteSlug=airbnb-three-months
    // n: number of words, k: length of longest word
    // time : K^2 * n
    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if(words == null || words.length == 0){
            return res;
        }
        //build the map save the key-val pairs: String - idx
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            map.put(words[i], i);
        }

        //special cases: "" can be combined with any palindrome string
        if(map.containsKey("")){
            int blankIdx = map.get("");
            for(int i = 0; i < words.length; i++){
                if(isPalindrome(words[i])){
                    if(i == blankIdx) continue;
                    res.add(Arrays.asList(blankIdx, i));
                    res.add(Arrays.asList(i, blankIdx));
                }
            }
        }

        //find all string and reverse string pairs
        for(int i = 0; i < words.length; i++){
            String cur_r = reverseStr(words[i]);
            if(map.containsKey(cur_r)){
                int found = map.get(cur_r);
                if(found == i) continue;
                res.add(Arrays.asList(i, found));
            }
        }

        //find the pair s1, s2 that
        //case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1)
        //case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverse(s2) => (s1, s2)
        for(int i = 0; i < words.length; i++){
            String cur = words[i];
            for(int cut = 1; cut < cur.length(); cut++){
                if(isPalindrome(cur.substring(0, cut))){
                    String cut_r = reverseStr(cur.substring(cut));
                    if(map.containsKey(cut_r)){
                        int found = map.get(cut_r);
                        if(found == i) continue;
                        res.add(Arrays.asList(found, i));
                    }
                }
                if(isPalindrome(cur.substring(cut))){
                    String cut_r = reverseStr(cur.substring(0, cut));
                    if(map.containsKey(cut_r)){
                        int found = map.get(cut_r);
                        if(found == i) continue;
                        res.add(Arrays.asList(i, found));
                    }
                }
            }
        }

        return res;
    }

    public String reverseStr(String str){
        StringBuilder sb= new StringBuilder(str);
        return sb.reverse().toString();
    }

    public boolean isPalindrome(String s){
        int i = 0;
        int j = s.length() - 1;
        while(i <= j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
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

package com.alg;

import java.util.*;

/**
 * Created by HAU on 10/8/2017.
 */
/*Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], */
public class Sol49_GroupAnagram {
    //time : Nklog(k)   N is length of strs, k is the max length of string in strs
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<List<String>>();
        Map<String, List<String>> res = new HashMap<>();
        for (String s : strs){
            char[] car = s.toCharArray();
            Arrays.sort(car);
            String key = String.valueOf(car);
            if (!res.containsKey(key)){
                res.put(key, new ArrayList());
            }

            res.get(key).add(s);

/*            List<String> anagrams = res.getOrDefault(key, new LinkedList());
            anagrams.add(s);
            res.put(key,anagrams);*/

        }
        return new ArrayList(res.values());
    }

    public static void main(String[] args) {
        String[] strs ={"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
        System.out.println(groupAna(strs));
    }
    // method 2, count array
    public static List<List<String>> groupAna(String[] strs){
        if ( strs.length == 0 ) return  new ArrayList<List<String>>();
        Map<String, List> ans = new HashMap<>();
        int[] count = new int[26];
        for(String s: strs){
            Arrays.fill(count,0);
            for(char c: s.toCharArray()){
                count[c-'a'] ++;
            }
            StringBuilder sb = new StringBuilder("");
            for(int i = 0; i < 26; i++){
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if(!ans.containsKey(key)){
                ans.put(key, new ArrayList());
            }
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}

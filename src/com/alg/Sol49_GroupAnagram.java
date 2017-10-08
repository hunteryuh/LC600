package com.alg;

import java.util.*;

/**
 * Created by HAU on 10/8/2017.
 */
/*Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], */
public class Sol49_GroupAnagram {
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List> res = new HashMap<>();
        for (String s : strs){
            char[] car = s.toCharArray();
            Arrays.sort(car);
            String key = String.valueOf(car);
            if (!res.containsKey(key)){
                res.put(key, new ArrayList());
            }

            res.get(key).add(s);

        }
        return new ArrayList(res.values());
    }

    public static void main(String[] args) {
        String[] strs ={"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }
}

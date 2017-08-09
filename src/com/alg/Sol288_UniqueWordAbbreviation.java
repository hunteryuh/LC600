package com.alg;

import java.util.HashMap;

/**
 * Created by HAU on 6/26/2017.
 */

/*Assume you have a dictionary and given a word, find whether
its abbreviation is unique in the dictionary. A word's abbreviation is unique
if no other word from the dictionary has the same abbreviation.

        Example:

        Given dictionary = [ "deer", "door", "cake", "card" ]

        isUnique("dear") -> false
        isUnique("cart") -> true
        isUnique("cane") -> false
        isUnique("make") -> true*/
public class Sol288_UniqueWordAbbreviation {
    public static class ValidWordAbbr {
        HashMap<String, String> map;

        public ValidWordAbbr(String[] dictionary) {
            map = new HashMap<>();
            for (String str:dictionary) {
                String key;
                if (str.length() <= 2) {
                    key = str.substring(0);
                } else {
                    key = str.charAt(0) + Integer.toString(str.length() - 2) + str.substring(str.length() - 1);
                }
                if(map.containsKey(key)) {
                    if (map.get(key).equals(str)){
                        map.put(key,"");
                    }
                }
                else map.put(key,str);
            }
        }

        public boolean isUnique(String word) {
            if (word.length() <= 2)
                return map.containsValue(word);
            else{
                return !map.containsKey(word.charAt(0) + Integer.toString(word.length() - 2) + word.substring(word.length() - 1));
            }
        }

        public static void main(String[] args) {
            String[] dict = {"door","deer","sake","cane","it","it"};
            ValidWordAbbr dic = new ValidWordAbbr(dict);
            System.out.println(dic.isUnique("clan"));
            System.out.println(dic.isUnique("safe"));
            System.out.println(dic.isUnique("it"));
            System.out.println(dic.isUnique("daar"));
        }
    }
}

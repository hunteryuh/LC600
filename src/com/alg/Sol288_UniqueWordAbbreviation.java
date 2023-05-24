package com.alg;

import java.util.HashMap;

/**
 * Created by HAU on 6/26/2017.
 */
/*An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
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
                    key = str;
                } else {
                    key = str.charAt(0) + Integer.toString(str.length() - 2) + str.substring(str.length() - 1);
                }
                if(map.containsKey(key)) {
                    if (map.get(key).equals(str)){
                        map.put(key,""); // if more than one string maps to the same key, then this key will be invalid, set to ""
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
            System.out.println(dic.isUnique("clan")); // true
            System.out.println(dic.isUnique("safe")); // false
            System.out.println(dic.isUnique("it")); //false
            System.out.println(dic.isUnique("daar")); // false
        }
    }
}

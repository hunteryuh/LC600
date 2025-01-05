package com.alg;

import java.util.*;

/**
 * Created by HAU on 6/10/2017.
 */

/*Given a string, find the first non-repeating character in it and
        return it's index. If it doesn't exist, return -1.
        s = "leetcode"
        return 0.

        s = "loveleetcode",
        return 2.*/
public class Sol387_FirstUniqueCharacter_inAString {

    public static int firstUniqChar(String s){
/*        if (s == null || s.length() == 0)
            return -1;
        int n = s.length();
        if (n == 1) return 0;
        int i, j;
        for (i = 0; i < n; i++){
            for (j = i + 1; j < n; j++){
                if (s.charAt(j) == s.charAt(i)){
                    //i++;
                    break;
                }

            }
            if ( j == n && i != n - 1) return i;
        }
        return -1;*/

        if (s == null || s.length() == 0)
            return -1;
        int n = s.length();
        if (n == 1) return 0;
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(s.charAt(i)) )
                map.put(s.charAt(i),1);
            else {
                //int t = map.get(s.charAt(i));
                map.put( s.charAt(i), map.get(s.charAt(i))+1);
            }
        }
        for (int i = 0; i < n; i++){
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;

    }

    public static int firstUniqChar2(String s) {
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++)
            freq [s.charAt(i) - 'a'] ++;
        for(int i = 0; i < s.length(); i ++)
            if(freq [s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(firstUniqChar(s));
        String t = "loveleetcode";
        System.out.println(firstUniqChar(t));
        String a = "ccdcde";
        System.out.println(firstUniqChar(a));
        System.out.println(firstUniqChar2(a));
    }

    // https://leetcode.com/problems/first-unique-character-in-a-string/solutions/917857/java-one-pass-solution/
    // one pass solution using linkedHashMap
    // LinkedHashMap preserves insert order (like a linked list)
    // but also allows for O(1) put, remove, contains operations like a Map.
    // Therefore the following works as a one pass solution:

    public int firstUniqChar3(String s) {
        LinkedHashMap<Character, Integer> lhs = new LinkedHashMap<>();
        Set<Character> knownDups = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            Character c = s.charAt(i);

            if (knownDups.contains(c))
                continue;

            if  (lhs.containsKey(c)) {
                lhs.remove(c);
                knownDups.add(c);
            }
            else {
                lhs.put(c, i);
            }
        }

        if (lhs.isEmpty())
            return -1;

        return lhs.entrySet().iterator().next().getValue();
    }

    // https://leetcode.com/problems/first-unique-character-in-a-string/solutions/2436396/java-one-pass-two-pass-solutions/
    // one pass method 2
    public int firstUniqChar4(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        int[] freq = new int[26];

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // Repeated character
            if(freq[c - 'a'] > 0) {
                map.remove(c);
            } else {
                map.put(c, i);
            }

            freq[c - 'a']++;
        }

        return map.size() == 0 ? -1 : map.entrySet().iterator().next().getValue();
    }
}

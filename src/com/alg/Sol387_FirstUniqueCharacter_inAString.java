package com.alg;

import java.util.HashMap;

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
}

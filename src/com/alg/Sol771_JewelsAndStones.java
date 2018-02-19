package com.alg;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by HAU on 2/18/2018.
 */
/*You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".

Example 1:

Input: J = "aA", S = "aAAbbbb"
Output: 3
Example 2:

Input: J = "z", S = "ZZ"
Output: 0*/
public class Sol771_JewelsAndStones {
    public static int numJewelsInStones(String J, String S) {
        // hashset
        int res = 0;
        Set<Character> set = new HashSet<>();
        for(char c: J.toCharArray()) set.add(c);
        for(char c: S.toCharArray()) {
            if(set.contains(c)){
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String J = "Aa";
        String S = "aAAbBc";
        System.out.println(numJewelsInStones(J,S));
    }
}

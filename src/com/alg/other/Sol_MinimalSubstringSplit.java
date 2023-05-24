package com.alg.other;

import java.util.HashSet;
import java.util.Set;

public class Sol_MinimalSubstringSplit {

    public int minSplit(String s) {
        int count = 1;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!set.contains(c)) {
                set.add(c);
            } else {
                set.clear();
                set.add(c);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Sol_MinimalSubstringSplit ss = new Sol_MinimalSubstringSplit();
        String s1 = "aabb";
        String s2 = "aaaa";
        String s3 = "abcabc";
        String s4 = "abccbad";
        String s5 = "";
        System.out.println(ss.minSplit(s1));
        System.out.println(ss.minSplit(s2));
        System.out.println(ss.minSplit(s3));
        System.out.println(ss.minSplit(s4));
        System.out.println(ss.minSplit(s5));
    }
}

package com.alg.other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
//https://leetcode.com/discuss/interview-question/398023/Microsoft-Online-Assessment-Questi&#8205;&#8205;&#8204;&#8205;&#8205;&#8204;&#8205;&#8204;&#8204;&#8204;&#8204;&#8205;&#8205;&#8205;&#8204;&#8205;ons
// https://algo.monster/problems/microsoft_online_assessment_questions
public class MicroSoft_OA {
    // return the mi number of substrings into
    // which the string has to be split no that no letter occurs more than once in each substring

    // abab
    public int solution(String S) {
        // write your code in Java SE 8
        Set<Character> set = new HashSet<>();
        int count = 1;
        for (char c : S.toCharArray()) {
            if (set.contains(c)) {
                set.clear();
                count++;
            }
            set.add(c);
        }

        return count;
    }

    public boolean solution(int[] A) {
        // write your code in Java SE 8
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: A) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key: map.keySet()) {
            if (map.get(key) % 2 == 1) {
                return false;
            }
        }
        return true;

    }
    // whether it is possible to split an int array into paris

    public boolean splitToPairs(int[] A) {
        // write your code in Java SE 8
//        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int num: A) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        return set.isEmpty();

    }
}

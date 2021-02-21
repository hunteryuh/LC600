package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAU on 7/11/2017.
 */

/*
Given an array A of strings made only from lowercase letters, return a list of all characters that
show up in all strings within the list (including duplicates).
For example, if a character occurs 3 times in all strings but not 4 times,
you need to include that character three times in the final answer.
*/

public class Sol1002_FindCommonChars {
    public static List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        int[] count = new int[26];
        if (A == null || A.length == 0) return res;
        for (char c: A[0].toCharArray()) {
            count[c-'a']++;
        }
        for (int i = 1; i < A.length; i++) {
            int[] curr = new int[26];
            for (char d: A[i].toCharArray()) {
                curr[d - 'a']++;
            }
            for (int j = 0; j < count.length; j++) {
                if (curr[j] < count[j]) {
                    count[j] = curr[j];
                }
            }
        }

        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                res.add((char) (i + 'a') + ""); // Character.toString((char) ('a' + i))
            }
        }
        return res;
    }

    // clean solution 2
    public static List<String> findCommonChars(String[] A) {
        List<String> result = new ArrayList<>();

        for (char c = 'a'; c <= 'z'; c++){
            int minCount = Integer.MAX_VALUE;
            for (String s : A){
                int wordCount = 0;
                for (char cur_c : s.toCharArray()) if (cur_c == c) wordCount++;
                minCount = Math.min(minCount, wordCount);
            }

            for (int i = 0; i < minCount; i++) result.add("" + c);
        }

        return result;
    }


    public static void main(String[] args) {
        String[] arr = {"bella", "lable", "roller"};
        System.out.println(commonChars(arr));
        System.out.println(findCommonChars(arr));
    }
}

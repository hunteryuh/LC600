package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
We can shift a string by shifting each of its letters to its successive letter.

    For example, "abc" can be shifted to be "bcd".

We can keep shifting the string to form a sequence.

    For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".

Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.



Example 1:

Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]

Example 2:

Input: strings = ["a"]
Output: [["a"]]



Constraints:

    1 <= strings.length <= 200
    1 <= strings[i].length <= 50
    strings[i] consists of lowercase English letters.


 */
public class Sol249_GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String string: strings) {
            String key = getHash(string);
            map.computeIfAbsent(key, x -> new ArrayList<>()).add(string);
        }
        for (List<String> group: map.values()) {
            res.add(group);
        }
//        return new ArrayList<>(map.values());
        return res;
    }

    private String getHash(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            int relative_distance = (s.charAt(i) - s.charAt(i - 1) + 26) % 26;
            sb.append(".");                 // in case [0,1,11] [0,11,1], so i add "." to key.

//            char shift = (char) ((s.charAt(i) - s.charAt(i-1) + 26 )% 26 + 'a'); // covers circular shift
            sb.append(relative_distance);
        }
        System.out.println(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"abc","bcd","acef","xyz","az","ba","a","z"};
        Sol249_GroupShiftedStrings ss = new Sol249_GroupShiftedStrings();
        List<List<String>> res = ss.groupStrings(strings);
        System.out.println(res);

        StringBuilder sb = new StringBuilder();
        sb.append(1);
        sb.append(Integer.toString(1)); // as "1" in the string
        System.out.println(sb);

        int x = 'c' - 'a';
        System.out.println(x);
        int y = 'c' + 1;
        System.out.println(y);
        char ch = 1 + 'c';
        System.out.println(ch);
    }
}

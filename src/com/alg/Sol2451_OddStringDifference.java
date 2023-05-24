package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given an array of equal-length strings words. Assume that the length of each string is n.

Each string words[i] can be converted into a difference integer array difference[i] of length n - 1 where difference[i][j] = words[i][j+1] - words[i][j] where 0 <= j <= n - 2. Note that the difference between two letters is the difference between their positions in the alphabet i.e. the position of 'a' is 0, 'b' is 1, and 'z' is 25.

For example, for the string "acb", the difference integer array is [2 - 0, 1 - 2] = [2, -1].
All the strings in words have the same difference integer array, except one. You should find that string.

Return the string in words that has different difference integer array.



Example 1:

Input: words = ["adc","wzy","abc"]
Output: "abc"
Explanation:
- The difference integer array of "adc" is [3 - 0, 2 - 3] = [3, -1].
- The difference integer array of "wzy" is [25 - 22, 24 - 25]= [3, -1].
- The difference integer array of "abc" is [1 - 0, 2 - 1] = [1, 1].
The odd array out is [1, 1], so we return the corresponding string, "abc".
Example 2:

Input: words = ["aaa","bob","ccc","ddd"]
Output: "bob"
Explanation: All the integer arrays are [0, 0] except for "bob", which corresponds to [13, -13].
 */
public class Sol2451_OddStringDifference {
    public String oddString(String[] words) {
        // https://leetcode.com/problems/odd-string-difference/solutions/2756780/java-hashmap-explained/
        Map<List<Integer>, List<String>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < chars.length - 1; j++) {
                list.add(chars[j + 1] - chars[j]);
            }
            if (map.containsKey(list)) {
                map.get(list).add(words[i]);
            } else {
                List<String> list2 = new ArrayList<>();
                list2.add(words[i]);
                map.put(list, list2);
            }
        }
        for (List<Integer> key : map.keySet()) {
            if (map.get(key).size() == 1) {
                return map.get(key).get(0);
            }
        }
        return "";
    }
    // https://leetcode.com/problems/odd-string-difference/solutions/2763310/java-c-constant-space-no-hashmaps/
    public String oddString2(String[] words) {
        int matchesFirst = 0;
        int mismatchIdx = -1;
        for (int i = 1; i < words.length; i++) {
            if (matchesDiff(words[0], words[i])) {
                matchesFirst++;
            } else {
                mismatchIdx = i;
            }
        }
        System.out.println(matchesFirst);
        System.out.println(mismatchIdx);
        if (matchesFirst == 0) {
            return words[0];
        }
        return words[mismatchIdx];
    }

    private static boolean matchesDiff(String a, String b) {
        for (int i = 0; i < a.length() - 1; i++) {
            if (a.charAt(i + 1) - a.charAt(i) != b.charAt(i + 1) - b.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words0 = {"adc", "wzy", "abc"};
        String[] words = {"aaa", "bcd", "efg"};
        Sol2451_OddStringDifference ss = new Sol2451_OddStringDifference();
        System.out.println(ss.oddString2(words));
    }
}

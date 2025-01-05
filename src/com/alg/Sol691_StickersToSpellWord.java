package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
We are given n different types of stickers. Each sticker has a lowercase English word on it.

You would like to spell out the given string target by cutting individual letters from your collection of stickers and rearranging them. You can use each sticker more than once if you want, and you have infinite quantities of each sticker.

Return the minimum number of stickers that you need to spell out target. If the task is impossible, return -1.

Note: In all test cases, all words were chosen randomly from the 1000 most common US English words, and target was chosen as a concatenation of two random words.



Example 1:

Input: stickers = ["with","example","science"], target = "thehat"
Output: 3
Explanation:
We can use 2 "with" stickers, and 1 "example" sticker.
After cutting and rearrange the letters of those stickers, we can form the target "thehat".
Also, this is the minimum number of stickers necessary to form the target string.

Example 2:

Input: stickers = ["notice","possible"], target = "basicbasic"
Output: -1
Explanation:
We cannot form the target "basicbasic" from cutting letters from the given stickers.



Constraints:

    n == stickers.length
    1 <= n <= 50
    1 <= stickers[i].length <= 10
    1 <= target.length <= 15
    stickers[i] and target consist of lowercase English letters.


 */
// mock interview meta 3/29/2024
/*
The Facebook company store sells stickers that say the word “facebook” on them. We just got a new shipment in, and we have way more than we know what to do with. We came up with a plan: we can make posters by cutting and pasting the letters in the word "facebook" to make other words.
Given a particular source string representing a word on a sticker, write a function that tells me how many stickers of that string I need in order to make a given target string. Your function should take in both a source string and target string, and return the number of stickers.

Examples:
source == "facebook", target == "fee": return 2
facebook ->
source == "facebook", target == "BOO": return 1
source == "facebook", target == "coffee kebab": return 3
 */
public class Sol691_StickersToSpellWord {
    // https://leetcode.com/problems/stickers-to-spell-word/solutions/313463/java-dfs-memo/
    private int[][] maps;
    private Map<String, Integer> memo;

    public int minStickers(String[] words, String target) {
        memo = new HashMap<>();
        memo.put("", 0);
        maps = new int[words.length][26];
        for (int i = 0; i < words.length; i++) {
            for (char ch: words[i].toCharArray()) {
                maps[i][ch - 'a']++;
            }
        }
        return dfs(target);
    }

    private int dfs(String target) {
        System.out.println(target);
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        int min = Integer.MAX_VALUE;
        int[] targetMap = new int[26];
        for (char ch : target.toCharArray()) {
            targetMap[ch - 'a']++;
        }
        for (int[] map : maps) {
            /*
            better:
// #0: Skip words that don't cover the first character of target.
That is inspected by the top post. My original approach is to skip stickers don't cover any
character of target.
The current approach cuts branches in solution tree much earlier thus much faster.
             */
            if (map[target.charAt(0) - 'a'] <= 0) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (targetMap[i] > 0) {
                    /*
                    Skip characters that are not in target. That also helps cut branches in solution tree.
                     */
                    for (int time = 0; time < Math.max(targetMap[i] - map[i], 0);time++) {
                        sb.append((char) ('a' + i));
                    }
                }
            }
            System.out.println(sb.toString());
            int cur = dfs(sb.toString());
            if (cur != -1) {
                min = Math.min(min, cur);
            }
        }
        int result = (min == Integer.MAX_VALUE) ? -1 : 1 + min;
        memo.put(target, result);

        return result;
    }

    public static void main(String[] args) {
        String[] sources = {"with","example","science"};
        String target = "thehat";
        Sol691_StickersToSpellWord ss = new Sol691_StickersToSpellWord();
        System.out.println(ss.minStickers(sources, target));
    }


    public static int countStickers(String source, String target) {
        // Initialize a map to store the count of each character in the source string
        Map<Character, Integer> sourceCount = new HashMap<>();
        for (char c : source.toCharArray()) {
            sourceCount.put(c, sourceCount.getOrDefault(c, 0) + 1);
        }

        // Initialize a map to store the count of each character in the target string
        Map<Character, Integer> targetCount = new HashMap<>();
        for (char c : target.toCharArray()) {
            targetCount.put(c, targetCount.getOrDefault(c, 0) + 1);
        }

        // Initialize the minimum number of stickers required
        int minStickers = 0;

        // Iterate over each character in the target string
        for (char c : targetCount.keySet()) {
            // If the character is not in the source string, return -1 (impossible to form the target)
            if (!sourceCount.containsKey(c)) {
                return -1;
            }
            // Calculate the number of stickers required for the current character
//            int stickersNeeded = (targetCount.get(c) + sourceCount.get(c) - 1) / sourceCount.get(c);
            int tc = targetCount.get(c);
            int sc = targetCount.get(c);
            if (tc % sc == 0) {
                minStickers = Math.max(minStickers, tc/ sc);
            } else {
                minStickers = Math.max(minStickers, tc / sc + 1);
            }
        }

        return minStickers;
    }
}

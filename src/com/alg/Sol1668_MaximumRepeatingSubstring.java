package com.alg;
/*
For a string sequence, a string word is k-repeating if word concatenated k times is a substring of sequence. The word's maximum k-repeating value is the highest value k where word is k-repeating in sequence. If word is not a substring of sequence, word's maximum k-repeating value is 0.

Given strings sequence and word, return the maximum k-repeating value of word in sequence.
 */

public class Sol1668_MaximumRepeatingSubstring {
    public static int maxRepeating(String sequence, String word) {
        StringBuilder sb = new StringBuilder(word);
        int res = 0;
        while (sequence.contains(sb)) {
            res++;
            sb.append(word);
        }
        return res;
    }

    // no string function, wrong as it counts first match and moves on;
    // it could match from not the first match and has the max value
    public static int maxRepeat(String sequence, String word) {
        int res = 0;
        int i = 0;
        int max = 0;
        while (i + word.length() <= sequence.length()) {
            if (match(word, sequence, i)) {
                res++;
                i += word.length();
                max = Math.max(max, res);
            } else {
                i++;
                res = 0;
            }
        }
        return max;
    }
    private static boolean match(String word, String sequence, int start) {
        for (int i = start; i - start < word.length(); i++) {
            if (sequence.charAt(i) != word.charAt(i - start)) {
                return false;
            }
        }
        return true;
    }

    // wrong too,
    public static int maxRepeating2(String sequence, String word) {
        int max = 0;
        for (int ind = 0; ind < sequence.length(); ind++) {
            if (sequence.charAt(ind) == word.charAt(0)) {
                int cnt = 0;
                while (ind + word.length() <= sequence.length()) {
                    if (sequence.substring(ind).startsWith(word)) {
                        cnt++;
                        ind += word.length();
                    } else {
                        break;
                    }
                }

                max = Math.max(max, cnt);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String sequence = "aaabaaabaaaabaaaabaaaabaaaabaaaaba";
        String word = "aaaba";
        int result = maxRepeating(sequence, word);
        System.out.println(result);
        System.out.println(maxRepeat(sequence, word));
        System.out.println(maxRepeating2(sequence, word));
    }
}

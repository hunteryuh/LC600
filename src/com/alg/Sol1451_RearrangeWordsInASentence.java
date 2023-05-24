package com.alg;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
Given a sentence text (A sentence is a string of space-separated words) in the following format:

First letter is in upper case.
Each word in text are separated by a single space.
Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their lengths. If two words have the same length, arrange them in their original order.

Return the new text following the format shown above.



Example 1:

Input: text = "Leetcode is cool"
Output: "Is cool leetcode"
Explanation: There are 3 words, "Leetcode" of length 8, "is" of length 2 and "cool" of length 4.
Output is ordered by length and the new first word starts with capital letter.
Example 2:

Input: text = "Keep calm and code on"
Output: "On and keep calm code"
Explanation: Output is ordered as follows:
"On" 2 letters.
"and" 3 letters.
"keep" 4 letters in case of tie order by position in original text.
"calm" 4 letters.
"code" 4 letters.
Example 3:

Input: text = "To be or not to be"
Output: "To be or to be not"
 */
public class Sol1451_RearrangeWordsInASentence {
    public static class StringPosition {
        private String s;
        private int p;
        public StringPosition(String s, int p) {
            this.s = s;
            this.p = p;
        }
    }
    public String arrangeWords(String text) {
        String[] words = text.toLowerCase().split(" ");
        PriorityQueue<StringPosition> pq = new PriorityQueue<>(
                (a, b) -> a.s.length() - b.s.length() == 0 ? a.p - b.p : a.s.length() - b.s.length()
        );
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            pq.add(new StringPosition(word, i));
        }
        StringBuilder sb = new StringBuilder();
        String first = pq.poll().s;
//        sb.append(String.valueOf(first.charAt(0)).toUpperCase());
        sb.append(Character.toUpperCase(first.charAt(0)));
        sb.append(first.substring(1));
        while (!pq.isEmpty()) {
            sb.append(" ");
            sb.append(pq.poll().s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        String input = "Keep calm and code on";
        String input = "To be or not to be";
        Sol1451_RearrangeWordsInASentence s = new Sol1451_RearrangeWordsInASentence();
        System.out.println(s.arrangeWords(input));
    }

    // 4 lines
    public String arrangeWords2(String text) {
        String[] s = text.toLowerCase().split("\\s+");
        Arrays.sort(s, (a, b) ->  a.length() - b.length()); // if same length original order is maintained in .sort
        String ans = String.join(" ", s);
        return Character.toUpperCase(ans.charAt(0)) + ans.substring(1);
    }
}

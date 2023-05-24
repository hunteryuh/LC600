package com.alg.other;

import java.util.HashMap;
import java.util.Map;

/*
interviewed by Karat, Compass  1/14/2022
You are running a classroom and suspect that some of your students are passing around the answers to multiple choice questions disguised as random strings.

Your task is to write a function that, given a list of words and a string, finds and returns the word in the list that is scrambled up inside the string, if any exists. There will be at most one matching word.
The letters don't need to be in order or next to each other. The letters cannot be reused.

Example:
words = ['cat', 'baby', 'dog', 'bird', 'car', 'ax']
string1 = 'tcabnihjs'
find_embedded_word(words, string1) -> cat

string2 = 'tbcanihjs'
find_embedded_word(words, string2) -> cat

string3 = 'baykkjl'
find_embedded_word(words, string3) -> None

string4 = 'bbabylkkj'
find_embedded_word(words, string4) -> baby

string5 = 'ccc'
find_embedded_word(words, string5) -> None

string6 = 'nbird'
find_embedded_word(words, string6) -> bird

n = number of words in words
m = maximal string length of each word
 */
public class FindEmbeddedWords {
    private  String getWord(String[] words, String s) {
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);

        }
        for (String word : words) {
            Map<Character, Integer> wordMap = new HashMap<>();
            for (int j = 0; j < word.length(); j++) {
                char wc = word.charAt(j);
                if (!map.containsKey(wc)) {
                    break;
                }
                wordMap.put(wc, wordMap.getOrDefault(wc, 0) + 1);
                if (wordMap.get(wc) > map.get(wc)) {
                    break;
                }
                if (j == word.length() - 1) {
                    return word;
                }
            }

        }

        return null;
    }


    public static void main(String[] argv) {
        String[] words = new String[] { "cat", "baby", "dog", "bird", "car", "ax"};
        String string1 = "tcabnihjs";
        String string2 = "tbcanihjs";
        String string3 = "baykkjl";
        String string4 = "bbabylkkj";
        String string5 = "ccc";
        String string6 = "breadmaking";

        FindEmbeddedWords ff = new FindEmbeddedWords();
        String res = ff.getWord(words, string1);
        System.out.println(res);

    }

}

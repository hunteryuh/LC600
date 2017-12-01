package com.alg;

/**
 * Created by HAU on 12/1/2017.
 */
/*Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list. */
public class Sol243_ShortestWordDistance {
    public static int shortestDistance(String[] words, String word1, String word2) {
        int w1 = -1, w2 = -1;
        int res = words.length;
        for(int i = 0; i < words.length; i++){
            if (words[i].equals(word1)) w1 = i;
            else if(words[i].equals(word2)) w2 = i;
            if(w1 != -1 && w2 != -1){
                res = Math.min(res,Math.abs(w1-w2));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"you","are","my","god","are","you"};
        String word1 = "are";
        String word2 = "god";
        System.out.println(shortestDistance(words,word1,word2));
    }
}

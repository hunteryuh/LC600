package com.alg;

import java.util.*;

/**
 * Created by HAU on 11/3/2017.
 */
/*Given two words (beginWord and endWord), and a dictionary's word list,
find the length of shortest transformation sequence from beginWord to endWord,
such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list.
Note that beginWord is not a transformed word.
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

*/
public class Sol127_WordLadder {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.length() != endWord.length()) return 0;
        HashMap<String,Integer> distance = new HashMap<>();
        HashSet<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();

        HashSet<String> dic = new HashSet<>();
        dic.addAll(wordList);
        q.add(beginWord);
        visited.add(beginWord);
        distance.put(beginWord,1);
        while (!q.isEmpty()){
            String word = q.remove();
            if (word.equals(endWord)) return distance.get(word);
            // if not found, go to find neighbors
            for (int i = 0; i < word.length(); i++){
                char oldChar = word.charAt(i);
                for (char c = 'a'; c <= 'z'; c++){
                    if (c == oldChar){
                        continue;
                    }
                    StringBuilder sb = new StringBuilder(word);
                    sb.setCharAt(i,c);
                    String newword = sb.toString();
                    if ( dic.contains(newword) && !visited.contains(newword)){
                        q.add(newword);
                        visited.add(newword);
                        distance.put(newword,distance.get(word)+1);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        List<String> list = new ArrayList<>(Arrays.asList("hot","dot","dog","log","lot","cog"));
        System.out.println(ladderLength(start,end,list));
    }
}

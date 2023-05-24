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

https://leetcode.com/problems/word-ladder/
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
            // if the goal is to find one path of transformation, better use DFS depth first search
            // here we are asked for shortest path, then use breadth first search bfs
            for (int i = 0; i < word.length(); i++){
                char oldChar = word.charAt(i);
                for (char c = 'a'; c <= 'z'; c++){
                    if (c == oldChar){
                        continue;
                    }
                    StringBuilder sb = new StringBuilder(word);
                    sb.setCharAt(i,c);  //set the ith char in the stringbuilder as c
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

    // https://www.jiuzhang.com/solutions/word-ladder 简单图最短路径问题

    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        List<String> list = new ArrayList<>(Arrays.asList("hot","dot","dog","log","lot","cog"));
        System.out.println(ladderLength(start,end,list));

        String start2 = "ymain";
        String end2 = "oecij";
        List<String> list2 = new ArrayList<>(Arrays.asList("ymann","yycrj","oecij","ymcnj","yzcrj","yycij","xecij","yecij","ymanj","yzcnj","ymain"));
        Sol127_WordLadder s = new Sol127_WordLadder();
        System.out.println(s.ladderLength2(start2, end2, list2));

    }


    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (endWord.length() != beginWord.length()) {
            return 0;
        }
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        int res = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (String neigh : getAvailableWords(word, wordSet)) {
                    if (!visited.contains(neigh)) {
                        queue.offer(neigh);
                        visited.add(neigh);
                        if (neigh.equals(endWord)) {
                            return res;
                        }
                    }
                }
            }

        }
        return 0;
    }

    /*
    "ymain"
"oecij"
["ymann","yycrj","oecij","ymcnj","yzcrj","yycij","xecij","yecij","ymanj","yzcnj","ymain"]
     */
    // time : 25 * L * L  ( length of word in the dic)
    private List<String> getAvailableWords(String word, Set<String> wordList) {
        List<String> words = new ArrayList<>();
        for (int c = 0; c < word.length(); c++) {
            char cur = word.charAt(c);
            for (char i = 'a'; i <= 'z'; i++) { //  i can be equal to 'z'
                if (cur != i) {
                    char[] newCharArray = word.toCharArray();
                    newCharArray[c] = i;
                    String newWord = new String(newCharArray);
                    if (wordList.contains(newWord)) {
                        words.add(newWord);
                    }
                }
            }
        }
        return words;
    }
}

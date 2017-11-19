package com.alg;

import java.util.*;

/**
 * Created by HAU on 11/3/2017.
 */
/*Given two words (beginWord and endWord), and a dictionary's word list,
find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]

*/
/*大部分都是先用BFS找到最短步骤数，并将一些相关信息使用HashMap保存，
然后再使用DFS遍历保存下来的neighbor和distance信息，找出符合要求的结果，大概是因为Word Ladder I那个题目中延续下来的方法。*/
public class Sol126_WordLadderII {
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
/*        if (beginWord.length() != endWord.length()) return 0;
        HashMap<String,Integer> distance = new HashMap<>();
        HashSet<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();*/

        HashSet<String> dic = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<>();// neighbors of each node
        HashMap<String,Integer> distance = new HashMap<>();
        ArrayList<String> sol = new ArrayList<>();
        
        dic.add(beginWord); 
        bfs( beginWord, endWord, dic, nodeNeighbors, distance);
        dfs( beginWord, endWord, dic, nodeNeighbors, distance, sol, res);
        return res;
        
    }



    private static void bfs(String beginWord, String endWord, HashSet<String> dic, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance) {
        for( String str: dic){
            nodeNeighbors.put(str, new ArrayList<>());
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        distance.put(beginWord,0);

        while(!queue.isEmpty()){
            int count = queue.size();
            boolean found = false;
            for ( int i = 0; i < count; i++){
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                ArrayList<String> neighbors = getNeighbors(cur, dic);

                for (String neighbor: neighbors){
                    nodeNeighbors.get(cur).add(neighbor);
                    if(!distance.containsKey(neighbor)){ // check if visited
                        distance.put(neighbor,curDistance + 1);
                        if(endWord.equals(neighbor)){
                            found = true; // found the shortest path
                        }else{
                            queue.add(neighbor);
                        }
                    }
                }
            }
            if(found){
                break;
            }
        }
    }

    private static ArrayList<String> getNeighbors(String cur, HashSet<String> dic) {
        // find all next level nodes( string words)
        ArrayList<String> res = new ArrayList<>();
        char[] charr = cur.toCharArray();
        for ( char ch = 'a'; ch <= 'z'; ch++){
            for (int i = 0; i < charr.length; i++){
                if (charr[i] == ch) continue;
                char oldChar = charr[i];
                charr[i] = ch;
                if (dic.contains(String.valueOf(charr))){
                    res.add(String.valueOf(charr));
                }
                charr[i] = oldChar;
            }
        }
        return res;
    }
    // DFS: output all paths with the shortest distance.
    private static void dfs(String node, String endWord, HashSet<String> dic, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance, ArrayList<String> sol, List<List<String>> res) {
        sol.add(node);
        if(endWord.equals(node)){
            res.add(new ArrayList<>(sol));
        }else{
            for (String next: nodeNeighbors.get(node)){
                if(distance.get(next) == distance.get(node) + 1){
                    dfs(next,endWord,dic,nodeNeighbors,distance,sol,res);
                }
            }
        }
        sol.remove(sol.size() - 1);
    }
    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        List<String> list = new ArrayList<>(Arrays.asList("hot","dot","dog","log","lot","cog"));
        System.out.println(findLadders(start,end,list));
    }
}

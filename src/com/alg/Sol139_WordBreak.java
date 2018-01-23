package com.alg;

import java.util.*;

/**
 * Created by HAU on 11/30/2017.
 */
/*Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".*/
public class Sol139_WordBreak {

    public static boolean wordBreak(String s, List<String> wordDict) {
        //Breadth-First-Search
        Set<String> set = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[s.length()];
        queue.add(0);
        while(!queue.isEmpty()){
            int start = queue.remove();
            if(visited[start]  == 0){
                for (int end = start + 1; end <= s.length(); end++){
                    if(set.contains(s.substring(start,end))){
                        queue.add(end);
                        if(end == s.length())
                            return true;
                    }
                }
                visited[start] = 1;
            }

        }
        return false;
    }

    /*

    Time complexity : O(n^2). For every starting index, the search can continue till the end of the given string.

    Space complexity : O(n). Queue of atmost nnn size is needed.

*/
    // dp approach time O(n^2), space O(n)
    public static boolean wordBreakdp(String s, List<String> wordDict){
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // initial state
        for ( int i = 1; i <=s.length(); i++){
            for (int j= 0; j < i; j++){
                if(dp[j] && set.contains(s.substring(j,i))){
                    dp[i]= true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        String s = "goodyear";
        List<String> dict = new ArrayList<>(Arrays.asList("good","bad","year"));
        System.out.println(wordBreakdp(s,dict));
        System.out.println(wordBreak(s,dict));
    }
}

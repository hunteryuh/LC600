package com.alg.dp;

import java.util.*;

/*

https://leetcode.com/problems/word-break/
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
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
        boolean[] visited = new boolean[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.poll();
            if (!visited[start]) {
                for (int end = start + 1; end <= s.length(); end++){
                    if (set.contains(s.substring(start, end))) {
                        queue.add(end);
                        if (end == s.length())
                            return true;
                    }
                }
                visited[start] = true;
            }

        }
        return false;
    }

    /*

    Time complexity : O(n^3). For every starting index, the search can continue till the end of the given string.
    Space complexity : O(n). Queue of at most n size is needed.

*/
    // dp approach time O(n^3), space O(n) , n : length of s
    public static boolean wordBreakdp(String s, List<String> wordDict){
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // initial state
        for (int i = 1; i <= s.length(); i++){
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j,i))) {
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

    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0139.%E5%8D%95%E8%AF%8D%E6%8B%86%E5%88%86.md
    // 时间复杂度：O(n^3)，因为substr返回子串的副本是O(n)的复杂度（这里的n是substring的长度）
    //空间复杂度：O(n)
    public boolean wordBreakDp2(String s, List<String> wordDict) {
        // unbounded, WITH order, multiple times, so j small-big
        // knapsack: s length
        // item: string in wordDict
        // 本题最终要求的是是否都出现过，所以对出现单词集合里的元素是组合还是排列，并不在意！
        //
        //那么本题使用求排列的方式，还是求组合的方式都可以。
        //
        //即：外层for循环遍历物品，内层for遍历背包 或者 外层for遍历背包，内层for循环遍历物品 都是可以的。
        //
        //但本题还有特殊性，因为是要求子串，最好是遍历背包放在外循环，将遍历物品放在内循环。
        // dp[i] : 字符串长度为i的话，dp[i]为true，表示可以拆分为一个或多个在字典中出现的单词。
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;
        for (int j = 1; j <= length; j++) { // 遍历背包
            for (int i = 0; i < j; i++) { //遍历 物品
                if (wordDict.contains(s.substring(i, j))) {
                    dp[j] = dp[j] || dp[i];
                }
            }
        }
        return dp[length];
    }

    // tested loop with bag first then loop item, it passed leetcode tests as well
    public boolean wordBreak2(String s, List<String> wordDict) {
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;

        // no order
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                if (wordDict.contains(s.substring(i, j))) {
                    dp[j] |= dp[i];
                }
            }
        }
        return dp[length];
    }

    // dfs without memoization, time limit exceeded.
    public boolean wordBreakDfs(String s, List<String> wordDict) {
        return dfs(s, new HashSet<>(wordDict), 0);
    }
    private boolean dfs(String s, Set<String> words, int start) {
        if (start == s.length()) {
            return true;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String word = s.substring(start, i);
            if (words.contains(word)) {
                if (dfs(s, words, i)) {
                    return true;
                };
            }
        }
        return false;
    }

    // dfs with memoization
    //https://leetcode.com/problems/word-break/discuss/43941/3ms-Java-DFS-solution-beats-90
    // https://leetcode.com/problems/word-break/discuss/1587690/Java-DFS-beats-94-on-timespace-wsimple-optimizations
    public boolean wordBreakDfsm(String s, List<String> wordDict) {
        boolean[] visited = new boolean[s.length()];
        return dfsm(s, new HashSet<>(wordDict), 0, visited);
    }
    private boolean dfsm(String s, Set<String> words, int start, boolean[] visited) {
        if (start == s.length()) {
            return true;
        }
        if (visited[start]) {
            return false;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String word = s.substring(start, i);
            if (words.contains(word)) {
                if (dfsm(s, words, i, visited)) {
                    // Unnecessary to memoize anything here since we just found the result!
                    return true;
                }
            }
        }
        // visited[start] default to false, mark it as true to indicate we have checked this starting position and did not find a solution
        visited[start] = true;
        return false;
    }
    // dfs with memoization
    // https://leetcode.com/problems/word-break/solutions/127450/word-break/
    public boolean wordBreak5(String s, List<String> wordDict) {
        return wordBreakMemo(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }

    private boolean wordBreakMemo(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && wordBreakMemo(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }
}

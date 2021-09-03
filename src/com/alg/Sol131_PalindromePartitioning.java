package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HAU on 11/30/2017.
 */
/*Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]*/
public class Sol131_PalindromePartitioning {
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++){
            for (int j = 0; j <= i ; j++){
                if (s.charAt(i) == s.charAt(j) && ( i-j<=2 || dp[j+1][i-1])){
                    dp[j][i] = true;
                }
            }
        }
        helper(res,new ArrayList<>(), dp,s,0);
        return res;
    }

    private static void helper(List<List<String>> res, List<String> list, boolean[][] dp, String s, int pos) {
        if ( pos == s.length()){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = pos; i<s.length();i++){
            if(dp[pos][i]){
                list.add(s.substring(pos,i+1));
                helper(res,list,dp,s,i+1);
                list.remove(list.size()-1);
            }

        }
    }

    public static void main(String[] args) {
        String s = "abbac";
//        System.out.println(partition(s));

        Sol131_PalindromePartitioning ss = new Sol131_PalindromePartitioning();
        String w = "cbbbcc";
        ss.partitionPalin(w);
    }

    // https://www.jiuzhang.com/problem/palindrome-partitioning/
    // a 2nd way with backtracking, no dp used for isPal check
    public List<List<String>> partitionPal(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> curList = new ArrayList<String>();
        doPart(s, result, curList,0);
        return result;
    }

    private void doPart(String s, List<List<String>> result, List<String> curList, int pos) {
        int len = s.length();
        if (pos == len) {
            result.add(new ArrayList<>(curList));
            return;
        }
        for (int i = pos; i < len; i++){
            if (isPal(s, pos, i)){
                curList.add(s.substring(pos, i + 1));
                doPart(s, result, curList, i + 1);
                curList.remove(curList.size() - 1);
            }
        }
    }

    private boolean isPal(String s, int j, int k) {
        if (j == k) return true;
        while (j < k){
            if(s.charAt(j) != s.charAt(k)) {
                return false;
            }
            j++;
            k--;
        }
        return true;
    }

    boolean[][] subPalinFlag;
    public List<List<String>>  partitionPalin(String s) {
        List<List<String>> res = new ArrayList<>();
        List<Integer> sol = new ArrayList<>();
        calculateSubPalin(s);
        System.out.println(Arrays.deepToString(subPalinFlag));
        dfs(s, 0, res, sol);
        return res;
    }

    private void calculateSubPalin(String s) {
        int n = s.length();
        subPalinFlag = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            subPalinFlag[i][i] = true;
        }
        for (int i = 0; i < n - 1; i++) {
            subPalinFlag[i][i+1] = s.charAt(i) == s.charAt(i + 1);
        }
//        for (int i = 0; i < n - 1; i++) { //failed as i,j results depends on i+1, j-1, so need to calculate large i first
        for (int i = n-3; i >= 0; i--) {
            for(int j = i + 2; j < n; j++) {
                subPalinFlag[i][j] = subPalinFlag[i+1][j-1] && s.charAt(i) == s.charAt(j);
            }
        }
    }

    private void dfs(String s, int start, List<List<String>> res, List<Integer> sol) {
        if (start == s.length()) {
            getResults(s, sol, res);
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (subPalinFlag[start][i]) {
                sol.add(i);
                dfs(s, i + 1, res, sol);
                sol.remove(sol.size() - 1);
            }
        }
    }

    private void getResults(String s, List<Integer> sol, List<List<String>> res) {
        int start = 0;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < sol.size(); i++) {
            int pos = sol.get(i);
            result.add(s.substring(start, pos + 1));
            start = pos + 1;
        }
        res.add(result);
    }

}

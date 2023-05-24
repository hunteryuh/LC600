package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAU on 11/19/2017.
 */
/*Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]*/

public class Sol77_Combinations {
    public static List<List<Integer>> combine(int n, int k) {
        /*这是一道 NP 难问题，时间复杂度没办法提高，用一个循环递归处理子问题，问题的终止条件是每个组合中的元素个数达到 k 个
        * dfs  backtracking*/
        List<List<Integer>> res = new ArrayList<>();
        if ( n < k || n <= 0) return res;
        List<Integer> temp = new ArrayList<>();
        dfs(res, temp, n, k, 1);
        return res;
    }

    private static void dfs(List<List<Integer>> res, List<Integer> temp, int n, int k, int start) {
        if (k == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= n; i++){
            temp.add(i);
            dfs(res, temp, n,k-1,i+1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 4, k = 3;
        System.out.println(combine(n,k));
        System.out.println(combine2(n,k));

        Sol77_Combinations ss = new Sol77_Combinations();
        ss.rollDice(2);
    }
    // methods 2, slightly different in the dfs
    public static List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if ( n < k || n <= 0) return res;
        List<Integer> list = new ArrayList<>();
        dfs2(res, list, n, k, 1);
        return res;
    }

    private static void dfs2(List<List<Integer>> res, List<Integer> list, int n, int k, int pos) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = pos; i <= n; i++){
            list.add(i);
            dfs2(res,list,n,k,i+1);
            list.remove(list.size() - 1);
        }
    }

    // not using global variable, added optimization for the "breadth" search of the for loop
    public List<List<Integer>> combine3(int n, int k) {
        List<Integer> sol = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs3(n, k, sol, res, 1);
        return res;
    }

    private void dfs3(int n, int k, List<Integer> sol, List<List<Integer>> res, int start) {
        if (sol.size() == k) {
            res.add(new ArrayList<>(sol));
            return;
        }
//        for (int i = start; i <=n; i++) {
        for (int i = start; i <=n - (k - sol.size()) + 1; i++) {  // optimized, i can start at most n - (k-existing size) + 1
            sol.add(i);
            dfs3(n, k, sol, res, i + 1);
            sol.remove(sol.size() - 1);
        }
    }

    // doordash dice roll dice
    // Write a function that takes n number of dice/die and returns all possible permutations of result. For example, when you have n=2 dice, we want to return: [(1,1), (1,2),(1,3)...(6,5),(6,6)]
    public List<List<Integer>> rollDice(int n) {
        List<Integer> sol = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        roll(n, sol, res);
        System.out.println(res);
        return res;
    }

    private void roll(int n, List<Integer> sol, List<List<Integer>> res) {
        if (n == 0) {
            res.add(new ArrayList<>(sol));
            return;
        }
        for (int i = 1; i <= 6; i++) {
            sol.add(i);
            roll(n - 1, sol, res);
            sol.remove(sol.size() - 1);
        }
    }

}

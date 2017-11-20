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
        if ( k == 0){
            res.add(new ArrayList<>(temp));
            return;
        }
        for ( int i = start; i <= n; i++){
            temp.add(i);
            dfs(res,temp,n,k-1,i+1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 4, k = 3;
        System.out.println(combine(n,k));
        System.out.println(combine2(n,k));
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
        if(list.size() == k){
            res.add(new ArrayList<>(list));
            return;
        }
        for ( int i = pos; i <= n; i++){
            list.add(i);
            dfs2(res,list,n,k,i+1);
            list.remove(list.size() - 1);
        }
    }
}

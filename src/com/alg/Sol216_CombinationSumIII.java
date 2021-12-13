package com.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HAU on 11/19/2017.
 */
/*Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]*/
public class Sol216_CombinationSumIII {
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> sol = new ArrayList<>();
        dfshelper(res,sol,k,1,n);
        return res;
    }

    private static void dfshelper(List<List<Integer>> res, List<Integer> sol, int k, int start, int target) {
        if (sol.size() == k && target == 0){
            res.add(new ArrayList<>(sol));
            return;
        }
        for (int i = start; i <= 9; i++){
            sol.add(i);
            dfshelper(res,sol,k,i+1,target-i);
            sol.remove(sol.size() - 1);  // backtracking
        }
    }

    public static void main(String[] args) {
        int k = 3, n = 9;
        System.out.println(combinationSum3(k,n));
    }

    public List<List<Integer>> combinationSum3_Opt(int k, int n) {
        List<Integer> sol = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (k < 0 || n < 0) {
            return res;
        }

        dfs(k, n, sol, res, 1);
        return res;
    }

    private void dfs(int k, int n, List<Integer> sol, List<List<Integer>> res, int start) {
        if (n < 0) {
            return;
        } // 剪枝1
        if ( n> 0 && sol.size() == k) {
            return;
        } // 剪枝2
        if (sol.size() == k && n == 0) {
            res.add(new ArrayList<>(sol));
            return;
        }

        for (int i = start; i <= 9; i++) {
            sol.add(i);
            // not using sum += i and n - sum as it adds one at a time
            dfs(k, n - i, sol, res, i + 1);

            sol.remove(sol.size()-1);
        }
    }

    // approach 3, keep target sum but change sum
    public List<List<Integer>> combinationSum3_3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        backTracking(n, k, 1, 0, result, path);
        return result;
    }

    private void backTracking(int targetSum, int k, int startIndex, int sum, List<List<Integer>> result, LinkedList<Integer> path) {
        // 减枝
        if (sum > targetSum) {
            return;
        }

        if (path.size() == k) {
            if (sum == targetSum) result.add(new ArrayList<>(path));
            return;
        }

        // 减枝 9 - (k - path.size()) + 1
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            sum += i;
            backTracking(targetSum, k, i + 1, sum, result, path);
            //回溯
            path.removeLast();
            //回溯
            sum -= i;
        }
    }
}

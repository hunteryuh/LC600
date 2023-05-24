package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by HAU on 7/22/2017.
 */
/*
Given a collection of candidate numbers (C) and a target number (T),
find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.*/

// https://www.jiuzhang.com/problem/combination-sum-ii/
public class Sol40_CombinationSumII {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target){
        ArrayList<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        ArrayList<Integer> subset = new ArrayList<>();
        helper(candidates, 0, subset, target, result);
        return result;

    }

    private static void helper(int[] candidates, int index, ArrayList<Integer> subset, int target, ArrayList<List<Integer>> result) {
        if (target == 0) {
            ArrayList<Integer> tmp = new ArrayList<>(subset);
            result.add(tmp);
            return;
        }
        for (int i = index; i < candidates.length; i++){
            if (candidates[i] > target) return;  // array is sorted
            // "continue the while loop" by satisfied "if (i > cur && cand[i] == cand[i - 1])".
            //You see, in your case [1,1,1], if you need a sum of 2, you will not skip the third 1 because
            // it does not satisfy the condition "i > cur".
            // What I mean in my first comment, for example, [1,1,1,2], we want a sum of 4, we have add [1, 1(second), 2] to the result set,
            // then back from the recursion, when i > cur, that is, i point to the third 1, this time we skip the third 1,
            // just to avoid another [1,1(third), 2] added to the result set.
            // 要对同一树层使用过的元素进行跳过
            if (i > index && candidates[i] == candidates[i - 1]) continue; // candidate may have duplicate numbers
            subset.add(candidates[i]);
            helper(candidates,i + 1, subset,target - candidates[i], result);  // i + 1, only be used once
            subset.remove(subset.size() - 1);
        }

    }

    public static void main(String[] args) {
        int[] nums = { 10,1,2,1,6,5,7};
        int t = 8;
//        System.out.println(combinationSum2(nums,t));  // can print list directly
        Sol40_CombinationSumII s = new Sol40_CombinationSumII();
        List<List<Integer>> res = s.combinationSum22(nums, t);
        System.out.println(res);
    }

    public List<List<Integer>> combinationSum22(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> sol = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, sol, target, results, 0);
        return results;
    }
    private void dfs(int[] candidates, List<Integer> sol, int target, List<List<Integer>> results, int start) {
        if (target == 0) {
            List<Integer> temp = new ArrayList<>(sol);
            results.add(temp);
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break; // otherwise time exceeded
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            sol.add(candidates[i]);
            dfs(candidates, sol, target - candidates[i], results, i + 1);
            sol.remove(sol.size() - 1);
        }
    }

    // use set for each layer de-duplication, no need of used[]
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        LinkedList<Integer> sol = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target < 0) {
            return res;
        }
        Arrays.sort(candidates);
//        boolean[] used = new boolean[candidates.length];
        dfs3(candidates, target, sol, res, 0, 0);
        return res;
    }

    private void dfs3(int[] candidates, int target, LinkedList<Integer> sol, List<List<Integer>> res, int start, int sum) {
        if (sum == target) {
            res.add(new ArrayList<>(sol));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = start; i < candidates.length; i++) {
            if (set.contains(candidates[i])) {
                continue;
            }
            sum += candidates[i];
            if (sum > target) return;
            set.add(candidates[i]);
//            used[i] = true;
            sol.addLast(candidates[i]);
            dfs3(candidates, target, sol, res, i + 1, sum);
            sum -= candidates[i];
//            used[i] = false;
            sol.removeLast();
        }
    }

    public List<List<Integer>> combinationSum4(int[] candidates, int target) {
        LinkedList<Integer> sol = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target < 0) {
            return res;
        }
        Arrays.sort(candidates);
        boolean[] used = new boolean[candidates.length];
        dfs4(candidates, target, sol, res, 0, 0, used);
        return res;
    }

    private void dfs4(int[] candidates, int target, LinkedList<Integer> sol, List<List<Integer>> res, int start, int sum, boolean[] used) {
        if (sum == target) {
            res.add(new ArrayList<>(sol));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // used[i - 1] == true，说明同一树支candidates[i - 1]使用过
            // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
            // 要对同一树层使用过的元素进行跳过
            if (i > 0 && candidates[i] == candidates[i-1] && !used[i-1]) {
                continue;
            }
            sum += candidates[i];
            if (sum > target) return;
            used[i] = true;
            sol.addLast(candidates[i]);
            dfs4(candidates, target, sol, res, i + 1, sum, used);
            sum -= candidates[i];
            used[i] = false;
            sol.removeLast();
        }
    }

}

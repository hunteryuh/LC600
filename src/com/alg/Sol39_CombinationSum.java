package com.alg;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HAU on 7/22/2017.
 */
/*
Given a set of candidate numbers (C) (without duplicates) and
a target number (T), find all unique combinations in C where the candidate numbers sums to T.

        The same repeated number may be chosen from C unlimited number of times.

        Note:
        All numbers (including target) will be positive integers.
        The solution set must not contain duplicate combinations.
        For example, given candidate set [2, 3, 6, 7] and target 7,
        A solution set is:
[
  [7],
  [2, 2, 3]
]*/
public class Sol39_CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target){
        ArrayList<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        ArrayList<Integer> subset = new ArrayList<>();
        helper(candidates, 0, subset, target, result);
        return result;
    }

    private static void helper(int[] candidates, int index, ArrayList<Integer> subset, int target, ArrayList<List<Integer>> result) {
        if (target == 0){
            ArrayList<Integer> tmp = new ArrayList<>(subset);
            result.add(tmp);
            return;
        }
        for ( int i = index; i < candidates.length; i++){
            if (candidates[i] > target) return;
            subset.add(candidates[i]);
            //The same repeated number may be chosen from C unlimited number of times.
            helper(candidates,i,subset,target - candidates[i],result); // still start from i, not i + 1
            subset.remove(subset.size() - 1);
        }

    }

    public static void main(String[] args) {
        int[] nums = { 2,3,4,7};  // no duplicates
        int t = 7;
        System.out.println(combinationSum(nums,t));  // can print list directly
        int[] n2 = {10,1,2,7,6,1,5};
        int tt = 8;
        System.out.println(combinationSum(n2,tt));
    }

    // https://www.jiuzhang.com/problem/combination-sum/
    // assume there are duplicates in the original nums
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        int[] uniqueNums = removeDuplicates(candidates);
        List<Integer> combination = new ArrayList<>();

        dfs(uniqueNums, 0, target, combination, result);
        return result;
    }


    // 1. 递归的定义
    // 从nums中的StartIndex开始挑选一些数，放到combination中，且他们的和为target
    private static void dfs(int[] nums, int start, int remainTarget, List<Integer> combination, List<List<Integer>> result) {
        // 3. 递归的出口
        if (remainTarget == 0) {
            // deep copy
            result.add(new ArrayList<>(combination));
            return;
        }

        // 2.递归的拆解
        // try [1,2] , [1,3], [1,8]...
        for (int i = start; i < nums.length; i++) {
            if (nums[i] > remainTarget) {
                break;
            }
            // [1] -> [1,2]
            combination.add(nums[i]);
            // 把所有[1,2]开头的（剩余）和为remainTarget的集合都找到， 放到Results
            dfs(nums, i, remainTarget - nums[i], combination, result);
            // [1,2] -> [1]
            combination.remove(combination.size() - 1);
        }
    }

    private static int[] removeDuplicates(int[] candidates) {
        Arrays.sort(candidates);
        int index = 0;
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] != candidates[index]) {
                candidates[++index] = candidates[i];
            }
        }
        int[] unique = new int[index + 1];
        for (int i = 0; i < unique.length; i++) {
            unique[i] = candidates[i];
        }
        return unique;
    }
}

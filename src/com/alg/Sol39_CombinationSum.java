package com.alg;

import edu.princeton.cs.algs4.In;

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
        For example, given candidate set [2, 3, 6, 7] and target 7,*/
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
            helper(candidates,i,subset,target - candidates[i],result);
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
}

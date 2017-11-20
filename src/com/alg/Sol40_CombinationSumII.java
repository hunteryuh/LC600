package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class Sol40_CombinationSumII {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target){
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
            if (candidates[i] > target) return;  // array is sorted
            if ( i > index && candidates[i] == candidates[i - 1]) continue; // candidate may have duplicate numbers
            subset.add(candidates[i]);
            helper(candidates,i + 1,subset,target - candidates[i],result);  // i + 1, only be used once
            subset.remove(subset.size() - 1);
        }

    }

    public static void main(String[] args) {
        int[] nums = { 10,1,2,1,6,5,7};
        int t = 8;
        System.out.println(combinationSum2(nums,t));  // can print list directly
    }
}

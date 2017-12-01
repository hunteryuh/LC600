package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HAU on 11/30/2017.
 */
/*Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]*/
public class Sol47_PermutionsII {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        boolean[] used = new boolean[nums.length];
        List<Integer> sol = new ArrayList<>();

        Arrays.sort(nums);
        dfs(nums,res,sol,used);
        return res;
    }

    private static void dfs(int[] nums, List<List<Integer>> res, List<Integer> sol, boolean[] used) {
        if(sol.size() == nums.length){
            res.add(new ArrayList<>(sol));
            return;
        }
        for (int i = 0; i < nums.length ;i++){
            if(used[i]) continue;
            if(i>0 && nums[i] == nums[i-1] && !used[i-1]) continue;
            //when a number has the same value with its previous, we can use this number only if his previous is used
            used[i] = true;
            sol.add(nums[i]);
            dfs(nums,res,sol,used);
            used[i] = false;
            sol.remove(sol.size() -1 );
        }
    }

    public static void main(String[] args) {
        int[] nums= {1,2,1};
        System.out.println(permuteUnique(nums));
    }
}

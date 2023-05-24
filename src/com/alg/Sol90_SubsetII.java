package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HAU on 11/8/2017.
 */
/*Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set
must not contain duplicate subsets.

If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]*/
public class Sol90_SubsetII {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if (nums == null){
            return result;
        }
        if (nums.length == 0){
            result.add(new ArrayList<Integer>());
            return result;
        }

        Arrays.sort(nums);
        List<Integer> subset = new ArrayList<>();
        helper(nums,0,subset, result);
        return result;
    }

    private static void helper(int[] nums, int start, List<Integer> subset, ArrayList<List<Integer>> result) {
        result.add(new ArrayList<>(subset));
        for (int i = start; i < nums.length; i++){
            if (i != start && nums[i] == nums[i-1]) continue;  // key step to remove the duplicate sets in the result
            subset.add(nums[i]);
            helper(nums, i + 1, subset, result);
            subset.remove(subset.size() - 1);
        }
    }
    public static void main(String[] args) {
        int[] nums = {1,2,2};
        System.out.println(subsetsWithDup(nums) );
    }
}

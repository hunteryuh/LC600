package com.alg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*Given an integer array nums, return all the different possible increasing subsequences of the given array with at least two elements. You may return the answer in any order.

The given array may contain duplicates, and two equal integers should also be considered a special case of increasing sequence.



Example 1:

Input: nums = [4,6,7,7]
Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
Example 2:

Input: nums = [4,4,3,2,1]
Output: [[4,4]]

 */
public class Sol491_IncreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        LinkedList<Integer> sol = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        dfs(nums, sol, res, 0);
        return res;
    }

    private void dfs(int[] nums, LinkedList<Integer> sol, List<List<Integer>> res, int start) {
        if (sol.size() > 1) {
            res.add(new ArrayList<>(sol));
            // do not return as we may have sol with more numbers
        }
        Set<Integer> set = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if ((sol.size() == 0 || nums[i] >= sol.getLast()) && !set.contains(nums[i])) {
                sol.add(nums[i]);
                set.add(nums[i]);  // save the items in this layer to de-duplicate; no need to remove from the set for backtrack as it is still the same layer
                dfs(nums, sol, res, i + 1);
                sol.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 7};
        Sol491_IncreasingSubsequences ss = new Sol491_IncreasingSubsequences();
        List<List<Integer>> res = ss.findSubsequences(nums);
        System.out.println(res);
        // without de-dupe  [[4, 6], [4, 6, 7], [4, 6, 7, 7], [4, 6, 7], [4, 7], [4, 7, 7], [4, 7], [6, 7], [6, 7, 7], [6, 7], [7, 7]]
        // with de-depe [[4, 6], [4, 6, 7], [4, 6, 7, 7], [4, 7], [4, 7, 7], [6, 7], [6, 7, 7], [7, 7]]
    }
}


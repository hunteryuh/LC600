package com.alg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by HAU on 6/13/2017.
 */
/*Given a collection of distinct numbers, return all possible permutations.

        For example,
        [1,2,3] have the following permutations:
        [
        [1,2,3],
        [1,3,2],
        [2,1,3],
        [2,3,1],
        [3,1,2],
        [3,2,1]
        ]*/
public class Sol46_Permutations {
    public static List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), nums);
        return res;
    }

    // https://www.jiuzhang.com/problem/permutations/
    private static void helper(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length){
            res.add(new ArrayList<>(list));
        }else{
            for( int i = 0; i < nums.length; i++){
                if (list.contains(nums[i])) continue;  // go to the next iteration
                list.add(nums[i]);
                helper(res,list,nums);
                list.remove(list.size()-1);  //backtracking

            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,0};
//        System.out.println(permute(nums));

        Sol46_Permutations s = new Sol46_Permutations();
//        System.out.println(s.permutet(nums));
    }

    // time complexity n * n * n!
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> pm = new ArrayDeque<>();
        dfs(result, pm, nums);
        return result;
    }

    private void dfs(List<List<Integer>> res, Deque<Integer> pm, int[] nums) {

        if (pm.size() == nums.length) {
            res.add(new ArrayList<>(pm));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!pm.contains(nums[i])) {  // time complexity of contains: O(n)
                pm.addLast(nums[i]);
                dfs(res, pm, nums);
                pm.removeLast();
            }
        }
    }

    // optimize time: use a visited array []
    public List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> pm = new ArrayDeque<>();
        boolean[] visited = new boolean[nums.length];
        dfs(result, pm, nums, visited);
        return result;
    }

    private void dfs(List<List<Integer>> res, Deque<Integer> pm, int[] nums, boolean[] visited) {

        if (pm.size() == nums.length) {
            res.add(new ArrayList<>(pm));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                pm.addLast(nums[i]);
                visited[i] = true;
                dfs(res, pm, nums, visited);
                pm.removeLast();
                visited[i] = false;
            }
        }
    }

//    public List<List<Integer>> permutet(int[] nums) {
//        // write your code here
//        List<List<Integer>> res = new ArrayList<>();
//        List<Integer> sol = new ArrayList<>();
//        dfst(res, sol, nums, 0);
//        return res;
//    }
//
//    private void dfst(List<List<Integer>> res, List<Integer> sol, int[] nums, int start) {
//        if (sol.size() == nums.length) {
//            res.add(new ArrayList<>(sol));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {  // needs to start with 0, so no need to pass "start"
//            if (!sol.contains(nums[i])) {
//                sol.add(nums[i]);
//                dfst(res, sol, nums, i + 1);
//                sol.remove(sol.size() - 1);
//            }
//
//        }
//    }
}

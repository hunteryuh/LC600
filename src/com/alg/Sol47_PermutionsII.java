package com.alg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
/*求全排列，但元素可能有重复。去重复就成为了关键。今天好好思考了一下dfs+回溯，比如1134，
最外层就是求出第一个元素，比如 1， 2， 3， 里面的嵌套dfs再负责第二，三，四个元素。
去重复的方法是传递一个visited数组，把排序后相同的元素看成一个cluster，假如nums[i] == nums[i - 1]，
但i-1没有被访问过，说明整个cluster不被访问，跳过整个cluster。

Time complexity - O(n*n!), Space Complexity - O(n)。*/
    private static void dfs(int[] nums, List<List<Integer>> res, List<Integer> sol, boolean[] used) {
        if (sol.size() == nums.length) {
            res.add(new ArrayList<>(sol));
            return;
        }
        for (int i = 0; i < nums.length ;i++){
            if(used[i]) continue;
            if(i>0 && nums[i] == nums[i-1] && !used[i-1]) continue;
            //when a number has the same value with its previous,
            // we can use this number only if its previous is used
            /*比如，给出一个排好序的数组，[1,2,2]，那么第一个2和第二2如果在结果中互换位置，
            我们也认为是同一种方案，所以我们强制要求相同的数字，原来排在前面的，在结果
            当中也应该排在前面，这样就保证了唯一性。所以当前面的2还没有使用的时候，就
            不应该让后面的2使用。*/
            used[i] = true;
            sol.add(nums[i]);
            dfs(nums,res,sol,used);
            used[i] = false;
            sol.remove(sol.size() -1 );
        }
    }

    // https://www.jiuzhang.com/problem/permutations-ii/
    public static void main(String[] args) {
        int[] nums= {1,2,1};
        System.out.println(permuteUnique(nums));
        Sol47_PermutionsII ss = new Sol47_PermutionsII();
        System.out.println(ss.permuteUnique3(nums));
    }

    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Deque<Integer> pm = new ArrayDeque<>();
        Arrays.sort(nums);  // need to sort first
        dfs2(res, pm, nums, visited);
        return res;
    }

    private void dfs2(List<List<Integer>> res, Deque<Integer> pm, int[] nums, boolean[] visited) {
        if (pm.size() == nums.length) {
            res.add(new ArrayList<>(pm));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            if (i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;

            pm.add(nums[i]);
            visited[i] = true;
            dfs2(res, pm, nums, visited);
            pm.removeLast();
            visited[i] = false;
        }
    }

    // use set for de-duplication at each layer
    public List<List<Integer>> permuteUnique3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Deque<Integer> pm = new ArrayDeque<>();
//        Arrays.sort(nums);  // no need to sort first if using a set to do de-duplication
        dfs3(res, pm, nums, visited);
        return res;
    }

    private void dfs3(List<List<Integer>> res, Deque<Integer> pm, int[] nums, boolean[] visited) {
        if (pm.size() == nums.length) {
            res.add(new ArrayList<>(pm));
            return;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) continue;
            if (visited[i]) continue;
            set.add(nums[i]); // nums[i], meaning the value, not the position i

            pm.add(nums[i]);
            visited[i] = true;
            dfs3(res, pm, nums, visited);
            pm.removeLast();
            visited[i] = false;
        }
    }
}

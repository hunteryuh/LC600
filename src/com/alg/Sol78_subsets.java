package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HAU on 6/10/2017.
 */
/*Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]*/


public class Sol78_subsets {

    // list 是个接口， arraylist是个具体 的实现， linkedlist是另一种，它的Add跟remove与arraylist不同
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        if (nums.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
//        Arrays.sort(nums); // no need to sort if no duplicate

        //[1,2] == [2,1], no duplicate subsets
        // find all subsets starting from empty sets
        // all subsets start from empty set []
        // all string start with ""  ( empty string)
        List<Integer> subset = new ArrayList<>();
        helper(nums, 0, subset, result);

        return result;

    }

    // 1. definition of recursion: in nums, find all subsets starting from subset, put in results
    private static void helper(int[] nums,
                               int offset,
                               List<Integer> subset,
                               List<List<Integer>> result){
        // 2. break-down of recursion
        // wrong: result.add(subset)
        // clone operation:  deep copy
        result.add(new ArrayList<>(subset)); // ??

        if (offset >= nums.length) { //终止条件可不加 因为 offset >= nums.size()，本层for循环本来也结束了。
            return;
        }

        for (int i = offset; i < nums.length; i++) {
            subset.add(nums[i]);
            // [] -> [1]
            // find all subsets starting from [1]
            helper(nums,i + 1, subset, result);
            // now subset is [1]
            // do backtracking: [1]->[]
            subset.remove(subset.size()-1);
        }

        //3. outlet of recursion
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1));
        List<Integer> list3 = new ArrayList<>(Arrays.asList(1,2));
        List<Integer> list4 = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> list5 = new ArrayList<>(Arrays.asList(1,3));
        List<Integer> list6 = new ArrayList<>(Arrays.asList(2));
        List<Integer> list7 = new ArrayList<>(Arrays.asList(2,3));
        List<Integer> list8 = new ArrayList<>(Arrays.asList(3));
        ArrayList<List<Integer>> res = new ArrayList<>
                (Arrays.asList(list1,list2,list3,list4,list5,list6
                    ,list7,list8));
                //new ArrayList<List<Integer>>();

        List<List<Integer>> ref = subsets(nums);
        assert ref.equals(res);
        System.out.println(ref);
        System.out.println(res);
    }

    public List<List<Integer>> subsets2(int[] nums) {
        LinkedList<Integer> sol = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
//        if (nums == null || nums.length == 0) return res; // can remove, it still passes

        dfs(nums, res, sol, 0);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, LinkedList<Integer> sol, int start) {
        res.add(new ArrayList<>(sol));

        for (int i = start; i < nums.length; i++) {
            sol.add(nums[i]);
            dfs(nums, res, sol, i + 1);
            sol.removeLast();
        }
    }
}

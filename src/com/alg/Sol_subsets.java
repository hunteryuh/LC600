package com.alg;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by HAU on 6/10/2017.
 */
/*recursion (program implementation): function call the function itself 程序实现： 函数 的自我调用
recursion 算法 ： 如何 把规模为N的问题，通过 处理之后，变成规模为N-1的问题*/
// depth first search : standard solution for problems of "all possible"
    // backtracking  回溯, one step for depth first search

//bread first search, layer + layer enumeration, not so good for this case
    // space needed is big for first layer


public class Sol_subsets {

    public static ArrayList<ArrayList<Integer>> subsets(int[] nums){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (nums == null){
            return result;
        }
        if (nums.length == 0){
            result.add(new ArrayList<Integer>());
            return result;
        }

        Arrays.sort(nums);

        //[1,2] == [2,1], no duplicate subsets
        // find all subsets starting from empty sets
        // all subsets start from empty set []
        // all string start with ""  ( empty string)
        ArrayList<Integer> subset = new ArrayList<>();
        helper(nums, 0, subset, result);

        return result;

    }

    // 1. definition of recursion: in nums, find all subsets starting from subset, put in results
    private static void helper(int[] nums,
                               int offset,
                               ArrayList<Integer> subset,
                               ArrayList<ArrayList<Integer>> result){
        // 2. break-down of recursion
        // wrong: result.add(subset)
        // clone operation:  deep copy
        result.add(new ArrayList<Integer>(subset));//??

        for (int i = offset; i < nums.length; i++){
            subset.add(nums[i]);
            // [] -> [1]
            // find all subsets starting from [1]
            helper(nums,i+1, subset, result);
            // now subset is [1]
            // do backtracing: [1]->[]
            subset.remove(subset.size()-1);
        }

        //3. outlet of recursion
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subsets(nums) );
    }
}

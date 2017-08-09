package com.alg;

import java.util.ArrayList;
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
        int[] nums = {1,2,3};
        //System.out.println(nums);
        System.out.println(permute(nums));

    }

}

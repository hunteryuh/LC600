package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAU on 11/19/2017.
 */
/*Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]*/
public class Sol216_CombinationSumIII {
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> sol = new ArrayList<>();
        dfshelper(res,sol,k,1,n);
        return res;
    }

    private static void dfshelper(List<List<Integer>> res, List<Integer> sol, int k, int start, int target) {
        if (sol.size() == k && target == 0){
            res.add(new ArrayList<>(sol));
            return;
        }
        for (int i = start; i <= 9; i++){
            sol.add(i);
            dfshelper(res,sol,k,i+1,target-i);
            sol.remove(sol.size() - 1);  // backtracking
        }
    }

    public static void main(String[] args) {
        int k = 3, n = 9;
        System.out.println(combinationSum3(k,n));
    }
}

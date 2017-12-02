package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAU on 12/1/2017.
 */
/*Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.

Write a function that takes an integer n and return all possible combinations of its factors.
 Note:

    You may assume that n is always positive.
    Factors should be greater than 1 and less than n.
*/
public class Sol254_FactorCombinations {
    public static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfshelper(res,list,n,2);
        return res;
    }

    private static void dfshelper(List<List<Integer>> res, List<Integer> list, int n, int start) {
        if ( n<= 1){
            if(list.size() >1){  // do not add n itself as factors
                res.add(new ArrayList<>(list));
            }
            return;
        }
        for (int i = start; i <= n; i++){
            if ( n % i == 0){
                list.add(i);
                dfshelper(res,list,n/i,i);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int t = 40;
        System.out.println(getFactors(t));
        // 40 : [[2, 2, 2, 5], [2, 2, 10], [2, 4, 5], [2, 20], [4, 10], [5, 8]]
    }
}

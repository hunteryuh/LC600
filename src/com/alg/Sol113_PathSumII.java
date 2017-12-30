package com.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HAU on 11/30/2017.
 */
/*Given a binary tree and a sum, find all root-to-leaf paths where each path's sum
equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]*/
public class Sol113_PathSumII {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        //dfs
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> sol = new LinkedList<>();
        dfs(root,sum,sol,res);
        return res;
    }

    private static void dfs(TreeNode root, int sum, List<Integer> sol, List<List<Integer>> res) {
        if(root == null) return;
        sol.add(root.val);
        if( root.left == null && root.right == null && sum == root.val){
            res.add(new LinkedList<>(sol));
            // no return here;
            //sol.remove(sol.size() - 1);
        }else{
            dfs(root.left,sum - root.val,sol, res);
            dfs(root.right,sum - root.val,sol, res);
        }
        sol.remove(sol.size() - 1); // remove the last integer
    }
}

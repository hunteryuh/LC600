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
        dfs(root, sum, sol, res);
        return res;
    }

    private static void dfs(TreeNode root, int sum, List<Integer> sol, List<List<Integer>> res) {
        if (root == null) return;
        sol.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            res.add(new LinkedList<>(sol));
            // no return here;
            //sol.remove(sol.size() - 1);
        } else {
            dfs(root.left,sum - root.val,sol, res);
            dfs(root.right,sum - root.val,sol, res);
        }
        sol.remove(sol.size() - 1); // remove the last integer
    }

    private static void dfs2(TreeNode root, int sum, List<Integer> sol, List<List<Integer>> res) {
        if (root == null) return;
        sol.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            res.add(new LinkedList<>(sol));
            // no return here;
            //sol.remove(sol.size() - 1);
        }
        dfs2(root.left,sum - root.val,sol, res);
        dfs2(root.right,sum - root.val,sol, res);

        sol.remove(sol.size() - 1); // remove the last integer
    }

    /*
    https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0112.%E8%B7%AF%E5%BE%84%E6%80%BB%E5%92%8C.md
    如果需要搜索整颗二叉树且不用处理递归返回值，递归函数就不要返回值。（这种情况就是本文下半部分介绍的113.路径总和ii）
如果需要搜索整颗二叉树且需要处理递归返回值，递归函数就需要返回值。 （这种情况我们在236. 二叉树的最近公共祖先中介绍）
如果要搜索其中一条符合条件的路径，那么递归一定需要返回值，因为遇到符合条件的路径了就要及时返回。（112 pathSum 的情况）
     */

    public List<List<Integer>> pathSums(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<Integer> sol = new LinkedList<>();
        preorderdfs(root, sum, res, sol);
        return res;
    }

    private void preorderdfs(TreeNode root, int target, List<List<Integer>> res, LinkedList<Integer> sol) {
        sol.add(root.val);
        if (root.left == null && root.right == null && target == root.val) {
            res.add(new ArrayList<>(sol));
            return;
        }
        if (root.left != null) {
            preorderdfs(root.left, target - root.val, res, sol);
            sol.removeLast();  // 回溯
        }
        if (root.right != null) {
            preorderdfs(root.right, target - root.val, res, sol);
            sol.removeLast();  // 回溯
        }
    }
}

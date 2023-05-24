package com.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by HAU on 2/4/2018.
 */
/*Given a binary tree, return the bottom-up level order traversal
of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]*/
public class Sol107_BinaryTreeLevelOrderTraversalII {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        //bfs approach
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        queue.offer(root);
        while(!queue.isEmpty()){
            int levelNum = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < levelNum;i++){
                TreeNode cur = queue.poll();
                level.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(0, level);
        }
        return res;
    }
    // dfs solution
    public static List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        levelMaker(res, root, 0);
        return res;
    }

    private static void levelMaker(List<List<Integer>> res, TreeNode root, int level) {
        if (root == null) return;
        if (level >= res.size()){
            res.add(0,new LinkedList<>());// linkedlist add O(1) in time
        }
        levelMaker(res,root.left,level + 1);
        levelMaker(res,root.right,level + 1);
        res.get(res.size() - level - 1).add(root.val);
    }

    public List<List<Integer>> levelOrderBottom3(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        if (root == null){
            return res;
        }

        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            res.add(level);
        }

        List<List<Integer>> sol = new ArrayList<>();
        int n = res.size();
        for (int i = n - 1; i >= 0; i--) {
            sol.add(res.get(i));
        }
        return sol;
    }
}

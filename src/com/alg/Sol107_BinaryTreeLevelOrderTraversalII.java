package com.alg;

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
        List<List<Integer>> res = new LinkedList<>();
        if( root == null){
            return res;
        }
        queue.add(root);
        while(!queue.isEmpty()){
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<>();
            for(int i = 0; i < levelNum;i++){
                if(queue.peek().left != null){
                    queue.add(queue.peek().left);
                }
                if(queue.peek().right != null){
                    queue.add(queue.peek().right);
                }
                subList.add(queue.poll().val);
            }
            res.add(0,subList);
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
        if ( root == null) return;
        if(level >= res.size()){
            res.add(0,new LinkedList<>());// linkedlist add O(1) in time
        }
        levelMaker(res,root.left,level + 1);
        levelMaker(res,root.right,level + 1);
        res.get(res.size() - level - 1).add(root.val);
    }
}

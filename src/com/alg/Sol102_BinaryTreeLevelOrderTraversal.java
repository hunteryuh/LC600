package com.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by HAU on 11/5/2017.
 */
/*Given a binary tree, return the level order traversal of its nodes' values.
(ie, from left to right, level by level).*/
/*Given binary tree [3,9,20,null,null,15,7],
*
*
* [
  [3],
  [9,20],
  [15,7]
]*/
public class Sol102_BinaryTreeLevelOrderTraversal {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    public static List<List<Integer>> levelOrder(TreeNode root) {
        // using dfs, recursion
        List<List<Integer>> res = new ArrayList<>();
        levelhelper(root, res, 0);
        return res;
    }

    private static void levelhelper(TreeNode root, List<List<Integer>> res, int depth) {
        if (root == null) return ;
        if (depth == res.size()){
            res.add(new ArrayList<>());
        }
        res.get(depth).add(root.val);
        levelhelper(root.left, res,depth + 1);
        levelhelper(root.right, res, depth + 1);
    }

    public static void main(String[] args) {
        //      1
        //    /   \
        //   2     3
        //  / \   /
        // 4   5 6
        //    /
        //   7
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node5.left = node7;
        System.out.println(levelOrder(node1));
        System.out.println(levelOrd2(node1));
    }

    //method 2, BFS but with queue, first in first out
    public static List<List<Integer>> levelOrd2(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()){
            List<Integer> tmp = new ArrayList<>();
            int size = q.size(); // store the size of the current level
            for (int i = 0; i < size; i++) {
                // cannot use i < queue.size() as the size changes in the loop, so need to cache the size beforehand
                TreeNode node = q.poll();  // get the head node of the queue
                tmp.add(node.val);
                if (node.left != null){
                    q.offer(node.left);
                }
                if (node.right!= null){
                    q.offer(node.right);
                }
            }
            res.add(tmp);
        }
        return res;
    }
}

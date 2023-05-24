package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by HAU on 8/26/2017.
 */
/*
Given two binary trees, write a function to check if they are equal or not.

        Two binary trees are considered equal if they are structurally
        identical and the nodes have the same value.*/
public class Sol100_sameTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    public static boolean isSameTree(TreeNode p, TreeNode q){
        if (p == null && q == null) return true;
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // bfs
    public boolean isSameTree_bfs(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while (!queue.isEmpty()) {
            TreeNode first = queue.poll();
            TreeNode second = queue.poll();
            if (first == null && second == null) continue;
            if (first == null || second == null) return false;
            if (first.val != second.val) return false;
            queue.offer(first.left);
            queue.offer(second.left);
            queue.offer(first.right);
            queue.offer(second.right);
        }
        return true;
    }
}

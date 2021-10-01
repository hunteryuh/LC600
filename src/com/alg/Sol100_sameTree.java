package com.alg;

/**
 * Created by HAU on 8/26/2017.
 */
/*
Given two binary trees, write a function to check if they are equal or not.

        Two binary trees are considered equal if they are structurally
        identical and the nodes have the same value.*/
public class Sol100_sameTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    public static boolean isSameTree(TreeNode p, TreeNode q){
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

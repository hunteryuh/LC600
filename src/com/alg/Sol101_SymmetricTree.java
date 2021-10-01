package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by HAU on 11/30/2017.
 */
/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3*


   https://leetcode.com/problems/symmetric-tree//
 */
public class Sol101_SymmetricTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        // recursive,  he total run time is O(n)O(n), where nn is the total number of nodes in the tree.
        return isMirror(root,root);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null){
            return true;
        } else if (t1 == null || t2 == null) {
            return false;
        }
        return (t1.val == t2.val && isMirror(t1.left,t2.right) && isMirror(t2.left,t1.right));
    }

    // iterative
    public boolean isSymmetric3(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode n1 = queue.poll();
            TreeNode n2 = queue.poll();
            if (n1 == null && n2 == null) {
                continue;
            }
            if (n1 == null || n2 == null || n1.val != n2.val) {
                return false;
            }
            queue.offer(n1.left);
            queue.offer(n2.right);
            queue.offer(n1.right);
            queue.offer(n2.left);
        }
        return true;
    }
    // mirror a tree
    public void mirrorTree(TreeNode root){
        if( root == null) return;
        mirrorTree(root.left);
        mirrorTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return;
    }


}

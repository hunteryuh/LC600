package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/*Invert a binary tree.
     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1*/
public class Sol226_InvertBinaryTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int x){
            val = x;
        }
    }
    public static TreeNode invertTree(TreeNode root) {
        // recursion  time O(n), space O(n)
        if ( root == null){
            return root;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }
    // iterative
    public static TreeNode mirrorTree(TreeNode root){
        if ( root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp; // swap its children
            if(cur.left != null) queue.add(cur.left);
            if(cur.right != null) queue.add(cur.right);
        }
        return root;
    }
}

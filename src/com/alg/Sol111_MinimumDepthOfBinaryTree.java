package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by HAU on 8/26/2017.
 */
/*

Given a binary tree, find its minimum depth.

        The minimum depth is the number of nodes
        along the shortest path from the root node down to
        the nearest leaf node.
*/


public class Sol111_MinimumDepthOfBinaryTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    public static int minDepth(TreeNode root){
        if ( root == null){
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if ( left == 0 || right == 0){
            return left + right + 1;
        }else{
            return Math.min(left,right) +1;
        }
    }
    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(5);
        n0.left = new TreeNode(2);
        n0.right = new TreeNode (15);
        n0.right.right = new TreeNode (25);
        System.out.println(minDepth(n0));
    }

    public int minDepthbfs(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return res;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }
}

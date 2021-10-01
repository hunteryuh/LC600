package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by HAU on 8/26/2017.
 */

/*Given a binary tree, find its maximum depth.

        The maximum depth is the number of nodes along the longest path
        from the root node down to the farthest leaf node.

        https://www.jiuzhang.com/problem/maximum-depth-of-binary-tree/
*/
public class Sol104_MaxDepthOfBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // Divide and Conquer
    public static int maxDepth(TreeNode root){
        if ( root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    private static int depthGlobal;
    // Traverse
    public static int maxDepthByTraversal(TreeNode root) {
        depthGlobal = 0;
        helper(root, 1);
        return depthGlobal;
    }

    private static void helper(TreeNode root, int currentDepth) {
        if (root == null) {
            return;
        }

        if (currentDepth > depthGlobal) {
            depthGlobal = currentDepth;
        }

        helper(root.left, currentDepth + 1);
        helper(root.right, currentDepth + 1);
    }


    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(5);
        n0.left = new TreeNode(2);
        System.out.println(maxDepth(n0));
    }

    public int maxDepthbfs(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
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

    public int maxDepthR(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = maxDepthR(root.left);
        int rightDepth = maxDepthR(root.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }
}

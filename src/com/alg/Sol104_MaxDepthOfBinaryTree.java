package com.alg;

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
}

package com.alg;

/**
 * Created by HAU on 8/26/2017.
 */

/*Given a binary tree, find its maximum depth.

        The maximum depth is the number of nodes along the longest path
        from the root node down to the farthest leaf node.*/
public class Sol104_MaxDepthOfBinaryTree {
    public static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }
   public static int maxDepth(TreeNode root){
       if ( root == null) return 0;
       int left = maxDepth(root.left);
       int right = maxDepth(root.right);

       return Math.max(left,right) + 1;
   }

    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(5);
        n0.left = new TreeNode(2);
        System.out.println(maxDepth(n0));
    }
}

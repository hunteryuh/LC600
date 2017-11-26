package com.alg;

/**
 * Created by HAU on 11/25/2017.
 */
/*Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node
in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”*/
public class Sol236_LowestCommonAncestorofABinaryTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right, p,q);
        if (left != null && right!= null){
            return root;
        }
        return left != null? left: right;
    }
}

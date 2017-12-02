package com.alg;

/**
 * Created by HAU on 11/25/2017.
 */
/*Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node
in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”*/
public class Sol235_LowestCommonAncestorofABinarySearchTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // bst: left<middle< right
        while( (root.val - p.val) * (root.val - q.val) >0){  //if equals 0, then return root.
            root = root.val > p.val ? root.left: root.right;
        }
        return root;
    }
}

package com.alg;

/**
 * Created by HAU on 1/7/2018.
 */
/*Given a tree of N nodes, return the amplitude of the tree
就是从 root 到 leaf max - min 的差*/
public class Sol0_amz_TreeAmplitude {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }
    public static int TreeAmplitude(TreeNode root){
        if(root == null) return 0;
        return helper(root,root.val,root.val);
    }

    private static int helper(TreeNode root, int min, int max) {
        if( root == null) return max -min;
        if(root.val < min) min = root.val;
        if ( root.val >max) max = root.val;
        return Math.max(helper(root.left, min, max),helper( root.right,min,max) );
    }
}

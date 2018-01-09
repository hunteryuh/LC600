package com.alg;

/**
 * Created by HAU on 1/9/2018.
 */
/*given a binary tree, find a subtree, where the sum of al its
* nodes is the largest among all subtrees*/
public class Sol0_amz_MaximumSubtree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }
    public static TreeNode res = null;
    public static int max = Integer.MIN_VALUE;

    public static TreeNode findMaxSubtree(TreeNode root){
        helper(root);
        return res;
    }

    private static int helper(TreeNode root) {
        if (root == null) return 0;
        int leftMax = helper(root.left);
        int rightMax = helper(root.right);
        if( res == null || leftMax + rightMax + root.val > max){
            max = leftMax + rightMax + root.val;
            res = root;
        }
        return leftMax+rightMax+root.val;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(-5);
        TreeNode n3 = new TreeNode(2);
        TreeNode n4 = new TreeNode(0);
        TreeNode n5 = new TreeNode(3);
        TreeNode n6 = new TreeNode(-4);
        TreeNode n7 = new TreeNode(-5);
        n1.left = n2;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        System.out.println(findMaxSubtree(n1).val);
    }
}

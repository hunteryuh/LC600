package com.alg;

/**
 * Created by HAU on 1/2/2018.
 */
/*Binary search tree minimum sum from root to leaf*/
public class Sol0_amz_BSTMinSumRootToLeaf {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int BSTMinSumRootToLeaf(TreeNode root) {
        if (root == null) return 0;
        if (root.left != null && root.right == null) {
            return BSTMinSumRootToLeaf(root.left) + root.val;
        }
        if (root.left == null && root.right != null) {
            return BSTMinSumRootToLeaf(root.right) + root.val;
        }
        return Math.min(BSTMinSumRootToLeaf(root.left), BSTMinSumRootToLeaf(root.right)) + root.val;
    }

    public static void main(String args[]) {
        int sum = 21;

        /* Constructed binary tree is
              10
             /  \
           8     13
          / \   /
         6  9
        */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(8);
        root.right = new TreeNode(13);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(9);
        //root.right.left = new TreeNode(11);
        System.out.println(BSTMinSumRootToLeaf(root));
    }
}


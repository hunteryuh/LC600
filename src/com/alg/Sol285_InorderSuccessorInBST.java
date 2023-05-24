package com.alg;

public class Sol285_InorderSuccessorInBST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode result = null;
        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            } else {
                result = root;
                root = root.left;
            }
        }
        return result;
    }
}

package com.alg;
/*
Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.


 */
public class Sol572_SubtreeOfAnotherTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (isSame(root, subRoot)) {
            return true;
        }
        if (root == null) {
            return false;
        } // subroot is not null: constraint
        boolean isLeftSub = isSubtree(root.left, subRoot);
        boolean isRightSub = isSubtree(root.right, subRoot);
        return isLeftSub || isRightSub;
    }
    private boolean isSame(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null || node1.val != node2.val) {
            return false;
        }
        return isSame(node1.left, node2.left) && isSame(node1.right, node2.right);
    }

    public boolean isSubtree2(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null){
            return true;
        }
        if (root != null && subRoot == null){
            return true;
        }
        if (root == null && subRoot != null){
            return false;
        }
        if (root.val == subRoot.val && isExactTree(root, subRoot)){
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);

    }

    public boolean isExactTree(TreeNode root, TreeNode subRoot){
        if (root == null && subRoot == null){
            return true;
        }
        if (root == null || subRoot == null){
            return false;
        }
        if (root.val == subRoot.val && isExactTree(root.left, subRoot.left) && isExactTree(root.right, subRoot.right)){
            return true;
        }
        return false;
    }


    public boolean isSubtree3(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        return isEquals(root, subRoot)
                || isSubtree3(root.left, subRoot)
                || isSubtree3(root.right, subRoot);
    }

    public boolean isEquals(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        return tree1.val == tree2.val
                && isEquals(tree1.left, tree2.left)
                && isEquals(tree1.right, tree2.right);
    }
}

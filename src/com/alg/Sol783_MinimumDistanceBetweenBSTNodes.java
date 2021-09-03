package com.alg;

import java.util.Stack;

/*
Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.



Example 1:


Input: root = [4,2,6,1,3]
Output: 1
Example 2:


Input: root = [1,0,48,null,null,12,49]
Output: 1
 */

// same as 530 https://leetcode.com/problems/minimum-absolute-difference-in-bst/
public class Sol783_MinimumDistanceBetweenBSTNodes {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int min = Integer.MAX_VALUE;
    private TreeNode pre = null;
    public int minDiffInBST(TreeNode root) {
        if (root == null) {
            return min;
        }
        minDiffInBST(root.left);
        if (pre != null) {
            min = Math.min(min, root.val - pre.val);
        }
        pre = root;
        minDiffInBST(root.right);
        return min;

    }

    // iteration
    public int minDiffinBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        int minDiff = Integer.MAX_VALUE;
        while (root!= null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre!=null) {
                minDiff = Math.min(minDiff, root.val - pre.val);
            }
            pre = root;
            root = root.right;
        }
        return minDiff;
    }


    // 530
    private int res = Integer.MAX_VALUE;
    private TreeNode pre2 = null;
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return res;
    }

    private void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        if (pre != null) {
            res = Math.min(res, root.val - pre2.val);
        }
        pre2 = root;
        inOrder(root.right);
    }
}

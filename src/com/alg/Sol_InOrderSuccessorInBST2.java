package com.alg;
/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
The successor of a node p is the node with the smallest key greater than p.val.
 */
public class Sol_InOrderSuccessorInBST2 {
    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // https://www.cnblogs.com/grandyang/p/5306162.html
    // https://www.jiuzhang.com/problem/inorder-successor-in-binary-search-tree/
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null)
            return null;

        TreeNode t = null;

        while (root != null) {
            if (root.val > p.val) {
                t = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return t;
    }
}

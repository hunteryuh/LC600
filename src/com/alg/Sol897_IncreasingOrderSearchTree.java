package com.alg;

import java.util.ArrayList;
import java.util.List;

public class Sol897_IncreasingOrderSearchTree {

    // https://leetcode.com/problems/increasing-order-search-tree/editorial/
    public TreeNode increasingBST2(TreeNode root) {
        List<Integer> vals = new ArrayList<>();
        inorder(root, vals);
        TreeNode dummy = new TreeNode(0);
        TreeNode cur = dummy;
        for (int v: vals) {
            cur.right = new TreeNode(v);
            cur = cur.right;
        }
        return dummy.right;
    }

    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);

    }
}

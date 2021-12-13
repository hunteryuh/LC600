package com.alg;

import java.util.ArrayList;
import java.util.List;

/*
Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.



Example 1:


Input: root = [4,2,6,1,3]
Output: 1
Example 2:


Input: root = [1,0,48,null,null,12,49]
Output: 1
 */
public class Sol530_MinimumAbsoluteDifferenceInBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int x){
            val = x;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int getMinimumDifference(TreeNode root) {
        if (root == null) return 0;
        List<Integer> list = new ArrayList<>();
        traverseInorder(root, list);
        if (list.size() == 1) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < list.size(); i++) {
            min = Math.min(min, list.get(i) - list.get(i-1));
        }
        return min;
    }

    private void traverseInorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        traverseInorder(root.left, list);
        list.add(root.val);
        traverseInorder(root.right, list);
    }


}

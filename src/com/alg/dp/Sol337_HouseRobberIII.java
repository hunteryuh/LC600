package com.alg.dp;

import com.alg.TreeNode;

/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.

Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree.
It will automatically contact the police if two directly-linked houses were broken into on the same night.

Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.



Example 1:


Input: root = [3,2,3,null,3,null,1]
Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:


Input: root = [3,4,5,1,3,null,1]
Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class Sol337_HouseRobberIII {
    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0337.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8DIII.md
    public int robTree(TreeNode root) {
        int[] result = robNode(root);
        return Math.max(result[0], result[1]);
    }
    private int[] robNode(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        // i = 0; rob node; i=1, do not rob node
        int[] left = robNode(node.left);
        int[] right = robNode(node.right);

        // 如果是偷当前节点，那么左右孩子就不能
        int r1 = node.val + left[1] + right[1]; // get cur value, plus the max value of left tree and right tree without their root
        // 如果不偷当前节点，那么左右孩子就可以偷，至于到底偷不偷一定是选一个最大的，所以：val2 = max(left[0], left[1]) + max(right[0], right[1]);
        int r2 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{r1, r2};
    }

}

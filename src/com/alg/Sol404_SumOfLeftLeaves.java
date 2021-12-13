package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/*
Given the root of a binary tree, return the sum of all left leaves.



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 24
Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
Example 2:

Input: root = [1]
Output: 0
 */
public class Sol404_SumOfLeftLeaves {
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

    //
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null ) { // cannot add || root.left == null, otherwise it will miss some left leave node on a sub right tree
            return 0;
        }
        int sumLeftTree = sumOfLeftLeaves(root.left);
        int sumRightTree = sumOfLeftLeaves(root.right);

        int cur = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) { // definition of left leaf node
            cur = root.left.val;
        }
        return cur + sumLeftTree + sumRightTree;

    }

    // bfs
    public int sumLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                if (node.left.left == null && node.left.right == null) {
                    sum += node.left.val;
                }
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return sum;
    }
}

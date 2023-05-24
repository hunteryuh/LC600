package com.alg;

import java.util.LinkedList;

/*
Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes are also counted into the length calculation.

It is guaranteed that the answer will in the range of 32-bit signed integer.



Example 1:


Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:


Input: root = [1,3,null,5,3]
Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:


Input: root = [1,3,2,5]
Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
 */
public class Sol662_MaximumWidthOfBinaryTree {

    class Pair {
        TreeNode node;
        int index;
        Pair(TreeNode node, int level){
            this.node = node;
            this.index = level;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        LinkedList<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 1));
        int res = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int left = 0;
            int right = 0;
            for (int i = 0; i < size; i++) {
                Pair pair = queue.poll();
                if (i == 0) left = pair.index;
                if (i == size -1) right = pair.index;
                TreeNode node = pair.node;
                if (node.left != null) {
                    queue.offer(new Pair(node.left, pair.index * 2));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, pair.index * 2 + 1));
                }
            }
            res = Math.max(res, right - left + 1);
        }
        return res;

    }
}

package com.alg;

import com.sun.tools.javac.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/*
Given a binary tree root, a node X in the tree is named good if in the path from root to X there are
no nodes with a value greater than X.

Return the number of good nodes in the binary tree.



Example 1:



Input: root = [3,1,4,3,null,1,5]
Output: 4
Explanation: Nodes in blue are good.
Root Node (3) is always a good node.
Node 4 -> (3,4) is the maximum value in the path starting from the root.
Node 5 -> (3,4,5) is the maximum value in the path
Node 3 -> (3,1,3) is the maximum value in the path.
Example 2:



Input: root = [3,3,null,4,2]
Output: 3
Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
Example 3:

Input: root = [1]
Output: 1
Explanation: Root is considered as good.


Constraints:

The number of nodes in the binary tree is in the range [1, 10^5].
Each node's value is between [-10^4, 10^4].
 */
public class Sol1448_CountGoodNodesInBinaryTree {
    int res = 0;
    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return res;
    }
    private void dfs(TreeNode node, int curMax) {
        if (node == null) return;
        if (node.val >= curMax) {
            res++;
        }
        dfs(node.left, Math.max(node.val, curMax));
        dfs(node.right, Math.max(node.val, curMax));
    }


    // with return value of dfs2
    public int goodNodes2(TreeNode root) {
        if(root == null) return 0;
        return dfs2(root, root.val);
    }

    private int dfs2(TreeNode current, int maxValue) {
        if(current == null) return 0;

        maxValue = Math.max(maxValue, current.val);
        if(current.val < maxValue) return dfs2(current.right, maxValue) + dfs2(current.left, maxValue);
        else return 1 + dfs2(current.right, maxValue) + dfs2(current.left, maxValue);
    }


    class Pair {
        TreeNode node;
        int max;
        Pair(TreeNode node, int max) {
            this.node = node;
            this.max = max;
        }
    }

    // bfs
    public int goodNodesBFS(TreeNode root) {
        int count = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, root.val));
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            TreeNode curNode = pair.node;
            int curMax = pair.max;
            if (curNode.val >= curMax) {
                count++;
            }
            if (curNode.left != null) {
                queue.offer(new Pair(curNode.left, Math.max(curMax, curNode.val)));
            }
            if (curNode.right != null) {
                queue.offer(new Pair(curNode.right, Math.max(curMax, curNode. val)));
            }

        }
        return count;
    }
}

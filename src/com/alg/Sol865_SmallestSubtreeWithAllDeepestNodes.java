package com.alg;

import java.util.*;

/*
Given the root of a binary tree, the depth of each node is the shortest distance to the root.

Return the smallest subtree such that it contains all the deepest nodes in the original tree.

A node is called the deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is a tree consisting of that node, plus the set of all descendants of that node.



Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation: We return the node with value 2, colored in yellow in the diagram.
The nodes coloured in blue are the deepest nodes of the tree.
Notice that nodes 5, 3 and 2 contain the deepest nodes in the tree but node 2 is the smallest subtree among them, so we return it.

Example 2:

Input: root = [1]
Output: [1]
Explanation: The root is the deepest node in the tree.

Example 3:

Input: root = [0,1,3,null,2]
Output: [2]
Explanation: The deepest node in the tree is 2, the valid subtrees are the subtrees of nodes 2, 1 and 0 but the subtree of node 2 is the smallest.



Constraints:

    The number of nodes in the tree will be in the range [1, 500].
    0 <= Node.val <= 500
    The values of the nodes in the tree are unique.

 */
public class Sol865_SmallestSubtreeWithAllDeepestNodes {
    // https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/solutions/146868/simple-java-dfs-recursion-function-with-explanation/
    int deepestLevel = 0;
    TreeNode res = null;

    public TreeNode subtreeWithAllDeepest0(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    private int dfs(TreeNode root, int level) {

        // level as both a parameter and the return value
        if (root == null) return level;

        int leftLevel = dfs(root.left, level + 1);
        int rightLevel = dfs(root.right, level + 1);

        int curLevel = Math.max(leftLevel, rightLevel);
        deepestLevel = Math.max(deepestLevel, curLevel);
        if (leftLevel == deepestLevel && rightLevel == deepestLevel) {
            res = root;
        }
        return curLevel;
    }

    // solution 2 with height dfs
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root==null) return null;
        int lh = height(root.left);
        int rh = height(root.right);
        if (lh==rh) {
            return root;
        } else if (lh > rh) {
            return subtreeWithAllDeepest(root.left);
        } else {
            return subtreeWithAllDeepest(root.right);
        }
    }
    public int height(TreeNode root) {
        if (root==null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) +1;
    }
    // bfs
    public TreeNode subtreeWithAllDeepest2(TreeNode root) {
        if (root == null) return null;
        Map<TreeNode, TreeNode> childToParentMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<TreeNode> lastRow = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            lastRow = new ArrayList<>();
            for (int i = 0; i <size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                    childToParentMap.put(cur.left, cur);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                    childToParentMap.put(cur.right, cur);
                }
                lastRow.add(cur);
            }
        }
        if (lastRow.size() == 1) {
            return lastRow.get(0);
        }
        // last row may have 2 nodes that do not have the same immediate parent
        TreeNode left = lastRow.get(0);
        TreeNode right = lastRow.get(lastRow.size() - 1);
        while (left != right) {
            left = childToParentMap.get(left);
            right = childToParentMap.get(right);
        }
        return left;
    }
}

package com.alg;
/*
You are given the root of a binary tree.

A ZigZag path for a binary tree is defined as follow:

    Choose any node in the binary tree and a direction (right or left).
    If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
    Change the direction from right to left or from left to right.
    Repeat the second and third steps until you can't move in the tree.

Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

Return the longest ZigZag path contained in that tree.



Example 1:

Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
Output: 3
Explanation: Longest ZigZag path in blue nodes (right -> left -> right).

Example 2:

Input: root = [1,1,1,null,1,null,null,1,1,null,1]
Output: 4
Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).

Example 3:

Input: root = [1]
Output: 0



Constraints:

    The number of nodes in the tree is in the range [1, 5 * 104].
    1 <= Node.val <= 100


 */
public class Sol1372_LongestZigZagPathInABinaryTree {
    // naive backtrack/dfs
    int max;
    public int longestZigZag(TreeNode root) {
        dfs(root);
        return max == 0 ? 0 : max - 1;
    }
    private int[] dfs(TreeNode root) {
        int[] res = new int[2];
        if (root == null) return res;
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        res[0] = left[1] + 1;  // 拿left child的right sum
        res[1] = right[0] + 1; // take right child's left sum
        max = Math.max(max, Math.max(res[0], res[1]));
        return res;
    }

    // method 2, 传一个boolean来指示是否来自left,这样可以减少不必要的traversal
    // O (n) 一个节点最多遍历1次
    private int res = 0;
    public int longestZigZag2(TreeNode root) {
        dfs(root, true);  // root是起点，没有parent，必然是valid???
//        dfs(root, false);
        return res;
    }

    private int dfs(TreeNode root, boolean isLeft) {
        if (root == null) return 0;
        int left = dfs(root.left, true);
        int right = dfs(root.right, false);
        res = Math.max(res, Math.max(left, right));
        return isLeft? 1 + right: 1 + left;
    }


    // https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/discuss/531867/JavaPython-DFS-Solution
    // Recursive return [left, right, result], where:
    //left is the maximum length in direction of root.left
    //right is the maximum length in direction of root.right
    //result is the maximum length in the whole sub tree.
    public int longestZigZag3(TreeNode root) {
        return dfs2(root)[2];
    }

    private int[] dfs2(TreeNode root) {
        if (root == null) return new int[]{-1, -1, -1};
        int[] left = dfs2(root.left), right = dfs2(root.right);
        int res = Math.max(Math.max(left[1], right[0]) + 1, Math.max(left[2], right[2]));
        return new int[]{left[1] + 1, right[0] + 1, res};
    }
}

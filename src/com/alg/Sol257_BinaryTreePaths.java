package com.alg;

import java.util.ArrayList;
import java.util.List;

/*
Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3

https://www.jiuzhang.com/problem/binary-tree-paths/
 */
public class Sol257_BinaryTreePaths {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        // 3. exit
        if (root == null) {
            return paths;
        }

        // leaf
        if (root.left == null && root.right == null) {
            paths.add(root.val + "");
        }

        // 2. split
        List<String> leftPaths = binaryTreePaths(root.left);
        List<String> rightPaths = binaryTreePaths(root.right);

        for (String path : leftPaths) {
            paths.add(root.val + "->" + path);
        }

        for (String path : rightPaths) {
            paths.add(root.val + "->" + path);
        }
        return paths;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        root.left = left;
        TreeNode right = new TreeNode(3);
        root.right = right;
        TreeNode leftR = new TreeNode(5);

        left.right = leftR;

        System.out.println(binaryTreePaths(root));

    }
}

package com.alg;
/*
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

A path is a continuous sequence of nodes connected to each other. There will always be at least one node in a path.
In a path, except for the starting and ending nodes, every node is connected to two other nodes in the sequence.

Example 1:


Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
Example 2:


Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.


Constraints:

The number of nodes in the tree is in the range [1, 3 * 104].
-1000 <= Node.val <= 1000
 */
public class Sol124_BinaryTreeMaxPathSum {

    // https://leetcode.com/problems/binary-tree-maximum-path-sum/discuss/603423/Python-Recursion-stack-thinking-process-diagram
    int res = Integer.MIN_VALUE;  // # placeholder to be updated
    public int maxPathSum(TreeNode root) {
        postOrder(root);
        return res;
    }

    public int postOrder(TreeNode node) {
        if (node == null) return 0;
        int left= Math.max(0, postOrder(node.left));
        int right = Math.max(0, postOrder(node.right));
        // the price to start a new path where `node` is a highest node
        res = Math.max(res, node.val + left + right);
        /*
        The path sum gain contributed by the subtree can be derived from a path that includes at most one child
        of the root. You may wonder, why can't we include both children? If we include both children in the path,
        the path would have to make a fork at the root. The root is already connected to its parent.
        Now, if we include both children as well, with three connections, it wouldn't be a valid path anymore.
        Therefore, we can say that the path would consist of at most one child of the root.
         */
        // return the max gain if continue the same path
        // return the max sum for a path starting at the root of subtree (continue the same path)
        return Math.max(left, right) + node.val;
    }

    //   10
    //1       5
    //     10   6
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(10);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(10);
        TreeNode n5 = new TreeNode(6);
        n1.left = n2; n1.right = n3;
        n3.left = n4; n3.right = n5;
        Sol124_BinaryTreeMaxPathSum ss = new Sol124_BinaryTreeMaxPathSum();
        System.out.println(ss.maxPathSum(n1));  // 26 ( 1, 10, 5, 10)

    }

    public int maxPathSum2(TreeNode root) {
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;

        postOrder2(root, res);
        return res[0];
    }

    int postOrder2(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }

        int left = postOrder2(root.left, res);
        int right = postOrder2(root.right, res);

        int max = root.val + left + right;
        res[0] = Math.max(res[0], max);

        return Math.max(0, root.val + Math.max(left, right));
    }

    // 给定一棵二叉树，找到二叉树的最大路径和，路径必须从根节点出发。
    //
    // 路径可在任意节点结束，但至少包含一个节点（也就是根节点）
    // https://www.jiuzhang.com/problem/binary-tree-maximum-path-sum-ii/
    public int maxPathSum3(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        int left = maxPathSum3(root.left);
        int right = maxPathSum3(root.right);
        return root.val + Math.max(0, Math.max(left, right));
    }

    private int res2 = Integer.MIN_VALUE;
    // private int sum = Integer.MIN_VALUE;
    public int getMaxPathSum(TreeNode root) {
        postOrder2(root);

        return res;
    }

    // https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
    // maximum path sum between two leaf nodes in a binary tree, The maximum sum path may or may not go through the root.
    // For example, in the following binary tree, the maximum sum is 27(3 + 6 + 9 + 0 – 1 + 10)

    // A utility function to find the maximum sum between any
    // two leaves.This function calculates two values:
    // 1) Maximum path sum between two leaves which is stored
    // in res.
    // 2) The maximum root to leaf path sum which is returned.
    private int postOrder2(TreeNode node) {
        if (node == null) return 0;
        // recursively calculate max sum from node to leaf for left and right subtrees
        int left = postOrder2(node.left);
        int right = postOrder2(node.right);
        // to make sure its leaf nodes, only update res when the node has two leaf nodes
        // res stores a sum for a path that connects two leaf nodes with the current node as root
        // // If both left and right children exist
        if (node.left != null && node.right != null) {
            res = Math.max(res, node.val + left + right);
        }
        System.out.println("Current res is " + res + " at node " + node.val);
        int sum = node.val + Math.max(left, right);
        System.out.println("Current sum is " + sum);
        return sum;
    }

}

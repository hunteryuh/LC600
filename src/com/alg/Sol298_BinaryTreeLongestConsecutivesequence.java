package com.alg;

/**
 * Created by HAU on 12/10/2017.
 */
/* Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,

   1
    \
     3
    / \
   2   4
        \
         5

Longest consecutive sequence path is 3-4-5, so return 3.

   2
    \
     3
    /
   2
  /
 1

Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
https://www.jiuzhang.com/problem/binary-tree-longest-consecutive-sequence/
*/
public class Sol298_BinaryTreeLongestConsecutivesequence {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    private static int maxLen = 0;
    public  static int longestConsecutive(TreeNode root) {
        /*Time complexity : O(n)O(n)O(n). The time complexity is the same as an in-order
        traversal of a binary tree with nnn nodes.*/
        dfshelper(root,null,0);
        return maxLen;
    }

    private  static void dfshelper(TreeNode p, TreeNode parent, int length) {
        if (p == null) return;
        length = (parent!= null && parent.val + 1 == p.val)? length + 1 : 1;
        maxLen= Math.max(length,maxLen);
        dfshelper(p.left, p, length);
        dfshelper(p.right, p, length);
    }

    public  static int longestConsecutive2(TreeNode root) {
        maxLen = 0;
        helper(root);
        return maxLen;
    }

    private static int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);

        int subTreeLongest = 1;
        if (root.left != null && root.val + 1 == root.left.val) {
            subTreeLongest = Math.max(subTreeLongest, left + 1);
        }

        if (root.right != null && root.val + 1 == root.right.val) {
            subTreeLongest = Math.max(subTreeLongest, right + 1);
        }
        if (subTreeLongest > maxLen) {
            maxLen = subTreeLongest;
        }
        return subTreeLongest;
    }

    public static void main(String[] args) {
        //      8
        //    /   \
        //   2     9
        //  / \   /
        // 1   3 7
        //    /
        //   4
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(9);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(4);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node5.left = node7;
        System.out.println(longestConsecutive(node1)); // 2-3-4 : 3
        System.out.println(longestConsecutive2(node1)); // 2-3-4 : 3
    }
}

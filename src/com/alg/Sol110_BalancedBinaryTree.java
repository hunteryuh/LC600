package com.alg;

/**
 * Created by HAU on 10/30/2017.
 */

/*Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is
defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.*/
public class Sol110_BalancedBinaryTree {
    public static class TreeNode
    {
        public int data;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data)
        {
            this.data = data;
        }
    }
    public static boolean isBalanced(TreeNode root) {
        return dfsDepth(root)!= -1;
    }

    private static int dfsDepth(TreeNode root) {
        if (root == null) return 0;
        int left = dfsDepth(root.left);
        int right = dfsDepth(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        return Math.max(left,right) + 1;
    }

    public static void main(String[] args) {
        //      1
        //    /   \
        //   2     3
        //  / \   /
        // 4   5 6
        //    /
        //   7
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node5.left = node7;
        System.out.println(isBalanced(node1));
    }

    public boolean isBalanced2(TreeNode root) {
        // height difference no more than one
        if (root == null) return true;
        if (!isBalanced2(root.left) || !isBalanced2(root.right)) {
            return false;
        }
        if (Math.abs(height(root.left) - height(root.right)) > 1) {
            return false;
        }
        return true;
    }
    private int height(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

 /*   public static boolean isBalanced(TreeNode root) {
        int depth[] = new int[1];
        return isBalanced(root, depth);
    }

    private static boolean isBalanced(TreeNode root, int[] depth) {
        if (root == null) {
            depth[0] = 0;
            return true;
        }

        int depthLeft[] = new int[1];
        int depthRight[] = new int[1];
        if (isBalanced(root.left, depthLeft)
                && isBalanced(root.right, depthRight)
                && Math.abs(depthLeft[0] - depthRight[0]) <= 1) {
            depth[0] = Math.max(depthLeft[0], depthRight[0]) + 1;
            return true;
        }

        return false;
    }*/
}

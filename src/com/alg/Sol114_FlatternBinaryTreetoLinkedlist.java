package com.alg;

/**
 * Created by HAU on 11/25/2017.
 */
/*Given a binary tree, flatten it to a linked list in-place.*/
public class Sol114_FlatternBinaryTreetoLinkedlist {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    private static  TreeNode prev = null;
    public static void flatten(TreeNode root){
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;

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

        flatten(node1);
        while ( node1 != null){
            System.out.println(node1.val);
            node1 = node1.right;

        }
    }
    public static void flatten2(TreeNode root){
        // straightforward recursive
        if ( root == null) return;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        flatten2(left);
        flatten2(right);
        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        cur.right = right;
    }
}

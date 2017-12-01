package com.alg;

/**
 * Created by HAU on 12/1/2017.
 */
/* Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

        Given target value is a floating point.
        You are guaranteed to have only one unique value in the BST that is closest to the target.
        */
public class Sol270_ClosestBinarySearchTreeValue {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    public static int closestValue(TreeNode root, double target) {
        int v1 = root.val;
        TreeNode c =  target<v1? root.left: root.right;
        if(c == null) return v1;
        int v2 = closestValue(c,target);
        return Math.abs(target - v1) < Math.abs(target - v2)? v1 : v2;
    }
    public static void main(String[] args) {
        //      8
        //    /   \
        //   2     10
        //  / \   /
        // 1   5 9
        //    /
        //   3
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(9);
        TreeNode node7 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node5.left = node7;

        System.out.println(closestValue(node1, 3.2));
        System.out.println(closestVal(node1, 3.2));
    }
    public static int closestVal(TreeNode root, double target){
        int res = root.val;
        while ( root!= null){
            if( Math.abs( target - res) > Math.abs(target-root.val)){
                res = root.val;
            }
            root = root.val < target ? root.right:root.left;
        }
        return res;
    }
}

package com.alg;


import java.util.Stack;

/**
 * Created by HAU on 11/26/2017.
 */
public class Sol98_ValidateBinarySearchTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    static TreeNode prev;
    public static boolean isValidBST(TreeNode root) {
        // use in-order traversal
        if (root == null) return true;
        if (!isValidBST(root.left)) {
            return false;
        }
        if (prev!= null && prev.val >= root.val){
            return false;
        }
        prev = root;
        if (!isValidBST(root.right)){
            return false;
        }
        return true;
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
        TreeNode node2 = new TreeNode(4);
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
        System.out.println(isValidBST(node1));
        System.out.println(isValidBST2(node1));


    }
    public static boolean isValidBST2(TreeNode root) {
        return isValidBST2(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public static boolean isValidBST2(TreeNode p, double min, double max){
        if (p == null)
            return true;

        if (p.val <= min || p.val >= max)
            return false;

        return isValidBST2(p.left, min, p.val) && isValidBST2(p.right, p.val, max);
    }

    //in order traversal method 2
    public boolean isValidBST0(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root, pre = null;
        while (!stack.isEmpty() || cur != null){
            if (cur!= null){
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode p = stack.pop();
                if (pre != null && p.val <= pre.val){
                    return false;
                }
                pre = p;
                cur = p.right;
            }
        }
        return true;
    }

    // in order method 3
    public boolean isValidBST3(TreeNode root){
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null, cur = root;
        while (cur!= null || !stack.isEmpty() ){
            while (cur!= null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (pre != null && cur.val <= pre.val){
                return false;
            }
            pre = cur;
            cur = cur.right;
        }
        return true;
    }
}

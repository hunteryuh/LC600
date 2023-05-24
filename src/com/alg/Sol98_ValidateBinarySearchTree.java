package com.alg;


import java.util.Stack;

/**
 * Created by HAU on 11/26/2017.
 */
public class Sol98_ValidateBinarySearchTree {

    // inorder recursive
    static TreeNode prev;
    public boolean isValidBST(TreeNode root) {
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
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(9);
        TreeNode node7 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;
//        node2.left = node4;
//        node2.right = node5;
//        node3.left = node6;
//        node5.left = node7;

        Sol98_ValidateBinarySearchTree ss = new Sol98_ValidateBinarySearchTree();
        System.out.println(ss.isValidBST(node1));
//        System.out.println(ss.isValidBST2(node1));


    }
    public boolean isValidBST2(TreeNode root) {
        return isValidBST2(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST2(TreeNode p, long min, long max){
        if (p == null)
            return true;
        if (p.val <= min || p.val >= max)
            return false;

        return isValidBST2(p.left, min, p.val) && isValidBST2(p.right, p.val, max);
    }

    public boolean isValidBST4(TreeNode root) {
        return isValidBST4(root, null, null);
    }

    private boolean isValidBST4(TreeNode root, Integer max, Integer min) {
        if (root == null) return true;
        if (max != null && root.val >= max) return false;
        if (min != null && root.val <= min) return false;
        return isValidBST4(root.left, root.val, min) && isValidBST4(root.right, max, root.val);
    }

    //in order traversal method 2
    public boolean isValidBST0(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root, pre = null;
        while (!stack.isEmpty() || cur != null){
            if (cur!= null) {
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

    // recursive inorder
    private long pre = Long.MIN_VALUE;
    public boolean isValidBST6(TreeNode root) {
        if (root == null) return true;
        boolean left = isValidBST6(root.left);
        if (!left) return false;
        if (root.val <= pre) return false;
        pre = root.val;
        return isValidBST6(root.right);
    }
}

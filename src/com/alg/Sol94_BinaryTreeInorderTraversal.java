package com.alg;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by HAU on 11/5/2017.
 */
/*Given a binary tree, return the inorder traversal of its nodes' values.*/
public class Sol94_BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root){
        //recursion, O(n) time O(n) space-worst, average O(logn)
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
    }



    private static void helper2(TreeNode root, List<Integer> res) {
        if (root!= null){
            if (root.left != null){
                helper2(root.left, res);
            }
            res.add(root.val);
            if (root.right!= null){
                helper2(root.right, res);
            }
        }
    }

    //iterative inorder template preferred
    public static List<Integer> inorderTra2(TreeNode root){
        //using stack
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }

    // template 2, iterative
    public List<Integer> inorderTra3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }
}

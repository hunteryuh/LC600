package com.alg;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by HAU on 11/5/2017.
 */
/*Given a binary tree, return the postorder traversal of its nodes' values.*/
public class Sol145_BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        helper(root.left, res);
        helper(root.right, res);
        res.add(root.val);
    }

    private static void helper2(TreeNode root, List<Integer> res) {
        if (root != null){
            if (root.left != null){
                helper2(root.left, res);
            }
            if (root.right != null){
                helper2(root.right, res);
            }
            res.add(root.val);
        }
    }

    public static List<Integer> postorderTra2(TreeNode root){
        LinkedList<Integer> ans = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) return ans;
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            ans.addFirst(cur.val);  // this adds the top elements in the stack first in the list
            if (cur.left != null){
                stack.push(cur.left);
            }
            if (cur.right != null){
                stack.push(cur.right);
            }
        }
        return ans;
    }

    public List<Integer> postorderTra3(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            ans.add(0, cur.val);  // this adds the top elements in the stack first in the list
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null){
                stack.push(cur.right);
            }
        }
        return ans;
    }


    // template 2
    public List<Integer> postorderTra4(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> resStack = new Stack<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                resStack.push(cur);  // root
                stack.push(cur);
                cur = cur.right;  // right

            } else {
                cur = stack.pop();
                cur = cur.left;  //left
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!resStack.isEmpty()) res.add(resStack.pop().val);
        return res;
    }
}

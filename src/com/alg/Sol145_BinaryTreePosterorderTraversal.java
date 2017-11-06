package com.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by HAU on 11/5/2017.
 */
/*Given a binary tree, return the postorder traversal of its nodes' values.*/
public class Sol145_BinaryTreePosterorderTraversal {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    public static List<Integer> inorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private static void helper(TreeNode root, List<Integer> res) {
        if ( root != null){
            if ( root.left != null){
                helper(root.left, res);
            }
            if ( root.right != null){
                helper(root.right, res);
            }
            res.add(root.val);
        }
    }

    public static List<Integer> postorderTra2(TreeNode root){
        LinkedList<Integer> ans = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if ( root == null) return ans;
        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            ans.addFirst(cur.val);
            if (cur.left != null){
                stack.push(cur.left);
            }
            if (cur.right != null){
                stack.push(cur.right);
            }
        }
        return ans;
    }
}

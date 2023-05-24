package com.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by HAU on 1/18/2018.
 */
/*Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],
   1
    \
     2
    /
   3
return [1,2,3].

preorder 先序  根左右
inorder 中序  左根右
postorder 后序 左右根

Note: Recursive solution is trivial, could you do it iteratively?*/
public class Sol144_BinaryTreePreorderTraversal {
    // recursive
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    // Divide and Conquer
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();

        // null or leaf
        if (root == null) return result;

        // Divide
        List<Integer> left = preorderTraversal(root.left);
        List<Integer> right = preorderTraversal(root.right);

        // Conquer  (merge)
        result.add(root.val);
        result.addAll(left);
        result.addAll(right);

        return result;
    }

    // method 2 with a helper to traverse
    public static List<Integer> preOrderTra(TreeNode root){
        List<Integer> res = new LinkedList<>();
        prehelper(root, res);
        return res;
    }
    private static void prehelper(TreeNode root, List<Integer> list){
        if(root == null) return;
        list.add(root.val);
        prehelper(root.left, list);
        prehelper(root.right, list);
    }

    // method 3, iterative
    public static List<Integer> preorderTra(TreeNode root){
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null){
            res.add(root.val);
            if (root.right != null){
                stack.push(root.right);
            }
            root = root.left;
            if ( root == null && !stack.isEmpty()){
                root = stack.pop();
            }
        }
        return res;
    }

    // method 4, iterative, preferred
    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) stack.push(node.right);
            // stack first in last out, so add right first so that left can be popped first
            if (node.left != null) stack.push(node.left);
        }
        return result;
    }
}

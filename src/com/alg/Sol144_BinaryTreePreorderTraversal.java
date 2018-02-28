package com.alg;

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
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preres = new LinkedList<>();
        if ( root == null) return preres;
        preres.add(root.val);
        preres.addAll(preorderTraversal(root.left));
        preres.addAll(preorderTraversal(root.right));
        return preres;

    }
    // method 2 with a helper
    public static List<Integer> preOrderTra(TreeNode root){
        List<Integer> res = new LinkedList<>();
        prehelper(root,res);
        return res;
    }
    private static void prehelper(TreeNode root, List<Integer> list){
        if(root == null) return;
        list.add(root.val);
        prehelper(root.left,list);
        prehelper(root.right,list);
    }
    // method 3, iterative
    public static List<Integer> preorderTra(TreeNode root){
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(root != null){
            res.add(root.val);
            if ( root.right != null){
                stack.push(root.right);
            }
            root = root.left;
            if ( root == null && !stack.isEmpty()){
                root = stack.pop();
            }
        }
        return res;
    }
}

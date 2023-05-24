package com.alg;

import sun.reflect.generics.tree.Tree;

import java.util.Stack;

/**
 * Created by HAU on 1/18/2018.
 */
/*Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

Example 1:
Input:
	Tree 1                     Tree 2
          1                         2
         / \                       / \
        3   2                     1   3
       /                           \   \
      5                             4   7
Output:
Merged tree:
	     3
	    / \
	   4   5
	  / \   \
	 5   4   7
Note: The merging process must start from the root nodes of both trees.*/
public class Sol617_MergeTwoBinaryTrees {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int x){
            val = x;
        }
    }
    // recursion
    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2){
        if ( t1 == null) return t2;
        if ( t2 == null) return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left,t2.left);
        t1.right = mergeTrees(t1.right,t2.right);
        return t1;
    }

    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTrees2(root1.left, root2.left);
        root.right = mergeTrees2(root1.right, root2.right);
        return root;
    }
    // iterative method
    public static TreeNode mergeTs(TreeNode t1, TreeNode t2){
        if (t1 == null) {
            return t2;
        }
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{t1,t2});
        while(!stack.isEmpty()){
            TreeNode[] t = stack.pop();
            if(t[0] == null || t[1] == null){
                continue;
            }
            t[0].val += t[1].val; // t[0] is not null
            if(t[0].left == null){
                t[0].left =t[1].left;
            }else{
                stack.push(new TreeNode[]{t[0].left,t[1].left});
            }
            if(t[0].right == null) {
                t[0].right = t[1].right;
            } else {
                stack.push(new TreeNode[]{t[0].right,t[1].right});
            }
        }
        return t1;
    }
}

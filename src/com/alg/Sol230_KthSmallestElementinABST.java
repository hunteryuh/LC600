package com.alg;

import java.util.Stack;

/**
 * Created by HAU on 11/30/2017.
 */
/*Given a binary search tree, write a function kthSmallest to find the kth smallest element
in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and
you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?*/
public class Sol230_KthSmallestElementinABST {

    int count = 0;
    int result = Integer.MIN_VALUE;
    public int kthSmallest(TreeNode root, int k) {
        //recursive
        traverse(root, k);
        return result;
    }
    public void traverse(TreeNode root, int k ){
        if (root == null) return;
        traverse(root.left, k);
        count++;
        if (count == k) result = root.val;
        traverse(root.right, k);
    }

    // iterative
    public static int kthSmall(TreeNode root, int k){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        int count = 0;
        while (!stack.isEmpty() || p != null){
            if (p!= null) {
                stack.push(p);// like recursion
                p = p.left;
            } else {
                TreeNode node = stack.pop();
                count++;
                if (count == k) return node.val;
                p = node.right;
            }
        }
        return Integer.MIN_VALUE;
    }
}

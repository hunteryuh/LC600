package com.alg;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.List;
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
        while (!stack.isEmpty() || p != null) {
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

    // recursive dfs/inorder traversal to sort the nodes in a list
    // O(n)
    public int kthSmallest2(TreeNode root, int k) {
        if (root == null) return 0;
        List<Integer> res = new ArrayList<>();
        inorder(res, root);
        return res.get(k - 1);
    }

    private void inorder(List<Integer> res, TreeNode root) {
        if (root == null) return;
        inorder(res, root.left);
        res.add(root.val);
        inorder(res, root.right);
    }

    // follow up If the BST is modified often
    // (i.e., we can do insert and delete operations) and
    // you need to find the kth smallest frequently, how would you optimize?
 /////If we could add a count field in the BST node class, it will take O(n) time when we calculate the count value for the whole tree,
    // but after that,
    // it will take O(logn) time when insert/delete a node or calculate the kth smallest element.
    class TreeNodeWithCount {
        int val;
        int count;
        TreeNodeWithCount left;
        TreeNodeWithCount right;
        TreeNodeWithCount(int x) {
            val = x;
            count = 1;
        }
    }

    TreeNodeWithCount treeNodeWithCount;
    public int KthSmallest(TreeNode root, int k) {
        treeNodeWithCount = buildTree(root);
        return helper(treeNodeWithCount, k);
    }
    private TreeNodeWithCount buildTree(TreeNode root) {
        if (root == null) return null;
        TreeNodeWithCount node = new TreeNodeWithCount(root.val);
        node.left = buildTree(root.left);
        node.right = buildTree(root.right);
        node.count += (node.left == null)? 0 : node.left.count;
        node.count += (node.right == null)? 0: node.right.count;
        return node;
    }

    private int helper(TreeNodeWithCount tRoot, int k) {
        int leftCount = (tRoot.left == null) ? 0 : tRoot.left.count;
        if (k == leftCount + 1) {
            return tRoot.val;
        } else if (k > leftCount + 1) {
            return helper(tRoot.right, k - leftCount - 1);
        } else {
            return helper(tRoot.left, k);
        }

    }

}

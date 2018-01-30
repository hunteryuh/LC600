package com.alg;

import java.util.Stack;

/**
 * Created by HAU on 1/30/2018.
 */
/*Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.*/
public class Sol173_BinarySearchTreeIterator {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    private Stack<TreeNode> stack = new Stack<>();

    public class BSTIterator{
        public BSTIterator(TreeNode root) {
            pushAllNodes(root);
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode node = stack.pop();
            pushAllNodes(node.right);
            return node.val;
        }
        private void pushAllNodes(TreeNode node){
            while(node!=null){
                stack.push(node);
                node = node.left;
            }
        }
    }

}

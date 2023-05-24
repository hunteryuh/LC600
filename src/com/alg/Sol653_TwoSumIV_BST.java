package com.alg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/*
Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.



Example 1:


Input: root = [5,3,6,2,4,null,7], k = 9
Output: true
Example 2:


Input: root = [5,3,6,2,4,null,7], k = 28
Output: false


Constraints:

The number of nodes in the tree is in the range [1, 104].
-104 <= Node.val <= 104
root is guaranteed to be a valid binary search tree.
-105 <= k <= 105
 */
public class Sol653_TwoSumIV_BST {
    // time O(n), space O(N)
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (list.get(left) + list.get(right) == k) return true;
            if (list.get(left) + list.get(right) < k) left++;
            else right--;
        }
        return false;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    // hashtable time n, space n
    public boolean findTarget2(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }

    public boolean dfs(TreeNode root, HashSet<Integer> set, int k){
        if(root == null)return false;
        if(set.contains(k - root.val))return true;
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }

    // optimization space , time O(n), space O(H)

    public boolean findTarget3(TreeNode root, int k) {
        BSTIterator leftIter = new BSTIterator(root, true);
        BSTIterator rightIter = new BSTIterator(root, false);
        int left = leftIter.next();
        int right = rightIter.next();
        while (left < right) {
            if (left + right == k) return true;
            else if (left + right < k) {
                left = leftIter.next();
            } else {
                right = rightIter.next();
            }
        }
        return false;
    }
    class BSTIterator {
        private Stack<TreeNode> stack = new Stack<>();
        private boolean forward;
        BSTIterator(TreeNode root, boolean forward) {
            this.forward = forward;
            pushAll(root);
        }
        void pushAll(TreeNode root) {
            while (root != null) {
                stack.push(root);
                root = forward ? root.left : root.right;
            }
        }
        int next() {
            TreeNode node = stack.pop();
            pushAll(forward? node.right : node.left);
            return node.val;
        }
    }

}

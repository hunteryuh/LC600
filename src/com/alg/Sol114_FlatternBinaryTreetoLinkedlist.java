package com.alg;

import java.util.Stack;

/**
 * Created by HAU on 11/25/2017.
 */
/*Given a binary tree, flatten it to a linked list in-place.
* For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.*/
// https://www.jiuzhang.com/problem/flatten-binary-tree-to-linked-list/
public class Sol114_FlatternBinaryTreetoLinkedlist {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    private static TreeNode prev = null;

    public static void flatten(TreeNode root){
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;

    }

    public static void main(String[] args) {
        //      1
        //    /   \
        //   2     3
        //  / \   /
        // 4   5 6
        //    /
        //   7
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node5.left = node7;

        flatten4(node1);
        while (node1 != null){
            System.out.print(node1.val + "->");
            node1 = node1.right;

        }
    }
    public static void flatten2(TreeNode root){
        // straightforward recursive
        if (root == null) return;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        flatten2(left);
        flatten2(right);
        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        cur.right = right;
    }

    public static void flatten3(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }

            if (!stack.empty()) {
                node.right = stack.peek();
            }
            node.left = null;
        }
    }


    public static void flatten4(TreeNode root) {
        helper(root);
    }
    // flatten root and return the last node
    private static TreeNode helper(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode leftLast = helper(root.left);
        TreeNode rightLast = helper(root.right);

        // connect leftLast to root.right
        if (leftLast != null) {
            leftLast.right = root.right;
            root.right = root.left;  // pointed by the comment at line 148
            root.left = null;
        }

        if (rightLast != null) {
            return rightLast;
        }
        // at this time the "leftLast" may be already the right child of the node (node.right, at line 141), but to get the node we still need its reference
        if (leftLast != null) {
            return leftLast;
        }

        return root;
    }
}

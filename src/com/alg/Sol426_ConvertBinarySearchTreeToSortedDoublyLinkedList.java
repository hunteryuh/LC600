package com.alg;

import java.util.Stack;

/*
Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.

You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.



Example 1:

Input: root = [4,2,5,1,3]


Output: [1,2,3,4,5]

Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

Example 2:

Input: root = [2,1,3]
Output: [1,2,3]

facebook like it?
 */
public class Sol426_ConvertBinarySearchTreeToSortedDoublyLinkedList {
    // recursive dfs, inorder traversal
    TreeNode head = null, pre = null;
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;
        inOrderTraversal(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    private void inOrderTraversal(TreeNode root) {
        if (root == null) return;
        inOrderTraversal(root.left); // left
        if (head == null) head = root;
        if (pre != null) pre.right = root; // root
        root.left = pre;
        pre = root;
        inOrderTraversal(root.right); // right
    }

    // iterative
    public TreeNode treeToDoublyList2(TreeNode root) {
        if (root == null) return null;
        TreeNode pre = null, head = null;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (head == null) head = root;
            if (pre != null) {
                pre.right = head;
                head.left = pre;
            }
            pre = root;
            root = root.right;
        }
        head.left = pre;
        pre.right = head;
        return head;
    }
}

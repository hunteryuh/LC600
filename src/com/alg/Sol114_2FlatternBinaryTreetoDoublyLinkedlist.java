package com.alg;

/**
 * Created by HAU on 11/25/2017.
 */
/*
Convert a binary search tree to doubly linked list with in-order traversal.
Example
Given a binary search tree:
    4
   / \
  2   5
 / \
1   3
return 1<->2<->3<->4<->5
Tags Expand
Linked List
*/

import java.util.Stack;

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 * Definition for Doubly-ListNode.
 * public class DoublyListNode {
 *     int val;
 *     DoublyListNode next, prev;
 *     DoublyListNode(int val) {
 *         this.val = val;
 *         this.next = this.prev = null;
 *     }
 * }
 */


/*
    Thoughts:
    Inorder with 1 stack: peek add left till end, pop and add, then push right node.

    Everytime when pop out a node and add, make it a new boubllistnode
        dNode.next = curr
        curr.pre = dNode.next
        dNode = dNode.next

    boarder case: if null, return a null.
*/
public class Sol114_2FlatternBinaryTreetoDoublyLinkedlist {
    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public class DoublyListNode {
        int val;
        DoublyListNode next, prev;

        DoublyListNode(int val) {
            this.val = val;
            this.next = this.prev = null;
        }
    }

    public DoublyListNode bstToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        //Init stack
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        stack.push(node);
        //Create DoublyListNode header
        DoublyListNode dummy = new DoublyListNode(0);
        DoublyListNode dNode = dummy;


        while (!stack.isEmpty()) {
            while (node != null && node.left != null) {
                stack.push(node.left);
                node = node.left;
            }
            //add node
            node = stack.pop();
            DoublyListNode curr = new DoublyListNode(node.val);
            dNode.next = curr;
            curr.prev = dNode;
            dNode = dNode.next;

            //check right node and add to stack
            node = node.right;
            if (node != null) {
                stack.push(node);
            }
        }

        return dummy.next;

    }

    // method 2: recursive  http://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
    static TreeNode prev;
    TreeNode head;
    void BinaryTree2DoubleLinkedList(TreeNode root)
    {
        // Base case
        if (root == null)
            return;

        // Recursively convert left subtree
        BinaryTree2DoubleLinkedList(root.left);

        // Now convert this node
        if (prev == null)
            head = root;
        else
        {
            root.left = prev;
            prev.right = root;
        }
        prev = root;

        // Finally convert right subtree
        BinaryTree2DoubleLinkedList(root.right);
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


        while (node1 != null) {
            System.out.println(node1.val);
            node1 = node1.right;

        }
    }
    void printList(TreeNode node)
    {
        while (node != null)
        {
            System.out.print(node.val + " ");
            node = node.right;
        }
    }

}

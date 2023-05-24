package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/*
Given a binary tree

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.



Example 1:

Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

Example 2:

Input: root = []
Output: []

 */
public class Sol117_PopulatingNextRightPointersinEachNodeII {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    // s1: level order traversal
    public Node connect(Node root) {
        if (root == null) return root;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                // 1 2 3
                if (i < size - 1) {
                    node.next = q.peek();
                }
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return root;
    }

    public Node connect1(Node root) {
        if (root == null) return root;
        Node dummy = new Node(0);
        Node p = dummy;
        Node head = root;
        while (head != null) { // if the head of the current layer is not null, then traverse this layer
            Node cur = head;
            while (cur != null) {
                if (cur.left != null) {
                    p.next = cur.left;
                    p = p.next;
                }
                if (cur.right != null) {
                    p.next = cur.right;
                    p = p.next;
                }
                cur = cur.next;  // move node to next in same level, end up null at rightmost
            }
            // after traversed to the end of the current layer, move to the next layer
            head = dummy.next; // next level start from the new head
            dummy.next = null;
            p = dummy;
        }
        return root;

    }
}

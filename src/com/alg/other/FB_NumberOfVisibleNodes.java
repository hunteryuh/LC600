package com.alg.other;

import java.util.LinkedList;
import java.util.Queue;

/*
There is a binary tree with N nodes. You are viewing the tree from its left side and can see only the leftmost nodes at each level.
Return the number of visible nodes.
Note: You can see only the leftmost nodes, but that doesn't mean they have to be left nodes. The leftmost node at a level could be a right node.
Signature
int visibleNodes(Node root) {
Input
The root node of a tree, where the number of nodes is between 1 and 1000, and the value of each node is between 0 and 1,000,000,000
Output
An int representing the number of visible nodes.
Example
            8  <------ root
           / \
         3    10
        / \     \
       1   6     14
          / \    /
         4   7  13
output = 4
 */
public class FB_NumberOfVisibleNodes {
    static class Node {
        int data;
        Node left;
        Node right;
        Node() {
            this.data = 0;
            this.left = null;
            this.right = null;
        }
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Add any helper functions you may need here


    public int visibleNodes(Node root) {
        // Write your code here
        if (root == null) return 0;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            res++;
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Node n1 = new Node(8);
        Node n2 = new Node(3);
        Node n3 = new Node(10);
        Node n4 = new Node(14);
        n1.left = n2;
        n1.right = n3;
        n3.right = n4;
        FB_NumberOfVisibleNodes f = new FB_NumberOfVisibleNodes();
        System.out.println(f.visibleNodes(n1));

    }
}

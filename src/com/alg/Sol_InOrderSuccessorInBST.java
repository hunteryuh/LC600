package com.alg;

public class Sol_InOrderSuccessorInBST {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };

    public Node findInOrderSuccessorInBST(Node node) {
        // right child is not null, go down to the most left
        if (node == null) return null;
        if (node.right != null) {
            return getMostLeft(node.right);
        }
        // right child is null, go up
        Node parent = node.parent;
        while (parent != null && parent.left != node) {
            node = parent;
            parent = parent.parent;
        }
        return parent;

    }

    Node getMostLeft(Node node) {
        if (node.left != null) {
            return getMostLeft(node.left);
        }
        return node;
    }

}

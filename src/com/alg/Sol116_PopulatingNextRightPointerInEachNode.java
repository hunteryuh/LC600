package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by HAU on 12/1/2017.
 */
public class Sol116_PopulatingNextRightPointerInEachNode {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }

    }
    public void connect(TreeLinkNode root){
        //recursive  Time Complexity - O(n)， Space Complexity - O(1).
        if ( root == null) return;
        if(root.left != null) {
            root.left.next = root.right;
            if (root.next != null) { // already points to the node on its right in the same level
                root.right.next = root.next.left;
            }
        }
        connect(root.left);
        connect(root.right);
    }
    public void connect2(TreeLinkNode root){
        /*两层循环，当root和root.left不为空的时候，我们要利用好自己创建的next节点来不断向右进行遍历。遍历完这一层以后我们可以设置root = root.left，这样我们到了下一层，继续向右进行遍历。

        Time Complexity - O(n)， Space Complexity - O(1)*/
        while(root != null && root.left!= null){
            TreeLinkNode cur = root;
            while(cur != null){
                cur.left.next = cur.right;
                if(cur.next != null) cur.right.next = cur.next.left;
                cur = cur.next;
            }
            root = root.left;
        }
    }

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

    public Node connect(Node root) {
        if (root == null)
            return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int s = queue.size();
            for (int i = 0; i < s; i++) {
                Node curr = queue.poll();
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
                if (i == s - 1)
                    curr.next = null;
                else
                    curr.next = queue.peek();
            }
        }
        return root;
    }
}

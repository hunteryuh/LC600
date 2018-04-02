package com.alg;

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
}

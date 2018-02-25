package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/*Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given depth d. The root node is at depth 1.

The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two tree nodes with value v as N's left subtree root and right subtree root. And N's original left subtree should be the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new right subtree root. If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root of the whole original tree, and the original tree is the new root's left subtree.

Example 1:
Input:
A binary tree as following:
       4
     /   \
    2     6
   / \   /
  3   1 5

v = 1

d = 2

Output:
       4
      / \
     1   1
    /     \
   2       6
  / \     /
 3   1   5

Example 2:
Input:
A binary tree as following:
      4
     /
    2
   / \
  3   1

v = 1

d = 3

Output:
      4
     /
    2
   / \
  1   1
 /     \
3       1*/
public class Sol623_AddOneRowToTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public static TreeNode addOneRow(TreeNode root, int v, int d) {
        // recursion dfs
        if ( d == 1){
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        insert(v,root,1,d);
        return root;
    }

    private static void insert(int val, TreeNode node, int depth, int n) {
        if ( node == null) return;
        if( depth == n-1){
            TreeNode temp = node.left;
            node.left = new TreeNode(val);
            node.left.left = temp; // original left subtree processed

            temp = node.right;
            node.right = new TreeNode(val);
            node.right.right = temp;
        }else{
            insert(val,node.left, depth + 1, n);
            insert(val,node.right, depth + 1, n);
        }
    }
    /*Time complexity : O(n). A total of n nodes of the given tree will be considered.

Space complexity : O(n). The depth of the recursion tree can go upto nn in the worst case(skewed tree).*/

    // method 2 ,using BFS and queue
    public static TreeNode add1Row(TreeNode root, int v, int d){
        if(d == 1){
            TreeNode t = new TreeNode(v);
            t.left = root;
            return t;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;
        while(depth < d - 1){
            Queue<TreeNode> temp = new LinkedList<>();
            while(!queue.isEmpty()){
                TreeNode node = queue.remove();
                if(node.left != null) temp.add(node.left);
                if(node.right != null) temp.add(node.right);
            }
            queue = temp;
            depth++;
        }
        while(!queue.isEmpty()){
            TreeNode node = queue.remove();
            TreeNode tmp = node.left;
            node.left = new TreeNode(v);
            node.left.left = tmp;

            tmp = node.right;
            node.right = new TreeNode(v);
            node.right.right = tmp;
        }
        return root;
    }
}

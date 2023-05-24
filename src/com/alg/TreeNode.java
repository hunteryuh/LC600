package com.alg;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    TreeNode() {}
    TreeNode(int x){
        val = x;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

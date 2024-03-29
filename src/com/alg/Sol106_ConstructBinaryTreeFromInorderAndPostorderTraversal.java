package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.



Example 1:


Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: inorder = [-1], postorder = [-1]
Output: [-1]


Constraints:

1 <= inorder.length <= 3000
postorder.length == inorder.length
-3000 <= inorder[i], postorder[i] <= 3000
inorder and postorder consist of unique values.
Each value of postorder also appears in inorder.
inorder is guaranteed to be the inorder traversal of the tree.
postorder is guaranteed to be the postorder traversal of the tree.
 */
public class Sol106_ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }


    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int length = inorder.length;
        if (length == 0 || postorder.length != length) return null;
        return helper(inorder, 0, length, postorder, 0, length);
        // left closed, right open 左闭右开区间
    }

    private TreeNode helper(int[] inorder, int inLeft, int inRight, int[] postorder, int postLeft, int postRight) {
        if (inRight == inLeft) {
            return null;
        }
        if (inRight - inLeft == 1) {
            return new TreeNode(inorder[inLeft]);
        }

        int rootVal = postorder[postRight - 1];
        int rootIndex = 0;
        for (int i = 0 ; i < inRight; i++) {
            if (rootVal == inorder[i]) {
                rootIndex = i;
                break;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = helper(inorder, inLeft, rootIndex, postorder, postLeft, postLeft + rootIndex - inLeft);
        root.right = helper(inorder, rootIndex + 1, inRight, postorder, postLeft + rootIndex - inLeft,postRight - 1);
        return root;
    }

    int[] inorder;
    int[] postorder;
    int n;
    Map<Integer, Integer> map = new HashMap<>();
    public  TreeNode buildTree2(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return helper(0, n-1, 0, n-1);
    }
    private TreeNode helper(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd) return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        int inIndex = map.get(root.val);
        int rightTreeSize = inEnd - inIndex;  // use inend not postend to get right tree size
        root.left = helper(inStart, inIndex - 1, postStart, postEnd - rightTreeSize - 1);
        root.right = helper(inIndex + 1, inEnd, postEnd - rightTreeSize, postEnd - 1);
        return root;
    }

}

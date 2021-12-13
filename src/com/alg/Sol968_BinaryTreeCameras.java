package com.alg;
/*
You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.

Return the minimum number of cameras needed to monitor all nodes of the tree.



Example 1:

Input: root = [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.

Example 2:

Input: root = [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.

 */
public class Sol968_BinaryTreeCameras {

    enum Camera {
        HAS_CAMERA, // 1
        COVERED,  // 2
        NOT_COVERED // 0
    }
    int res = 0;
    public int minCameraCover(TreeNode root) {
        Camera x = dfs(root);
        if (x == Camera.NOT_COVERED) {
            res++;
        }
        return res;
    }

    private Camera dfs(TreeNode node) {
        if (node == null) return Camera.COVERED;
        Camera left = dfs(node.left);
        Camera right = dfs(node.right);

        // branch 1
        if (left == Camera.COVERED && right == Camera.COVERED) {
            return Camera.NOT_COVERED;
        }
        // branch 2
        if (left == Camera.NOT_COVERED || right == Camera.NOT_COVERED) {
            res++;
            return Camera.HAS_CAMERA;
        }
        // branch 1 and 2 can be swapped
        // branch 3
        // this has to check at the end (after branch 1 and 2) to not cover the case where left or right is NOT_COVERED
        if (left == Camera.HAS_CAMERA || right == Camera.HAS_CAMERA) {
            return Camera.COVERED;
        }
        return null;
    }
}

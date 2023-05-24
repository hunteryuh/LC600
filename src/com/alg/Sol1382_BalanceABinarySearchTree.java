package com.alg;

import java.util.ArrayList;
import java.util.List;

/*
Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.

A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.



Example 1:


Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
Example 2:


Input: root = [2,1,3]
Output: [2,1,3]


Constraints:

The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 105
 */
public class Sol1382_BalanceABinarySearchTree {
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();  // compress it to a linear list, inorder traversal
        dfs(root, list);
        return build(list, 0, list.size() - 1);  // recursion
    }
    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }
    private TreeNode build(List<Integer> list, int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = build(list, start, mid - 1);
        root.right = build(list, mid + 1, end);
        return root;
    }
}

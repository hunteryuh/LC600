package com.alg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Given the root of a binary tree and an array of TreeNode objects nodes, return the lowest common ancestor (LCA) of all the nodes in nodes. All the nodes will exist in the tree, and all values of the tree's nodes are unique.

Extending the definition of LCA on Wikipedia: "The lowest common ancestor of n nodes p1, p2, ..., pn in a binary tree T is the lowest node that has every pi as a descendant (where we allow a node to be a descendant of itself) for every valid i". A descendant of a node x is a node y that is on the path from node x to some leaf node.



Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [4,7]
Output: 2
Explanation: The lowest common ancestor of nodes 4 and 7 is node 2.

Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [1]
Output: 1
Explanation: The lowest common ancestor of a single node is the node itself.

Example 3:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [7,6,2,4]
Output: 5
Explanation: The lowest common ancestor of the nodes 7, 6, 2, and 4 is node 5.



Constraints:

    The number of nodes in the tree is in the range [1, 104].
    -109 <= Node.val <= 109
    All Node.val are unique.
    All nodes[i] will exist in the tree.
    All nodes[i] are distinct.


 */
public class Sol1676_LowestCommonAncestorOfABinaryTreeIV {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<Integer> set = new HashSet<>();
        for (TreeNode node : nodes) set.add(node.val);
        return dfs(root, set);
    }
    private TreeNode dfs(TreeNode root, Set<Integer> set) {
        if (root == null) return null;
        if (set.contains(root.val)) return root; // this is to prevent from going further down to subtree of a target node
        TreeNode left = dfs(root.left, set);
        TreeNode right = dfs(root.right, set);
        if (left != null && right != null) return root; // answer
        if (left != null) return left;
        if (right != null) return right;
        return null;
    }
}

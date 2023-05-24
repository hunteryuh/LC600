package com.alg;
/*
Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q. If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.

According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.



Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.

Example 3:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
Output: null
Explanation: Node 10 does not exist in the tree, so return null.



Constraints:

    The number of nodes in the tree is in the range [1, 104].
    -109 <= Node.val <= 109
    All Node.val are unique.
    p != q

 */
public class Sol1644_LCAofBinaryTreeII {
    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/discuss/933835/Java.-Difference-from-236-is-you-need-to-search-the-entire-tree.
    boolean pFound = false;
    boolean qFound = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = LCA(root, p, q);
        return pFound && qFound ? lca : null;
    }
    private TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        TreeNode left = LCA(root.left, p, q);
        TreeNode right = LCA(root.right, p, q);
        if (root == p) {
            pFound = true;
            return p; // stop early and enough
        }
        if (root == q) {
            qFound = true;
            return q;
        }
        return (left == null) ? right : ((right == null) ? left : root);
    }

    // two pass based on Sol 236
    public TreeNode lowestCA(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode pNode = dfs(root, p.val);
        TreeNode qNode = dfs(root, q.val);
        if (pNode == null || qNode == null) {
            return null;
        }
        return lca(root, p, q);
    }
    private TreeNode dfs(TreeNode cur, int target) {
        if (cur == null) return null;
        if (cur.val == target) return cur;
        TreeNode left = dfs(cur.left, target);
        TreeNode right = dfs(cur.right, target);
        return left == null ? right : left;
    }
    private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lca(root.left, p, q);
        TreeNode right = lca(root.right, p, q);
        return left == null? right : right == null ? left : root;
    }
}

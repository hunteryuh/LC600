package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Created by HAU on 11/25/2017.
 */
/*Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node
in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”*/
public class Sol236_LowestCommonAncestorofABinaryTree {

    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/discuss/1672355/Java-or-Intuition-or-Explanation
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right!= null) { // which means p,q exist below different subtrees;
            return root;
        }
        return left != null? left: right; // which means p,q exist below the same subtree;
        //  return left == null ? right : right == null ? left : root;
    }

    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right!= null) {
            return root;
        }

        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }

    public static void main(String[] args) {
        //      8
        //    /   \
        //   2     10
        //  / \   /
        // 1   5 9
        //    /
        //   3
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(9);
        TreeNode node7 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node5.left = node7;
        System.out.println(lowestCommonAncestor(node1, node3, node7).val); //8
        System.out.println(lowestCommonAncestor2(node1, node3, node7).val); //8
        System.out.println(lowestCommonAncestor(node1, node2, node7).val); //2
        System.out.println(lowestCommonAncestor2(node1, node2, node7).val); //2
        Sol236_LowestCommonAncestorofABinaryTree ss = new Sol236_LowestCommonAncestorofABinaryTree();
        List<Integer> paths = ss.findPathsBetweenTwoNodes(node1, node4, node7);
        System.out.println(paths);
    }

    // https://www.geeksforgeeks.org/print-path-between-any-two-nodes-in-a-binary-tree/
    // https://codeforces.com/blog/entry/53220  dfs
    public List<Integer> findPathsBetweenTwoNodes(TreeNode root, TreeNode n1, TreeNode n2) {
        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();
        getPath(root, n1, path1);
        getPath(root, n2, path2);
        int i = 0;
        int j = 0;
        int lca = 0;
        while (i < path1.size() || j < path2.size()) {
            if (i == j && path1.get(i) == path2.get(j)) {
                i++; j++;
            } else {
                lca = i -1;
                break;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (i = path1.size() - 1; i>lca; i--) {
            res.add(path1.get(i).val);
        }
        for (i = lca; i < path2.size(); i++) {
            res.add(path2.get(i).val);
        }
        return res;
    }

    private boolean getPath(TreeNode root, TreeNode node, List<TreeNode> list) {
        if (root == null) return false;
        list.add(root);
        if (root == node) {
            return true;
        }
        if (getPath(root.left, node, list) || getPath(root.right, node, list)) {
            return true;
        }
        list.remove(list.size() - 1);
        return false;
    }

    // parent pointer, iterative
    Map<TreeNode, TreeNode> parents = new HashMap<>();
    public TreeNode lowestCA(TreeNode root, TreeNode p, TreeNode q) {
        dfs(null, root);
        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parents.get(p);
        }
        while (!ancestors.contains(q)) {
            q = parents.get(q);
        }
        return q;
    }
    private void dfs(TreeNode par, TreeNode cur) {
        if (cur == null) return;
        parents.put(cur, par);
        dfs(cur, cur.left);
        dfs(cur, cur.right);
    }

}

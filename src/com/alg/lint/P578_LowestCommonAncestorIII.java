package com.alg.lint;
/*
Description
Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
The nearest common ancestor of two nodes refers to the nearest common node among all the parent nodes of two nodes (including the two nodes).
Return null if LCA does not exist.

node A or node B may not exist in tree.
Each node has a different value
 */


public class P578_LowestCommonAncestorIII {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x){
            val = x;
            this.left = this.right = null;
        }
    }

    public static class ResultType {
        public boolean aExist;
        public boolean bExist;
        public TreeNode node;
        ResultType(boolean aExist, boolean bExist, TreeNode node) {
            this.aExist = aExist;
            this.bExist = bExist;
            this.node = node;
        }
    }

    /*
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here

        ResultType resultType = helper(root, A, B);
        if (resultType.bExist && resultType.aExist) {
            return resultType.node;
        }
        return null;
    }

    private static ResultType helper(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return new ResultType(false, false, null);
        }

        ResultType left = helper(root.left, a, b);
        ResultType right = helper(root.right, a, b);

        boolean aInTree = left.aExist || right.aExist || root == a;
        boolean bInTree = left.bExist || right.bExist || root == b;

        if (root == a || root == b) {
            return new ResultType(aInTree, bInTree, root);
        }

        if (left.node != null && right.node != null) {
            return new ResultType(aInTree, bInTree, root);
        }
        if (left.node != null) {
            return  new ResultType(aInTree, bInTree, left.node);
        }
        if (right.node != null) {
            return new ResultType(aInTree, bInTree, right.node);
        }
        return new ResultType(aInTree, bInTree, null);
    }


    // method2, without ResultType
    private static boolean aExist;
    private static boolean bExist;
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        TreeNode node = divConq(root, A, B);
        if (aExist && bExist) {
            return node;
        }
        return null;
    }

    private static TreeNode divConq(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return null;
        }

        TreeNode left = divConq(root.left, a, b);
        TreeNode right = divConq(root.right, a, b);

        // 如果 root 是要找的，更新全局变量  ( need to put this check after divConq)
        if (root == a || root == b) {
            aExist = (root == a) || aExist;
            bExist = (root == b) || bExist;
            return root;
        }

        // 和 LCA 原题的思路一样
        if (left != null && right != null) {
            return root;
        }

        if (left != null) {  // 注意这种情况返回的时候是不保证两个都有找到的。可以是只找到一个或者两个都找到
            return left;
        }

        return right;
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

        TreeNode nodex = new TreeNode(13);

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
//        System.out.println(lowestCommonAncestor(node1, node2, nodex).val);
        System.out.println(lowestCommonAncestor2(node1, node2, nodex));
    }
}

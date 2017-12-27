package com.alg;

/**
 * Created by HAU on 12/27/2017.
 */
/*distance between two nodes in a bst*/
public class Sol0_amz_disNodesinBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    // build bst and get Distance
    public static int bstDist(int[] a, int p , int q){
        if ( a == null || a.length == 0) return 0;
        int res = 0;
        // build bst
        TreeNode root = buildBST(a);
        // check if p,q exist in the BST
        if(bstSearch(root,q) && bstSearch(root,p)){
            // find lca lowest comman ancestor of p and q
            TreeNode lca = findLCA(root,p,q);
            // find length between Lca - p and lca - q
            res += getDist(lca,p);
            res += getDist(lca,q);
        }else{
            res = -1;
        }
        return res;
    }

    private static int getDist(TreeNode node, int data) {
        if(node == null) return 0;
        int dist = 0;
        while (node != null){
            if (node.val == data){
                break;
            }else if( node.val > data){
                node = node.left;
                dist++;
            }else {
                node = node.right;
                dist++;
            }
        }
        return dist;
    }

    // // find LCA in BST
    private static TreeNode findLCA(TreeNode root, int p, int q) {
        if ( root == null) return null;
        while (true){
            if (root.val > Math.max(p,q)){
                root = root.left;
            }else if (root.val < Math.min(p,q)){
                root = root.right;
            }else // current root is the lca
                break;
        }
        return root;
    }

    private static boolean bstSearch(TreeNode root, int val) {
        boolean res = false;
        while(root!= null){
            if (root.val == val){
                res = true;
            }else if( val < root.val){
                root = root.left;
            }else root = root.right;
        }
        return res;
    }

    private static TreeNode buildBST(int[] a) {
        TreeNode root = new TreeNode(a[0]);
        for (int i = 1; i < a.length; i++){
            createBST(root,a[i]);
        }
        return root;
    }

    private static void createBST(TreeNode root, int val) {
        if( val < root.val){
            if(root.left == null){
                root.left = new TreeNode(val);
            }else{
                createBST(root.left,val);
            }
        }else{
            if(root.right == null){
                root.right = new TreeNode(val);
            }else{
                createBST(root.right,val);
            }
        }
    }
}

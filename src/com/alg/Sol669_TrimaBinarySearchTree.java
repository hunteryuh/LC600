package com.alg;
/*Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

Example 1:
Input:
    1
   / \
  0   2

  L = 1
  R = 2

Output:
    1
      \
       2
Example 2:
Input:
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

Output:
      3
     /
   2
  /
 1*/
public class Sol669_TrimaBinarySearchTree {
    // recursion
    public TreeNode trimBST(TreeNode root, int L, int R){
        if (root == null) return root;
        if(root.val > R) return trimBST(root.left,L,R);
        if(root.val < L) return trimBST(root.right,L, R);

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }

    public TreeNode trimBST2(TreeNode root, int L, int R) {
        if (root == null) {
            return root;
        }
//        while (root != null) { // this may cause root never change and dead loop
//            if (root.val > R) {
//                root = root.left;
//            } else if (root.val < L) {
//                root = root.right;
//            }
//        }

        while (root != null && (root.val < L || root.val >  R)) {
            if (root.val < L)
                root = root.right;
            if (root.val > R)
                root = root.left;
        }
        // now root.val is in [L, R]
        TreeNode cur = root;
        while (cur != null) {
            while (cur.left != null && cur.left.val < L) {
                cur.left = cur.left.right;
            }
            cur = cur.left;
        }
        cur = root;
        while (cur != null) {
            while (cur.right != null && cur.right.val > R) {
                cur.right = cur.right.left;
            }
            cur = cur.right;
        }
        return root;
    }
}

package com.alg;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3*/
public class Sol95_UniqueBinarySearchTreeII {

    // https://leetcode.com/problems/unique-binary-search-trees-ii/discuss/1023791/Java-Recursion-Along-with-Recursion-Tree-Figure-Explanation
    public List<TreeNode> generateTrees(int n) {
        if( n == 0) return new LinkedList<>();
        return genTrees(1,n);
    }

    private static List<TreeNode> genTrees(int start, int end) {
        List<TreeNode> res = new LinkedList<>();
        if ( start > end){
            res.add(null);
            return res;
        }
        for(int i = start; i <= end; i++){
            List<TreeNode> lefttree = genTrees(start, i - 1);
            List<TreeNode> righttree = genTrees(i + 1, end);
            for(TreeNode left: lefttree ){
                for(TreeNode right: righttree){
                    // 这里剩最后一个node的时候，leftchild和rightChild都为空
                    TreeNode root = new TreeNode(i);
                    root.left = left;  //这里left 在经过recursion之后包含 了整个subtree
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
        //F(i) = G(i-1) * G(n-i)
    }

    public static void main(String[] args) {
        int n = 3;
        Sol95_UniqueBinarySearchTreeII ss = new Sol95_UniqueBinarySearchTreeII();
        List<TreeNode> list = ss.generateTrees(n);
        System.out.println(list);
    }
}

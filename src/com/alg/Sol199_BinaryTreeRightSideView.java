package com.alg;

import java.util.*;

/**
 * Created by HAU on 1/22/2018.
 */
/*Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \
  5              <---
 /
4                <---
You should return [1, 3, 4].*/
public class Sol199_BinaryTreeRightSideView {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    public static List<Integer> rightSideView(TreeNode root) {
        Map<Integer,Integer> rightMostValueAtDepth = new HashMap<>();
        int maxDepth = -1;
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();
        nodeStack.push(root);
        depthStack.push(0);
        while(!nodeStack.isEmpty()){
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();

            if ( node != null){
                maxDepth = Math.max(depth,maxDepth);

                if(!rightMostValueAtDepth.containsKey(depth)){
                    rightMostValueAtDepth.put(depth,node.val);
                }
                nodeStack.push(node.left);
                nodeStack.push(node.right); // will be popped first in next loop
                depthStack.push(depth + 1);
                depthStack.push(depth + 1);
            }
        }
        List<Integer> rightView = new ArrayList<>();
        for(int depth = 0; depth <= maxDepth; depth++){
            rightView.add(rightMostValueAtDepth.get(depth));
        }
        return rightView;
    }

    public static List<Integer> rightSideView2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    res.add(node.val);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
             }
        }
        return res;

    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(4);
        n1.left = n2;
        n1.right = n3;
        n2.right = n4;
        n4.left = n5;
//        System.out.println(rightSideView(n1));   // [1,3,5,6]
        System.out.println(leftSideView(n1));   //

    }

    public static List<Integer> leftSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, 0, list);
        return list;
    }

    private static void dfs(TreeNode root, int level, List<Integer> list) {
        if (root == null) return;

        if (list.size() == level) {
            list.add(root.val);
        }
        dfs(root.left, level + 1, list);
        dfs(root.right, level + 1, list);

    }
}

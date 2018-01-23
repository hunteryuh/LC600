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
 \     \
  5     4       <---
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
}

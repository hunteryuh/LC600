package com.alg.lint;
/*
Description
Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.

The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.

The node has an extra attribute parent which point to the father of itself. The root's parent is null.

Example 1:

Input：{4,3,7,#,#,5,6},3,5
Output：4
Explanation：
     4
     / \
    3   7
       / \
      5   6
LCA(3, 5) = 4
Example 2:

Input：{4,3,7,#,#,5,6},5,6
Output：7
Explanation：
      4
     / \
    3   7
       / \
      5   6
LCA(5, 6) = 7
 */



import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Definition of ParentTreeNode:
 *
 * class ParentTreeNode {
 *     public ParentTreeNode parent, left, right;
 * }
 */

public class P474_LowestCommonAncestorII {

    public static class ParentTreeNode {
        int val;
        ParentTreeNode left;
        ParentTreeNode right;
        ParentTreeNode parent;
        ParentTreeNode(int x){
            val = x;
        }
    }
    /*
     * @param root: The root of the tree
     * @param A: node in the tree
     * @param B: node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        // write your code here

        Set<ParentTreeNode> set = new HashSet<>();
        ParentTreeNode current = A;
        while (current != null) {
            set.add(current);
            current = current.parent;
        }

        // iterate B's parent, if found for the first time in the set, it is the lowest common ancestor
        current = B;
        while (current != null) {
            if (set.contains(current)) {
                return current;
            }
            current = current.parent;
        }
        return null;
    }

    // approach 2 to get 2 paths for 2 nodes
    public ParentTreeNode lowestCommonAncestorII_2(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        // write your code here
        ArrayList<ParentTreeNode> patha = getPathToRoot(A);
        ArrayList<ParentTreeNode> pathb = getPathToRoot(B);

        int indexa = patha.size() - 1;
        int indexb = pathb.size() - 1;
        ParentTreeNode result = null;
        while (indexa >=0 && indexb >= 9) {
            if (patha.get(indexa) != pathb.get(indexb)) {
                return result;  // or break;
            }
            result = patha.get(indexa);
            indexa--;
            indexb--;
        }
        return result;
    }

    private ArrayList<ParentTreeNode> getPathToRoot(ParentTreeNode A) {
        ArrayList<ParentTreeNode> path = new ArrayList<>();
        ParentTreeNode node = A;
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        return path;
    }
}

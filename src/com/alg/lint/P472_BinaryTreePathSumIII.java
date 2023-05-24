package com.alg.lint;
/**
 * Give a binary tree, and a target number, find all path that the sum of   nodes
 * equal to target, the path could be start and end at any node in the tree.
 */

/*
    Example
    Given binary tree:
        1
       / \
      2   3
     /
    4
    and target = 6. Return :
    [
      [2, 4],
      [2, 1, 3],
      [3, 1, 2],
      [4, 2]
    ]
 */


// https://www.jiuzhang.com/problem/binary-tree-path-sum-iii/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Definition of ParentTreeNode:
 *
 * class ParentTreeNode {
 *     public int val;
 *     public ParentTreeNode parent, left, right;
 * }
 */
public class P472_BinaryTreePathSumIII {

    class ParentTreeNode {
       public int val;
       public ParentTreeNode parent, left, right;
    }

    /**
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    public List<List<Integer>> binaryTreePathSum3(ParentTreeNode root, int target) {
        // Write your code here
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        dfs(root, target, results);
        return results;
    }

    public void dfs(ParentTreeNode root, int target, List<List<Integer>> results) {
        if (root == null)
            return;

        List<Integer> path = new ArrayList<Integer>();
        findSum(root, null, target, path, results);

        dfs(root.left, target, results);
        dfs(root.right, target, results);
    }

    public void findSum(ParentTreeNode root, ParentTreeNode father, int target,
                        List<Integer> path, List<List<Integer>> results) {
        path.add(root.val);
        target -= root.val;

        if (target == 0) {
            ///* Deep copy an ArrayList */
//            ArrayList<Integer> tmp = new ArrayList<Integer>();
//            Collections.addAll(tmp,  new Integer[path.size()]);
//            Collections.copy(tmp, path);
//            results.add(tmp);
            results.add(new ArrayList<>(path));
        }

        // father indicates a visited node in the graph
        if (root.parent != null && root.parent != father)
            findSum(root.parent, root, target, path, results);

        if (root.left != null && root.left != father)
            findSum(root.left, root, target, path, results);

        if (root.right != null && root.right != father)
            findSum(root.right, root, target, path, results);

        //DFS backtracking
        path.remove(path.size() - 1);
    }
}

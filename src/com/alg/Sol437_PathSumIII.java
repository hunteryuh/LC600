package com.alg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HAU on 1/16/2018.
 */
/*You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf,
but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11*/
public class Sol437_PathSumIII {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public static int pathSum(TreeNode root, int sum) {
        if ( root == null) {
            return 0;
        }
        return findPath(root,sum) + pathSum(root.left,sum) + pathSum(root.right,sum);
    }

    private static int findPath(TreeNode root, int sum) {
        int res = 0;
        if (root == null) return res;
        if (sum == root.val) {
            res++;
        }
        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);
        return res;
    }

    // https://leetcode.com/problems/path-sum-iii/solutions/1525060/easy-solution-using-java-dfs-map/
    // method 2, presum
    public static int pathSum2(TreeNode root, int sum){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);// if sum = 0, it has 1 path by default
        return backtrack(root, 0, sum, map);
    }

    private static int backtrack(TreeNode root, int sum, int target, Map<Integer, Integer> map) {
        if ( root == null) return 0;
        sum += root.val;
        // check if there is a subarray equals to target
        int count = map.getOrDefault(sum - target, 0);
        map.put(sum, map.getOrDefault(sum,0)+1);
        // extend to left and right child
        count += backtrack(root.left,sum, target, map) + backtrack(root.right,sum,target,map);
        // remove the current node so it won't affect other path
        map.put(sum, map.get(sum) - 1);
        return count;
    }

}

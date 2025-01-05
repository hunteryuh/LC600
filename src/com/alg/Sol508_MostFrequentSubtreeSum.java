package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given the root of a binary tree, return the most frequent subtree sum. If there is a tie, return all the values with the highest frequency in any order.

The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).



Example 1:

Input: root = [5,2,-3]
Output: [2,-3,4]

Example 2:

Input: root = [5,2,-5]
Output: [2]



Constraints:

    The number of nodes in the tree is in the range [1, 104].
    -105 <= Node.val <= 105

 */
public class Sol508_MostFrequentSubtreeSum {
    // post-order traversal
    // time O(n)
    private Map<Integer, Integer> sumFreq = new HashMap<>();
    private Integer maxFreq = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        subtreeSum(root);
        List<Integer> result = new ArrayList<>();
        for (int key: sumFreq.keySet()) {
            int sum = key;
            int freq = sumFreq.get(sum);
            if (freq == maxFreq) {
                result.add(sum);
            }
        }
        int maxFreqSum[] = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            maxFreqSum[i] = result.get(i);
        }
        return maxFreqSum;
//        return result.stream().mapToInt(i -> i).toArray();
    }
    private int subtreeSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSubtreeSum = subtreeSum(root.left);
        int rightSubTreeSum = subtreeSum(root.right);
        int curSum = root.val + leftSubtreeSum + rightSubTreeSum;
        sumFreq.put(curSum, sumFreq.getOrDefault(curSum, 0) + 1);
        maxFreq = Math.max(maxFreq, sumFreq.get(curSum));
        return curSum;
    }
}

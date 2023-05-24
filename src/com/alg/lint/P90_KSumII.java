package com.alg.lint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Description
Given n distinct positive integers, the integer k (1<=k<=n1<=k<=n) and a target number.

Find k distinct numbers within these n numbers such that the sum of these k numbers equals the target number,
and you need to find all the solutions that satisfy the requirement (the order of the solutions is not required).
Example
Example 1:

Input:

array = [1,2,3,4]
k = 2
target = 5
Output:

[[1,4],[2,3]]
Explanation:

1+4=5,2+3=5

Example 2:

Input:

array = [1,3,4,6]
k = 3
target = 8
Output:

[[1,3,4]]
Explanation:

1+3+4=8
 */

// get length  (getlength of a lsit/arry)
// array  .length
// arraylist .size()
// string .length()
public class P90_KSumII {
    public List<List<Integer>> kSumII(int[] a, int k, int target) {
        // 2^N *k

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(a);
        dfs(a, 0, k, target, result, new ArrayList<>());
        return result;
    }

    private void dfs(int[] a, int start, int remainingCount, int remainingTarget, List<List<Integer>> result, ArrayList<Integer> subset) {
        if (remainingCount == 0 && remainingTarget == 0) {
            result.add(new ArrayList<>(subset)); // here the time is O(k) since it needs to loop through all elements in the set
            return;
        }
        if (remainingCount == 0) return;  // alright select k and did not meet target, stop early
        for (int i = start; i < a.length; i++) {// optimize to a.length - (k?)
            if (a[start] > remainingTarget) return; // starting value too large, break early
            subset.add(a[i]);
            dfs(a, i + 1, remainingCount - 1, remainingTarget - a[i], result, subset);
            subset.remove(subset.size() - 1);
        }
    }

}

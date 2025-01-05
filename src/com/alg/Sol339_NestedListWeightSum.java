package com.alg;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by HAU on 12/1/2017.
 */
public class Sol339_NestedListWeightSum {
    public interface NestedInteger {
        // public NestedInteger();
        // @return true if this NestedInteger holds a single integer,
             // rather than a nested list.
              public boolean isInteger();

              // @return the single integer that this NestedInteger holds,
              // if it holds a single integer
              // Return null if this NestedInteger holds a nested list
              public Integer getInteger();

              // @return the nested list that this NestedInteger holds,
         // if it holds a nested list
              // Return null if this NestedInteger holds a single integer
              public List<NestedInteger> getList();
    }
    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList,1);

    }

    private int dfs(List<NestedInteger> list, int depth) {
        int sum = 0;
        for (NestedInteger n: list) {
            if (n.isInteger()) {
                sum += n.getInteger() * depth;
            } else {
                sum += dfs(n.getList(),depth + 1);
            }
        }
        return sum;
    }

    public int depthSum2(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);
        int depth = 1;
        int total = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger nested = queue.poll();
                if (nested.isInteger()) {
                    total += nested.getInteger() * depth;
                } else {
                    queue.addAll(nested.getList());
                }
            }
            depth++;
        }
        return total;
    }
}

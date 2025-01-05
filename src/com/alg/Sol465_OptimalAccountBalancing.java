package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given an array of transactions transactions where transactions[i] = [fromi, toi, amounti] indicates that the person with ID = fromi gave amounti $ to the person with ID = toi.

Return the minimum number of transactions required to settle the debt.



Example 1:

Input: transactions = [[0,1,10],[2,0,5]]
Output: 2
Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.
Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.

Example 2:

Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
Output: 1
Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.
Therefore, person #1 only need to give person #0 $4, and all debt is settled.



Constraints:

    1 <= transactions.length <= 8
    transactions[i].length == 3
    0 <= fromi, tboi < 12
    fromi != toi
    1 <= amounti <= 100


 */
public class Sol465_OptimalAccountBalancing {
    // https://leetcode.com/problems/optimal-account-balancing/solutions/95355/concise-9ms-dfs-solution-detailed-explanation/
    // time: O ((n-1)!)
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        // map of each person to its balance after all transactions
        for (int[] t: transactions) {
            map.put(t[0], map.getOrDefault(t[0], 0) - t[2]);
            map.put(t[1], map.getOrDefault(t[1], 0) + t[2]);
        }
        return dfssettle(0, new ArrayList<>(map.values()));
    }
    private int dfssettle(int cur, List<Integer> debt) {
        while (cur < debt.size() && debt.get(cur) == 0) {
            cur++;
        }
        if (cur == debt.size()) return 0;
        int r = Integer.MAX_VALUE;
        for (int i = cur + 1; i < debt.size(); i++) {
            // if i (next) is a valid recipient, do the following
            // 1. add cur's value to next
            // 2. recursively call dfs(cur + 1)
            // 3. remove cur's balance from next
            if (debt.get(i) * debt.get(cur) < 0) {
                debt.set(i, debt.get(i) + debt.get(cur));
                r = Math.min(r, 1 + dfssettle( cur + 1, debt));
                debt.set(i, debt.get(i) - debt.get(cur)); // backtrack
            }
        }
        return r;
    }
}

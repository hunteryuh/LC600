package com.alg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Sol403_FrogJump {
    //O(n^2) dp
    public boolean canCross(int[] stones) {
        int n = stones.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], new HashSet<>());
        }
        map.get(stones[0]).add(1); // stonevalue, curStep
        int last = stones[n-1];
        for (int i = 0; i < n; i++) {
            for (int step: map.get(stones[i])) {
                int temp = stones[i]  + step;
                if (temp == last) return true;
                if (map.get(temp) != null) {
                    map.get(temp).add(step);
                    map.get(temp).add(step + 1);
                    if (step - 1 > 0) {
                        map.get(temp).add(step - 1);
                    }
                }

            }

        }
        return false;
    }
}

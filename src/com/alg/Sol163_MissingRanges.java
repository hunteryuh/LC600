package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sol163_MissingRanges {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        int l = lower;
        List<List<Integer>> res = new ArrayList();
        for (int i : nums) {
            if (i > l) {
                res.add(Arrays.asList(l, i - 1));
            }
            l = i + 1; // compare to next i
        }
        if (upper >= l) {
            res.add(Arrays.asList(l, upper));
        }

        return res;
    }
}

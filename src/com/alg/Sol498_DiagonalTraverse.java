package com.alg;

import java.util.*;

public class Sol498_DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[m * n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i< m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = i + j;
                if (!map.containsKey(sum)) {
                    map.put(sum, new ArrayList<>());
                }
                map.get(sum).add(matrix[i][j]);
            }
        }
        int index = 0;
//        for (int key: map.keySet()) { // it will loop from smaller to larger keys, a better way is to use the range of the keys per the matrix
//            List<Integer> list = map.get(key);
//            if (key % 2 == 0) {
//                Collections.reverse(list);
//            }
//            for (int i = 0; i < list.size(); i++) {
//                res[index++] = list.get(i);
//            }
//        }
        for (int i = 0; i < m + n - 1; i++) {
            List<Integer> list = map.get(i);
            if (i % 2 == 0) {
                Collections.reverse(list);
            }
            for (int x : list) {
                res[index++] = x;
            }
        }
        return res;
    }
}

package com.alg.lint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000)
and an query list. For each query, give you an integer, return the number of element in the array
that are smaller than the given integer.
https://www.jiuzhang.com/problem/count-of-smaller-number/
 */
public class P248_CountOfSmallerNumbers {
    /**
     * @param A: An integer array
     * @param queries: The query list
     * @return: The number of element in the array that are smaller that the given integer
     */
    public static List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();

        Arrays.sort(A);  // nlogn, not as good as segment tree
        for (int i = 0; i < queries.length; i++) {
            result.add(countWithBinarySearch(A, queries[i]));
        }
        return result;
    }

    private static int countWithBinarySearch(int[] a, int target) {
        if(a.length == 0){
            return 0;
        }
        int start = 0;
        int end = a.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (a[mid] < target) {
                start = mid;
            } else if (a[mid] > target) {
                end = mid;
            } else {
                end = mid;
            }
        }
        if (a[end] < target) {
            return end + 1;
        }
        if (a[start] < target) {
            return start + 1;
        }
        return start;
    }

    public static void main(String[] args) {
        int[] array = {1,2,7,8,5};
        int[] queries = {1,8,5};
        System.out.println(countOfSmallerNumber(array, queries));  // [0,4,2]

        int[] array2 = {3,4,8,5};
        int[] queries2 = {2,4};
        System.out.println(countOfSmallerNumber(array2, queries2));  // [0,1]

        int[] array3 = {};
        int[] queries3 = {2,4};
        System.out.println(countOfSmallerNumber(array3, queries3));  // [0,0]
    }
}

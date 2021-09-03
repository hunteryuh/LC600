package com.alg.lint;

import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
Give you an integer array (index from 0 to n-1, where n is the size of this array, data value from 0 to 10000) .
For each element Ai in the array,
count the number of element before this element Ai is smaller than it and return count number array.
Example
Example 1:

Input:
[1,2,7,8,5]
Output:
[0,1,2,3,2]
Example 2:

Input:
[7,8,2,1,3]
Output:
[0,1,0,0,2]
https://www.jiuzhang.com/problem/count-of-smaller-number-before-itself/
 */
public class P249_CountOfSmallerNumberBeforeItself {
    /**
     * @param A: an integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public static List<Integer> countOfSmallerNumberBeforeItself(int[] A) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (A == null || A.length == 0) {
            return result;
        }

        ArrayList<Integer> helperList = new ArrayList<>();
//        List<Integer> list = new ArrayList<>(A.length);
//        for (int a : A) {
//            list.add(a);
//        }
//        Collections.sort(list);
//
//        Set<Integer> set = new TreeSet<>();
//        for (int num: A) {
//            set.add(num);
//        }
//        int[] sorted = new int[set.size()];
//        int index = 0;
//        for (int num: set) {
//            sorted[index] = num;
//            index++;
//        }


        for (int i = 0; i < A.length; i++) {
            int count = countWithBinarySearch(helperList, A[i]);
            result.add(count);
            helperList.add(A[i]);
            Collections.sort(helperList);
        }
        return result;
    }


    //binary search
    private static int countWithBinarySearch(ArrayList<Integer> a, int target) {
        if(a.size() == 0){
            return 0;
        }
        int start = 0;
        int end = a.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (a.get(mid) < target) {
                start = mid;
            } else if (a.get(mid) > target) {
                end = mid;
            } else {
                end = mid;
            }
        }
        if (a.get(end) < target) {
            return end + 1;
        }
        if (a.get(start) < target) {
            return start + 1;
        }
        return start;
    }
    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }
            result.add(count);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {3,2,7,8,5};
        System.out.println(countOfSmallerNumberBeforeItself(array));  // [0,0,2,3,2]
        System.out.println(countSmaller(array));  // [0,0,2,3,2]

        int[] array2 = {3,4,8,5};
        System.out.println(countOfSmallerNumberBeforeItself(array2));  //

        int[] array3 = {7,8,2,1,3};
        System.out.println(countOfSmallerNumberBeforeItself(array3));  //
    }
}

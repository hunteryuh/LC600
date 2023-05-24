package com.alg.lint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Description
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
https://www.lintcode.com/problem/count-of-smaller-numbers-after-self/
https://www.jiuzhang.com/problem/count-of-smaller-numbers-after-self/

Example
Example 1

Input: [5, 2, 6, 1]
Output: [2, 1, 1, 0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

Example 2

Input: [1, 2, 3, 4]
Output: [0, 0, 0, 0]
 */
public class P1297_CountOfSmallerNumbersAfterSelf {
    /**
     * @param nums: a list of integers
     * @return: return a list of integers
     */
    public static List<Integer> countSmallerAfterItself(int[] nums) {
        // write your code here
        int[] result = new int[nums.length];
        List<Integer> sorted = new ArrayList<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            int count = binarySearchHelper(sorted, nums[i]); // how many are less than the target
            result[i] = count;
            sorted.add(count, nums[i]); // will be its index in the "list",
            // the list add operation will shift the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
        }
        List<Integer> answer = new ArrayList<>();
        for (int i : result) {
            answer.add(i);
        }
        return answer;
    }

    public static int binarySearchHelper(List<Integer> a, int target) {
        // count how many are smaller than target in the list
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

    public static void main(String[] args) {
        int[] array = {1,2,7,8,5};
        int[] queries = {1,8,5};
        System.out.println(countSmallerAfterItself(array));  // [0,4,2]

        int[] array2 = {3,4,8,5};
        int[] queries2 = {2,4};
        System.out.println(countSmallerAfterItself(array2));  // [0,1]

        int[] array3 = {};
        int[] queries3 = {2,4};
        System.out.println(countSmallerAfterItself(array3));  // [0,0]
    }
}

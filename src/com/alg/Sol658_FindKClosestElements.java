package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

    |a - x| < |b - x|, or
    |a - x| == |b - x| and a < b



Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]

Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]



Constraints:

    1 <= k <= arr.length
    1 <= arr.length <= 104
    arr is sorted in ascending order.
    -104 <= arr[i], x <= 104


 */
public class Sol658_FindKClosestElements {
    // time: O(nlogn) + O(klogk)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Convert from array to list first to make use of Collections.sort()
        List<Integer> sortedArr = new ArrayList<Integer>();
        for (int num: arr) {
            sortedArr.add(num);
        }

        // Sort using custom comparator
        Collections.sort(sortedArr, (num1, num2) -> Math.abs(num1 - x) - Math.abs(num2 - x));

        // Only take k elements
        sortedArr = sortedArr.subList(0, k);

        // Sort again to have output in ascending order
        Collections.sort(sortedArr);
        return sortedArr;
    }

    // O(n) using 2 pointers https://leetcode.com/problems/find-k-closest-elements/solutions/202785/very-simple-java-o-n-solution-using-two-pointers/
    public List<Integer> findClosestKElements(int[] arr, int k, int x) {
        int lo = 0;
        int high = arr.length - 1;
        while (high - lo >= k) {
            if (Math.abs(arr[lo] - x) > Math.abs(arr[high] - x)) {
                lo++;
            } else {
                high--;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = lo; i <= high; i++) {
            res.add(arr[i]);
        }
        return res;
    }


    //https://leetcode.com/problems/find-k-closest-elements/solutions/106426/java-c-python-binary-search-o-log-n-k-k/
    // time O(log(n-k))+ k

    //Assume we are taking A[i] ~ A[i + k -1].
    //We can binary research i
    //We compare the distance between x - A[mid] and A[mid + k] - x
    //
    //@vincent_gui listed the following cases:
    //Assume A[mid] ~ A[mid + k] is sliding window
    //
    //case 1: x - A[mid] < A[mid + k] - x, need to move window go left
    //-------x----A[mid]-----------------A[mid + k]----------
    //
    //case 2: x - A[mid] < A[mid + k] - x, need to move window go left again
    //-------A[mid]----x-----------------A[mid + k]----------
    //
    //case 3: x - A[mid] > A[mid + k] - x, need to move window go right
    //-------A[mid]------------------x---A[mid + k]----------
    //
    //case 4: x - A[mid] > A[mid + k] - x, need to move window go right
    //-------A[mid]---------------------A[mid + k]----x------
    public List<Integer> findClosestElements3(int[] A, int k, int x) {
        int left = 0, right = A.length - k;
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - A[mid] > A[mid + k] - x)
                left = mid + 1;
            else
                right = mid;
        }
        List<Integer> list = new ArrayList<>();
        int bound = left + k;
        for (int j = left; j < bound; j++) {
            list.add(A[j]);
        }
        return list;
        //         return Arrays.stream(A, left, left + k).boxed().collect(Collectors.toList());
    }
}

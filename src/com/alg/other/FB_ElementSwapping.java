package com.alg.other;

import java.util.Arrays;

/*
Given a sequence of n integers arr, determine the lexicographically smallest sequence which may be obtained from it after performing at most k element swaps, each involving a pair of consecutive elements in the sequence.
Note: A list x is lexicographically smaller than a different equal-length list y if and only if, for the earliest index at which the two lists differ, x's element at that index is smaller than y's element at that index.
Signature
int[] findMinArray(int[] arr, int k)
Input
n is in the range [1, 1000].
Each element of arr is in the range [1, 1,000,000].
k is in the range [1, 1000].
Output
Return an array of n integers output, the lexicographically smallest sequence achievable after at most k swaps.
Example 1
n = 3
k = 2
arr = [5, 3, 1]
output = [1, 5, 3]
We can swap the 2nd and 3rd elements, followed by the 1st and 2nd elements, to end up with the sequence [1, 5, 3]. This is the lexicographically smallest sequence achievable after at most 2 swaps.
Example 2
n = 5
k = 3
arr = [8, 9, 11, 2, 1]
output = [2, 8, 9, 11, 1]
We can swap [11, 2], followed by [9, 2], then [8, 2].
 */
public class FB_ElementSwapping {
    public int[] findMinArray(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            int minIndexWithinKDistance = findMinInK(arr, i, k);
            if (minIndexWithinKDistance == i) continue;
            swapK(arr, i, minIndexWithinKDistance);
            k = k - (minIndexWithinKDistance - i);
            if (k <= 0) {
                break;
            }
        }
        return arr;
    }

    // here start + k < n
    private int findMinInK(int[] arr, int start, int k) {
        int minIndex = start;
        int min = arr[start];
        int end = start + k;
        if (start + k > arr.length - 1) {
            end = arr.length - 1;
        }
        for (int i = start; i <= end ; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private void swapK(int[] arr, int start, int end) {
        for (int i = end - start; i > 0; i--) {
            int temp = arr[end];
            arr[end] = arr[end - 1];
            arr[end-1] = temp;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {8,9,11,2,1};
        int k = 5;
        FB_ElementSwapping f = new FB_ElementSwapping();
        int[] res = f.findMinArray(arr, k);
        System.out.println(Arrays.toString(res));
        // k = 4 :   1 8  9  11, 2
        // k = 5; 1,8,9,2,11
    }
}

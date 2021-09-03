package com.alg.other;
/*

Given an array arr[] of non negative integers. We can perform a swap operation on any two adjacent elements in the array.
Find the minimum number of swaps needed to sort the array in ascending order.

Examples :

Input  : arr[] = {3, 2, 1}
Output : 3
We need to do following swaps
(3, 2), (3, 1) and (1, 2)

Input  : arr[] = {1, 20, 6, 4, 5}
Output : 5
)
There is an interesting solution to this problem. It can be solved using the fact that number of swaps needed is equal to number of
inversions. So we basically need to count inversions in array.

The fact can be established using below observations:
1) A sorted array has no inversions.
2) An adjacent swap can reduce one inversion. Doing x adjacent swaps can reduce x inversions in an array.

https://tutorialspoint.dev/algorithm/sorting-algorithms/number-swaps-sort-adjacent-swapping-allowed
https://www.geeksforgeeks.org/number-swaps-sort-adjacent-swapping-allowed/

https://tutorialspoint.dev/data-structure/arrays/counting-inversions

 */
public class NumberOfSwapsToSortWhenOnlyAdjacetSwapAllowed {
    // This function sorts the input array and returns the number of inversions in the array
    public int countSwaps(int[] arr) {
        int n = arr.length;
        return inversionCount(arr, 0, n - 1);
    }

    private int inversionCount(int[] a, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right-left)/2;
        /* Inversion count will be sum of inversions in left-part, right-part
        and number of inversions in merging */
        int cnt = inversionCount(a, left, mid) + inversionCount(a, mid+1, right);
        cnt += mergeSort(a, left, mid, right);
        return cnt;
    }

    /*
    Inversion Count for an array indicates – how far (or close) the array is from being sorted. If array is already sorted then inversion count is 0. If array is sorted in reverse order that inversion count is the maximum.
Formally speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j

Example:
The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).
     */

    private int mergeSort(int[] a, int left, int mid, int right) {
        int cnt = 0;
        /* i is index for left subarray*/
        int i = left;
        /* j is index for right subarray*/
        int j = mid+1;
        /* k is index for resultant merged subarray*/
        int k = left;
        int[] b = new int[a.length];
        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) {
                b[k++] = a[i++];
            } else {
                b[k++] = a[j++];
                cnt += mid - i + 1; /* this is tricky -- see above explanation/diagram for merge()*/
                // count how many items in left subarray is smaller then the current j, it is always the
                // all elements from the current i to the index of mid
            }
        }
        /* Copy the remaining elements of left subarray  (if there are any) to temp*/
        while (i <= mid) {
            b[k++] = a[i++];
        }
        /* Copy the remaining elements of right subarray  (if there are any) to temp*/
        while (j <= right) {
            b[k++] = a[j++];
        }
        /*Copy back the merged elements to original array*/
        for(i=left; i<=right; i++) {
            a[i] = b[i];
        }
        return cnt;
    }

    public static void main(String[] args) {
        int []arr = {1, 20, 6, 4, 5};
        int n = arr.length;

        NumberOfSwapsToSortWhenOnlyAdjacetSwapAllowed numberOfSwapsToSortWhenOnlyAdjacetSwapAllowed = new NumberOfSwapsToSortWhenOnlyAdjacetSwapAllowed();
        int res = numberOfSwapsToSortWhenOnlyAdjacetSwapAllowed.countSwaps(arr);
        System.out.println("Number of swaps is " + res);

        int[] arr2 = {3, 2 ,1};
        int res2 = numberOfSwapsToSortWhenOnlyAdjacetSwapAllowed.countSwaps(arr2);
        System.out.println("Number of swaps is " + res2);


    }
}

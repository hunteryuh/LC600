package com.alg.other;

import java.util.Arrays;
import java.util.HashMap;

/*
https://www.geeksforgeeks.org/minimum-number-swaps-required-sort-array/

Given an array of n distinct elements, find the minimum number of swaps required to sort the array.

Examples:

Input : {4, 3, 2, 1}
Output : 2
Explanation : Swap index 0 with 3 and 1 with 2 to
              form the sorted array {1, 2, 3, 4}.

Input : {1, 5, 4, 3, 2}
Output : 2
 */
public class MinimumNumberOfSwapsRequiredToSortAnArray {


    // Return the minimum number
    // of swaps required to sort the array
    public int minSwaps(int[] arr, int N) {
        int ans = 0;
        int[] temp = Arrays.copyOfRange(arr, 0, N);
        Arrays.sort(temp);
        for (int i = 0; i < N; i++)
        {

            // This is checking whether
            // the current element is
            // at the right place or not
            if (arr[i] != temp[i])
            {
                ans++;
                // Swap the current element
                // with the right index
                // so that arr[0] to arr[i] is sorted
                swap(arr, i, indexOf(arr, temp[i]));
            }
        }
        return ans;
    }

    public int indexOf(int[] arr, int ele) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ele) {
                return i;
            }
        }
        return -1;
    }

    // Return the minimum number
    // of swaps required to sort the array
    public int minSwapsOptimized(int[] arr, int N)
    {

        int ans = 0;
        int[] temp = Arrays.copyOfRange(arr, 0, N);

        // Hashmap which stores the
        // indexes of the input array
        HashMap<Integer, Integer> h
                = new HashMap<Integer, Integer>();

        Arrays.sort(temp);
        for (int i = 0; i < N; i++) {
            h.put(arr[i], i);
        }
        for (int i = 0; i < N; i++) {

            // This is checking whether
            // the current element is
            // at the right place or not
            if (arr[i] != temp[i]) {
                ans++;
                int init = arr[i];

                // If not, swap this element
                // with the index of the
                // element which should come here
                swap(arr, i, h.get(temp[i]));

                // Update the indexes in
                // the hashmap accordingly
                h.put(init, h.get(temp[i]));
                h.put(temp[i], i);
            }
        }
        return ans;
    }
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Driver program to test the above function
    public static void main(String[] args)
            throws Exception
    {
        int[] a = { 101, 758, 315, 730, 472, 619, 460, 479 };
        int[] b = a.clone();
        int n = a.length;
        // Output will be 5
        System.out.println(new MinimumNumberOfSwapsRequiredToSortAnArray().minSwaps(a, n));
        System.out.println(new MinimumNumberOfSwapsRequiredToSortAnArray().minSwapsOptimized(b, n));
    }
}

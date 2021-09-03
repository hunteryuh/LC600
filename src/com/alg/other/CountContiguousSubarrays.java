package com.alg.other;

import java.util.Arrays;

/*
You are given an array a of N integers. For each index i, you are required to
determine the number of contiguous subarrays that fulfills the following conditions:
The value at index i must be the maximum element in the contiguous subarrays, and
These contiguous subarrays must either start from or end with i.

Output
An array where each index i contains an integer denoting the maximum number of contiguous subarrays of a[i]
Example:
a = [3, 4, 1, 6, 2]
output = [1, 3, 1, 5, 1]

Explanation:
For index 0 - [3] is the only contiguous subarray that starts (or ends) with 3, and the maximum value in this subarray is 3.
For index 1 - [4], [3, 4], [4, 1]
For index 2 -[1]
For index 3 - [6], [6, 2], [1, 6], [4, 1, 6], [3, 4, 1, 6]
For index 4 - [2]
So, the answer for the above input is [1, 3, 1, 5, 1]
 */
public class CountContiguousSubarrays {
    public int[] countSubarrays(int[] arr) {
        // Write your code here
        int[] res = new int[arr.length];
        for (int i = 0; i< res.length; i++) {
            int l = 0;
            int r = 0;
            if (i != 0) {
                l = getNumberOfValidSubarraysAsEnding(i, arr);
            }
            if (i != res.length - 1) {
                r = getNumberOfValidSubarraysAsStart(i, arr);
            }
            res[i] = l + r + 1;
        }
        return res;

    }

    private int getNumberOfValidSubarraysAsEnding(int p, int[] arr) {
        int count = 0;
        for (int i = p - 1; i >= 0; i--) {
            if (arr[i] <= arr[p]) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private int getNumberOfValidSubarraysAsStart(int p, int[] arr) {
        int count = 0;
        for (int i = p + 1; i < arr.length; i++) {
            if (arr[i] < arr[p]) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] input = {3,4,1,6,2};
        CountContiguousSubarrays c = new CountContiguousSubarrays();
        int[] r = c.countSubarrays(input);
        System.out.println(Arrays.toString(r));
    }

    public int[] countSubArrays(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int l = i, r = i;
            while (l - 1 >= 0 && arr[l - 1] <= arr[i]) l--;
            while (r + 1 <= ans.length - 1 && arr[r + 1] <= arr[i]) r++;
            ans[i] = r - l + 1;
        }
        return ans;
    }
}

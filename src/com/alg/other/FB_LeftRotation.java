package com.alg.other;

public class FB_LeftRotation {
    // rotate using temp array
    // time: n, space: n
    public int[] leftRotation(int[] arr, int d) {
        int n = arr.length;
        int[] temp = new int[n];
        int k = 0;
        for (int i = d; i < n; i++) {
            temp[k] = arr[i];
            k++;
        }
        for (int i = 0; i < d; i++) {
            temp[k] = arr[i];
            k++;
        }
        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }
        return arr;
    }

    // method 2: reverse left; reverse right, then reverse whole
    // time: O(n) extra space: O(1)
    public int[] leftRotate2(int[] arr, int d) {
        // 12345 2   34512
        reverse(arr, 0, d - 1);
        reverse(arr, d, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
        return arr;
    }
    private void reverse(int[] a, int start, int end) {
        while (start < end) {
            int temp = a[start];
            a[start] = a[end];
            a[end] = temp;
            start++;
            end--;
        }
    }

}

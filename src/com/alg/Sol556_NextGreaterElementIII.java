package com.alg;
/*
Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and
is greater in value than n. If no such positive integer exists, return -1.

Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.



Example 1:

Input: n = 12
Output: 21

Example 2:

Input: n = 21
Output: -1



Constraints:

    1 <= n <= 231 - 1


 */
public class Sol556_NextGreaterElementIII {
    public int nextGreaterElement(int n) {
        char[] a = String.valueOf(n).toCharArray(); // or ("" + n).toCharArray()
//        System.out.println(a);
        int i = a.length - 2;
        while (i >= 0 && a[i + 1] <= a[i]) {
            i--;
        }
        if (i < 0) return -1;
        int j = a.length - 1;
        // II) Find the smallest digit on right side of (i)'th
        // digit that is greater than number[i]
        while (j >= 0 && a[i] >= a[j]) {
            j--;
        }
        swap(a, i, j); // swap first decrease element (count from back) with first element (count from back) larger than it
        reverse(a, i + 1);
        return Integer.parseInt(new String(a)); // String.valueOf(a)
    }

    private void reverse(char[] a, int start) {
        int i = start;
        int j = a.length - 1;
        while (i < j) {
            swap(a, i, j);
            i++;j--;
        }

    }

    private void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Sol556_NextGreaterElementIII ss = new Sol556_NextGreaterElementIII();
        ss.nextGreaterElement(12);
    }
}

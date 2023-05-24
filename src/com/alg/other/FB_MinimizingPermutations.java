package com.alg.other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*In this problem, you are given an integer N, and a permutation, P of the integers from 1 to N, denoted as (a_1, a_2, ..., a_N). You want to rearrange the elements of the permutation into increasing order, repeatedly making the following operation:
Select a sub-portion of the permutation, (a_i, ..., a_j), and reverse its order.
Your goal is to compute the minimum number of such operations required to return the permutation to increasing order.
Signature
int minOperations(int[] arr)
Input
Array arr is a permutation of all integers from 1 to N, N is between 1 and 8
Output
An integer denoting the minimum number of operations required to arrange the permutation in increasing order
Example
If N = 3, and P = (3, 1, 2), we can do the following operations:
Select (1, 2) and reverse it: P = (3, 2, 1).
Select (3, 2, 1) and reverse it: P = (1, 2, 3).
output = 2

 */
public class FB_MinimizingPermutations {
    public int minOperations(int[] arr) {
        if (arr == null || arr.length == 0 || isSorted(arr)) {
            return 0;
        }
        int res = 0;
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(arr);
        visited.add(Arrays.toString(arr));
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i< size; i++) {
                int[] cur = queue.poll();
                for (int l = 0; l < cur.length; l++) {
                    for (int r = l + 1; r < cur.length; r++) {
                        int[] next = Arrays.copyOf(cur, cur.length);
                        subReverse(next, l, r);
                        if (isSorted(next)) return res;
                        if (!visited.contains(Arrays.toString(next))) {
                            visited.add(Arrays.toString(next));
                            queue.offer(next);
                        }
                    }
                }
            }
        }

        return -1;

    }
    private void subReverse(int[] arr, int left, int right) {
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
    private boolean isSorted(int[] arr) {
        for (int i = 0; i + 1 < arr.length; i++) {
            if (arr[i+1] < arr[i]) {
                return false;
            }
        }
        return true;
    }
    static int test_case_number = 1;
    public static void main(String[] args) {
        FB_MinimizingPermutations f = new FB_MinimizingPermutations();
        int n_1 = 5;
        int[] arr_1 = {1, 2, 5, 4, 3};
        int expected_1 = 1;
        int output_1 = f.minOperations(arr_1);
        check(expected_1, output_1);

        int n_2 = 3;
        int[] arr_2 = {3, 1, 2};
        int expected_2 = 2;
        int output_2 = f.minOperations(arr_2);
        check(expected_2, output_2);
    }

    static void check(int expected, int output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printInteger(expected);
            System.out.print(" Your output: ");
            printInteger(output);
            System.out.println();
        }
        test_case_number++;
    }
    static void printInteger(int n) {
        System.out.print("[" + n + "]");
    }
}

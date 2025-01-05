package com.alg;

import java.util.PriorityQueue;

/*
Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

You must find a solution with a memory complexity better than O(n2).



Example 1:

Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13

Example 2:

Input: matrix = [[-5]], k = 1
Output: -5



Constraints:

    n == matrix.length == matrix[i].length
    1 <= n <= 300
    -109 <= matrix[i][j] <= 109
    All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
    1 <= k <= n2



Follow up:

    Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
    Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.


 */
public class Sol378_KthSmallestElementInASortedMatrix {
    // https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/solutions/1322101/c-java-python-maxheap-minheap-binary-search-picture-explain-clean-concise/
    // O(min(m,k) + O(klogk)
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int res = -1;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        for (int r = 0; r < Math.min(m, k); r++) {
            minHeap.offer(new int[]{matrix[r][0], r, 0});
        }
        for (int i = 1; i <= k; i++) {
            int[] top = minHeap.poll();
            int r = top[1];
            int c = top[2];
            res = top[0];
            if (c + 1 < n) {
                minHeap.offer(new int[]{matrix[r][c + 1], r, c + 1});
            }
        }
        return res;
    }
}

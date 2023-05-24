package com.alg;
/*
For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 <= i < j < nums.length and nums[i] > nums[j].

Given two integers n and k, return the number of different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs. Since the answer can be huge, return it modulo 109 + 7.



Example 1:

Input: n = 3, k = 0
Output: 1
Explanation: Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pairs.

Example 2:

Input: n = 3, k = 1
Output: 2
Explanation: The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.



Constraints:

    1 <= n <= 1000
    0 <= k <= 1000


 */
// https://leetcode.com/problems/k-inverse-pairs-array/solutions/127746/k-inverse-pairs-array/
public class Sol629_KInversePairsArray {
    /*
    From the above discussion, we can obtain the recursive formula for finding the number of arrangements with exactly kkk inverse pairs as follows. Let's say count(i,j)count(i,j)count(i,j) represents the number of arrangements with iii elements and exactly jjj inverse pairs.

    If n=0n=0n=0, no inverse pairs exist. Thus, count(0,k)=0count(0,k)=0count(0,k)=0.

    If k=0k=0k=0, only one arrangement is possible, which is all numbers sorted in ascending order. Thus, count(n,0)=1count(n,0)=1count(n,0)=1.

    Otherwise, count(n,k)=∑i=0min(k,n−1)count(n−1,k−i)count(n,k) = \sum_{i=0}^{min(k,n-1)} count(n-1, k-i)count(n,k)=∑i=0min(k,n−1)​count(n−1,k−i).


Time complexity : O(nkmin⁡(n,k))O(nk\min(n, k))O(nkmin(n,k)). The function kInversePairs is called nknknk times to fill the memomemomemo array. Each function call itself takes O(min⁡(n,k))O(\min(n, k))O(min(n,k)) time at worse.

Space complexity : O(nk)O(nk)O(nk). memomemomemo array of constant size n⋅kn \cdot kn⋅k is used. The depth of recursion tree can go upto nnn.

     */
    Integer[][] memo = new Integer[1001][1001];
    public int kInversePairs(int n, int k) {
        if (n == 0)
            return 0;
        if (k == 0)
            return 1;
        if (memo[n][k] != null)
            return memo[n][k];
        int inv = 0;
        for (int i = 0; i <= Math.min(k, n - 1); i++)
            inv = (inv + kInversePairs(n - 1, k - i)) % 1000000007;
        memo[n][k] = inv;
        return inv;
    }
}

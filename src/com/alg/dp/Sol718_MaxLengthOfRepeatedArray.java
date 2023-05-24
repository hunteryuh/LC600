package com.alg.dp;

/**
 * Created by HAU on 2/10/2018.
 */
/*Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:
Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation:
The repeated subarray with maximum length is [3, 2, 1].
Note:
1 <= len(A), len(B) <= 100*/
public class Sol718_MaxLengthOfRepeatedArray {
    // dp
    public static int findLength(int[] A, int[] B) {
        int res = 0;
        int m= A.length, n = B.length;
        int[][] dp = new int[m+1][n+1];
        for(int i = m - 1; i >=0; i--){
            for(int j = n - 1; j>= 0; j--){
                if(A[i] == B[j]){
                    dp[i][j] = dp[i+1][j+1] + 1;
                    if ( res < dp[i][j]) {
                        res = dp[i][j];
                    }
                }
            }
        }
        return res;
    }
    // from 0 dp  时间复杂度：O(n^2)，空间复杂度：O(n^2)
    public static int findLength2(int[] A, int[] B) {
        int res = 0;
        int m= A.length, n = B.length;
        int[][] dp = new int[m+1][n+1];
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }else {
                    if (A[i-1] == B[j-1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        if (res < dp[i][j]) {
                            res = dp[i][j];
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] A= {1,2,3,2,1};
        int[] B= {3,2,1,2,3,2};
        System.out.println(findLength(A,B));
        System.out.println(findLen(A,B));
        int[] C = {1,0,0,0,1};
        int[] D = {1,0,0,1,1};
        System.out.println(findLen(C,D));
    }
    // 1d dp from 0,
    /*这种解法是针对“最长连续公共子序列”的问题所设计的，一旦涉及不连续的元素，dp[]中的值全部都会置为0；而我们上面的一种解法是既可以同时解决不连续子序列的问题的。
既然这种解法可以通过，那么说明这个题目的初始设置想法就是求“最长连续公共子序列”。*/
    public static int findLength3(int[] A, int[] B) {
        int ans = 0;
        int[] dp = new int[A.length + 1];
        for (int j = 1; j <= B.length; j++) {
            for (int i = A.length; i >= 1; i--) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = 0;
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }
    // 1d dp array
    public static int findLen(int[] A, int[] B){
        int m = A.length, n = B.length;
        int[] dp = new int[n+1];
        int max = 0;
        for(int i = m - 1; i >= 0; i--){
            for(int j = 0; j < n; j++){ // from 0 to n
                if(A[i] == B[j]){
                    dp[j] = 1 + dp[j+1];
                    if( max < dp[j]){
                        max = dp[j];
                    }

                }else{
                    dp[j] = 0;
                }
            }
        }
        return max;
    }

    // refer to https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0718.%E6%9C%80%E9%95%BF%E9%87%8D%E5%A4%8D%E5%AD%90%E6%95%B0%E7%BB%84.md
    public int findLen2(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[][] dp = new int[m+1][n+1];
        // dp[i][j] max length of repeated array till i-1 in A and j-1 in B
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i] == B[j]) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                }
                res = Math.max(res, dp[i+1][j+1]);
            }
        }
        return res;
    }
}

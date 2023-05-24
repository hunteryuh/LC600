package com.alg.dp;

/**
 * Created by HAU on 12/2/2017.
 */
/*Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character*/
public class Sol72_EditDistance {
    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0072.%E7%BC%96%E8%BE%91%E8%B7%9D%E7%A6%BB.md
    public static int minDistance(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }
        int k1 = word1.length();
        int k2 = word2.length();
        if( k1 == 0 || k2 == 0){
            return Math.abs(k1 - k2);
        }
        // dp[i][j] 表示以下标i-1为结尾的字符串word1，和以下标j-1为结尾的字符串word2，最近编辑距离为dp[i][j]
        int[][] dp = new int[k1 + 1][k2+1];
        for (int i = 0; i <= k1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= k2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= k1; i++) {
            for (int j= 1; j <= k2; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;  // key
                }
            }
        }
        return dp[k1][k2];
    }

    public static void main(String[] args) {
        String s1 = "girlfr";
        String s2 = "frday";
        System.out.println(minDistance(s1,s2));
    }
}

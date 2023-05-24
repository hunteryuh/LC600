package com.alg;

import java.util.Arrays;

/*
You have an initial power of power, an initial score of 0, and a bag of tokens where tokens[i] is the value of the ith token (0-indexed).

Your goal is to maximize your total score by potentially playing each token in one of two ways:

    If your current power is at least tokens[i], you may play the ith token face up, losing tokens[i] power and gaining 1 score.
    If your current score is at least 1, you may play the ith token face down, gaining tokens[i] power and losing 1 score.

Each token may be played at most once and in any order. You do not have to play all the tokens.

Return the largest possible score you can achieve after playing any number of tokens.



Example 1:

Input: tokens = [100], power = 50
Output: 0
Explanation: Playing the only token in the bag is impossible because you either have too little power or too little score.

Example 2:

Input: tokens = [100,200], power = 150
Output: 1
Explanation: Play the 0th token (100) face up, your power becomes 50 and score becomes 1.
There is no need to play the 1st token since you cannot play it face up to add to your score.

Example 3:

Input: tokens = [100,200,300,400], power = 200
Output: 2
Explanation: Play the tokens in this order to get a score of 2:
1. Play the 0th token (100) face up, your power becomes 100 and score becomes 1.
2. Play the 3rd token (400) face down, your power becomes 500 and score becomes 0.
3. Play the 1st token (200) face up, your power becomes 300 and score becomes 1.
4. Play the 2nd token (300) face up, your power becomes 0 and score becomes 2.

 */
public class Sol948_BagOfTokens {
    // https://leetcode.com/problems/bag-of-tokens/solutions/197657/bag-of-tokens/
    // greedy
    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);
        int lo = 0, hi = tokens.length - 1;
        int points = 0, ans = 0;
        while (lo <= hi && (P >= tokens[lo] || points > 0)) {
            while (lo <= hi && P >= tokens[lo]) {
                P -= tokens[lo++];
                points++;
            }

            ans = Math.max(ans, points);
            if (lo <= hi && points > 0) {
                P += tokens[hi--];
                points--;
            }
        }

        return ans;
    }

    // greey with 2 pointers
    //https://leetcode.com/problems/bag-of-tokens/solutions/197696/c-java-python-greedy-two-pointers/
    public int bagOfTokensScore2(int[] A, int P) {
        Arrays.sort(A);
        int res = 0, points = 0, i = 0, j = A.length - 1;
        while (i <= j) {
            if (P >= A[i]) {
                P -= A[i++];
                res = Math.max(res, ++points);
            } else if (points > 0) {
                points--;
                P += A[j--];
            } else {
                break;
            }
        }
        return res;
    }
}

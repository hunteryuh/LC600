package com.alg;
/*
You are given a list of songs where the ith song has a duration of time[i] seconds.

Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.



Example 1:

Input: time = [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
Example 2:

Input: time = [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.


Constraints:

1 <= time.length <= 6 * 104
1 <= time[i] <= 500
 */
public class Sol1010_PairsOfSongsWithTotalDurationDivisibleBy60 {
    // brute force , Time O(n^2), space O(1)
    public int numPairsDivisibleBy60(int[] time) {
        int count = 0;
        int n = time.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((time[i] + time[j] ) % 60 == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    // time O(n)
    // 30 30 30
    public int numPairs(int[] time) {
        int[] remainders = new int[60];
        int count = 0;
        for (int t: time) {
            if (t % 60 == 0) {
                count += remainders[0];
            } else {// check if a % 60 + b % 60 == 60
                count += remainders[60 - t % 60];
                System.out.println(count);
            }
            remainders[t %60]++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] time = {30, 30, 30, 30};
        Sol1010_PairsOfSongsWithTotalDurationDivisibleBy60 ss = new Sol1010_PairsOfSongsWithTotalDurationDivisibleBy60();
        System.out.println(ss.numPairs(time));
    }
}

package com.alg;
/*

You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.

The population of some year x is the number of people alive during that year. The ith person is counted
in year x's population if x is in the inclusive range [birthi, deathi - 1].
Note that the person is not counted in the year that they die.

Return the earliest year with the maximum population.



Example 1:

Input: logs = [[1993,1999],[2000,2010]]
Output: 1993
Explanation: The maximum population is 1, and 1993 is the earliest year with this population.
Example 2:

Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
Output: 1960
Explanation:
The maximum population is 2, and it had happened in years 1960 and 1970.
The earlier year between them is 1960.
 */
public class Sol1854_MaximumPopulationYear {
    public int maximumPopulation(int[][] logs) {
        int firstBirth = getFB(logs);
        int lastBirth = getLB(logs);
        int[] deltas = getDeltas(logs, firstBirth, lastBirth);
        int rs = getRunningSumIndex(deltas);
        return rs + firstBirth;
    }
    private int getFB(int[][] logs) {
        int fb = Integer.MAX_VALUE;
        for(int[] a : logs) {
            fb = Math.min(a[0], fb);
        }
        return fb;
    }

    private int getLB(int[][] logs) {
        int lb = Integer.MIN_VALUE;
        for(int[] a : logs) {
            lb = Math.max(a[0], lb);
        }
        return lb;
    }

    private int[] getDeltas(int[][] logs, int fb, int lb) {
        int[] deltas = new int[lb - fb + 1];
        for(int[] a : logs) {
            deltas[a[0] - fb]++;
            if (a[1] - fb < lb - fb + 1) {  // to avoid index out of bound
                deltas[a[1] - fb]--;
            }
        }
        return deltas;
    }

    private int getRunningSumIndex(int[] a) {
        int res = 0;
        int max = 0;
        int runningSum = 0;
        for (int i = 0; i < a.length; i++) {
            runningSum += a[i];
            if (runningSum > max) {
                max = runningSum;
                res = i;
            }
        }
        return res;
    }

    //https://leetcode.com/problems/maximum-population-year/discuss/1198978/JAVA-oror-O(n)-solution-With-Explanation-oror-Range-Addition
    public int maxPop(int[][] logs) {
        //1950 <= birthi < deathi <= 2050
        int[] year = new int[101];
        for(int[] log : logs) {
            year[log[0] - 1950]++;
            year[log[1] - 1950]--;
        }
        int res = 0;
        int[] prefixSum = new int[101];
        int max = year[0];
        for (int i = 0; i < year.length; i++) {
            if (i == 0) {
                prefixSum[i] = year[0];
                continue;
            }
            prefixSum[i] = year[i] + prefixSum[i-1];
            if (prefixSum[i] > max) {
                max = prefixSum[i];
                res = i;
            }
        }
        return 1950 + res;
    }

    public static void main(String[] args) {

    }
}

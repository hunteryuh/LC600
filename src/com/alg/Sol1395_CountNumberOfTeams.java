package com.alg;
/*
There are n soldiers standing in a line. Each soldier is assigned a unique rating value.

You have to form a team of 3 soldiers amongst them under the following rules:

Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).



Example 1:

Input: rating = [2,5,3,4,1]
Output: 3
Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1).
Example 2:

Input: rating = [2,1,3]
Output: 0
Explanation: We can't form any team given the conditions.
Example 3:

Input: rating = [1,2,3,4]
Output: 4

 */
public class Sol1395_CountNumberOfTeams {
    public static int numTeams(int[] rating) {
        int res = 0;
        for (int i = 0; i < rating.length; i++) {
            for (int j = i + 1; j < rating.length; j++) {
                for (int k = j + 1; k < rating.length; k++) {
                    if (rating[i] < rating[j] && rating[j] < rating[k] ||
                        rating[i] > rating[j] && rating[j] > rating[k]) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ratings = {1,2,3,4};
        int[] ratings2 = {2,1,4};
        int[] ratings3 = {2,5,3,4,1};
        System.out.println(numTeams(ratings));
        System.out.println(numTeams(ratings2));
        System.out.println(numTeams(ratings3));
    }

    public int numTeams2(int[] rating) {
        int n = rating.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int l = 0;
            int r = 0;

            for (int j = 0; j < i; j++) {
                if (rating[j] < rating[i]) l++;
            }

            for (int k = i + 1; k < n; k++) {
                if (rating[i] < rating[k]) r++;
            }

            ans += (l * r) + (i - l) * (n - i - r - 1);
            // if there are l less than i on its left, then (i-l) that is greater than i on its left
            // if there are r greater than i on this right, then there are (n-i-1) -r that are less than it on its right
        }

        return ans;
    }


}

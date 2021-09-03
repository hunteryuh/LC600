package com.alg;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*

You are given an m x n integer grid accounts where accounts[i][j] is the amount of money the i​​​​​​​​​​​th​​​​ customer has in the j​​​​​​​​​​​th​​​​ bank. Return the wealth that the richest customer has.

A customer's wealth is the amount of money they have in all their bank accounts. The richest customer is the customer that has the maximum wealth.



Example 1:

Input: accounts = [[1,2,3],[3,2,1]]
Output: 6
Explanation:
1st customer has wealth = 1 + 2 + 3 = 6
2nd customer has wealth = 3 + 2 + 1 = 6
Both customers are considered the richest with a wealth of 6 each, so return 6.
Example 2:

Input: accounts = [[1,5],[7,3],[3,5]]
Output: 10
Explanation:
1st customer has wealth = 6
2nd customer has wealth = 10
3rd customer has wealth = 8
The 2nd customer is the richest with a wealth of 10.
Example 3:

Input: accounts = [[2,8,7],[7,1,3],[1,9,5]]
Output: 17

https://leetcode.com/problems/richest-customer-wealth/
 */
public class Sol1672_RichestCustomerWealth {

    public static int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int[] user : accounts) {
            int sum = 0;
            for (int acount : user) {
                sum += acount;
            }
            if (sum > max) max = sum;
        }
        return max;
    }

    public static int maxWealth(int[][] nums) {
        return Arrays.stream(nums)
                .mapToInt(i -> Arrays.stream(i).sum())
                .max()
                .getAsInt();
    }

    public static int maxWealth2(int[][] nums) {
        return Stream.of(nums)
                .map(account -> IntStream.of(account).sum())
                .max(Comparator.naturalOrder())
                .get();
    }
    public static void main(String[] args) {
        int[][] accounts = { {1,5}, {7,4}, {3,5}};
        System.out.println(maximumWealth(accounts));
        System.out.println(maxWealth(accounts));
        System.out.println(maxWealth2(accounts));
    }
}

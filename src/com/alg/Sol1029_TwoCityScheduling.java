package com.alg;

import java.util.Arrays;

/*
A company is planning to interview 2n people. Given the array costs where costs[i] = [aCosti, bCosti], the cost of flying the ith person to city a is aCosti, and the cost of flying the ith person to city b is bCosti.

Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.



Example 1:

Input: costs = [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation:
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
Example 2:

Input: costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
Output: 1859
Example 3:

Input: costs = [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,359],[631,42]]
Output: 3086


Constraints:

2 * n == costs.length
2 <= costs.length <= 100
costs.length is even.
1 <= aCosti, bCosti <= 1000
 */
public class Sol1029_TwoCityScheduling {
    //https://leetcode.com/problems/two-city-scheduling/editorial/
    // time O(n logn)
    // space O(logN) in java Arrays.sort which is a quick sort algorithm
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a, b) -> a[0] - a[1] - (b[0]- b[1]));
        int length = costs.length;
        int res = 0;
        // To optimize the company expenses,
        // send the first n persons to the city A
        // and the others to the city B
        for (int i = 0; i < length; i++) {
            if (i < length / 2) {
                res += costs[i][0];
            } else {
                res += costs[i][1];
            }
        }
        return res;
    }

    // https://leetcode.com/problems/two-city-scheduling/solutions/667786/java-c-python3-with-detailed-explanation/
    public int twoCityScheduleCost2(int[][] costs) {
        // send all to a, then sort on refund if pick n to b
        int N = costs.length/2;
        int[] refund = new int[N * 2];
        int minCost = 0, index = 0;
        for(int[] cost : costs){
            refund[index++] = cost[1] - cost[0]; // negative, sort from small to large
            minCost += cost[0];
        }
        Arrays.sort(refund);
        for(int i = 0; i < N; i++){
            minCost += refund[i];
        }
        return minCost;
    }

    public static void main(String[] args) {
        Sol1029_TwoCityScheduling ss = new Sol1029_TwoCityScheduling();
        int[][] costs = {{10,20},{30,200},{400,50},{30,20}};
        int res = ss.twoCityScheduleCost2(costs);
        System.out.println(res);
    }


}

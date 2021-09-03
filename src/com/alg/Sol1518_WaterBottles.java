package com.alg;
/*
Given numBottles full water bottles, you can exchange numExchange empty water bottles for one full water bottle.

The operation of drinking a full water bottle turns it into an empty bottle.

Return the maximum number of water bottles you can drink.

Example 1:



Input: numBottles = 9, numExchange = 3
Output: 13
Explanation: You can exchange 3 empty bottles to get 1 full water bottle.
Number of water bottles you can drink: 9 + 3 + 1 = 13.

 */
public class Sol1518_WaterBottles {
    public static int numWaterBottles(int numBottles, int numExchange) {
        int res = 0;
        int ne = 0;
        while (numBottles > 0 || numBottles + ne >= numExchange) {
            res += numBottles;
            int t = numBottles + ne;
            ne = t % numExchange;
            numBottles = t / numExchange;
        }
        return res;
    }

    public static void main(String[] args) {
        int a = 9, exc = 3;
        System.out.println(numWaterBottles(a, exc));
        System.out.println(numWaterBottles(15, 4));
    }

    //O(1):
    //Key observation: A full bottle of water = A empty bottle + One (bottle) unit of water.

    public int numWaterBottles2(int numBottles, int numExchange) {
        return numBottles + (numBottles - 1) / (numExchange - 1);
    }
}

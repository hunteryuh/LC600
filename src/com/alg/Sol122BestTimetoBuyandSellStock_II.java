package com.alg;

/**
 * Created by HAU on 5/24/2017.
 */
//Design an algorithm to find the maximum profit. You may complete
//as many transactions as you like
//(ie, buy one and sell one share of the stock multiple times).

public class Sol122BestTimetoBuyandSellStock_II {
    //(Simple One Pass) O(n) time O(1) space
    public static int maxProfit(int[] prices) {
        if (prices.length==0) return 0;
        int profit = 0;
        int n = prices.length;
        for (int i = 1; i < n; i++){
            if(prices[i]-prices[i-1]>0){
                profit += prices[i]-prices[i-1];
            }
        }
        return profit;

    }
    //(Peak Valley Approach)
    public static int maxP2(int[] prices){
        if (prices.length==0) return 0;
        int valley = prices[0], peak = prices[0];
        int res = 0, i = 0;
        int n = prices.length;
        while ( i < n - 1){
            while(i < n - 1 && prices[i] >= prices[i+1]){
                i++;
            }
            valley = prices[i];
            while ( i < n - 1 && prices[i] <= prices[i+1]){
                i++;
            }
            peak = prices[i];
            res += peak - valley;
        }
        return res;
    }



    public static void main(String[] args) {
        int[] prices1 = {7,1,5,3,6,5,4};
        int[] prices2={6,4,3,2,1};
        //int sol = 0;
        System.out.println(maxProfit(prices1));
        System.out.println(maxProfit(prices2));
        System.out.println(maxP2(prices1));
        //System.out.println(maxP2(prices1));
        //System.out.println(maxP2(prices2));


    }
}

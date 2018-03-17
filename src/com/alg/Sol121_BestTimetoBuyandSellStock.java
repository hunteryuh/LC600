package com.alg;

/**
 * Created by HAU on 5/23/2017.
 */
public class Sol121_BestTimetoBuyandSellStock {
    public static int maxProfit(int[] prices){
        int max=0;
        int n = prices.length;
        for (int i=0;i<n-1;i++)
            for (int j = i + 1; j < n; j++) {
                max = Math.max(prices[j] - prices[i], max);  // double loop  O (n^2)
            }
        return max;
    }
    public static int maxP2(int[] prices){
        if (prices.length==0) return 0;
        int max = 0;
        int n = prices.length;
        int min = prices[0];

        for (int i =1; i<n;i++){   //one loop, O(n)
            if(prices[i]<min)  min = prices[i];  // update min
            else if (prices[i]-min>max)
                max = prices[i]-min;   // update maxProfit

        }
        return max;
    }



    public static void main(String[] args) {
        int[] prices1 = {7,1,5,3,6,5,4};
        int[] prices2={6,4,3,2,1};
        //int sol = 0;
        //System.out.println(maxProfit(prices1));
        //System.out.println(maxProfit(prices2));
        System.out.println(maxP2(prices1));
        System.out.println(maxP2(prices2));
        int[] prices3 = {1,2};

        System.out.println(maxProfit(prices3));

    }
}

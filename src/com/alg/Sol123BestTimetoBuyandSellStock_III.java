package com.alg;

/**
 * Created by HAU on 5/24/2017.
 */
public class Sol123BestTimetoBuyandSellStock_III {
    public static int maxProfit2times(int[] prices){
        if (prices.length ==0) return 0;
        int n = prices.length;
        int profit1 = 0;
        int profit2 ;
        int min1= prices[0];
        int min2;
        int sum = 0;
        for(int i=1;i<n;i++){
            if(prices[i]<min1){
                min1 = prices[i];
            }else if(prices[i]-min1>profit1)
                profit1 = prices[i]-min1;
            min2 = prices[i];
            profit2 =0;
            for (int j=i+1;j<n;j++){
                if(prices[j]< min2) min2=prices[j];
                else if (prices[j]-min2> profit2)
                    profit2= prices[j]-min2;
            }
            if (sum < profit1 + profit2)
                sum = profit1+ profit2;

        }
        return sum;  // O(n^2)  time limit exceeded...


    }
    public static int maxProfit2times_2(int[] prices) {
        if (prices.length ==0) return 0;
        int n = prices.length;

        int profit1 = 0;
        int profit2 = 0;
        int minP = prices[0];
        int maxP = prices[n-1];

        int[] left = new int[n];
        int[] right = new int[n];
        for (int i=1;i<n;i++){
            if (prices[i]<minP) minP = prices[i];
            else if (prices[i]-minP>profit1)
                profit1 = prices[i]-minP;
            left[i] = profit1;
        }
        for (int i=n-1;i>=0;i--){
            if (prices[i]>maxP) maxP = prices[i];
            else if (maxP-prices[i]>profit2)
                profit2 = maxP - prices[i];
            right[i] = profit2;
        }

        int result = 0;
        for (int i=0;i<n;i++){
            if(left[i]+right[i]> result)
                result = left[i]+right[i];
        }
        return result;
    }


    public static void main(String[] args){
        int[] nums = {7,1,5,3,6,5,4};
        //int k = 2;
        System.out.println(maxProfit2times(nums));
        System.out.println(maxProfit2times_2(nums));
    }
}